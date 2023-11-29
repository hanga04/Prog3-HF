package Yavalath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

        /**
        * Ez az osztály működteti a 2 személyes játékot
        */
public class Game implements Serializable {
    /**
     * Kiírja a játék során, hogy melyik játékos van soron
     */
    private static JLabel field;
    /**
     * Gomb, ami a játék végét jelzi és meghívja
     */
    private JButton vege;
    /**
     * MapFrame objektum, ami a megjelenítést végzi
     */
    private MapFrame map;
    /**
     * GameLogic objektum, ami a játék logikáját adja
     */
    private GameLogic game;


    /**
     * Létrehozza a MapFrame objektumot, beállítja a megfelelő megjelenítést
     * Létrehozza a GameLogic objektumot, beállítja a játékosokat
     * A játékosok nevének és színének beállításához egy új ablakot hoz létre,
     * az ott kapott értékeket állítja be a játékosok tulajdonságainak
     * Meghívja a játékot indító függvényt
     * @throws IOException
     */
    public Game() throws IOException {

        map=new MapFrame("2 Players");
        map.setSize(1500, 1500);
        game=new GameLogic(map.table, "2 Players");
        map.add(map.Menu(map.table, game), BorderLayout.NORTH);
        map.setVisible(true);

        game.players.add(0, new Player());
        game.players.add(1, new Player());

        JFrame set = new JFrame();
        set.setSize(750, 500);
        JPanel pan1 = new JPanel();
        JPanel pan2 = new JPanel();
        JPanel pan3 = new JPanel();

        JComboBox<String> colorComboBox = new JComboBox<>(new String[]{"Piros", "Zöld", "Kék"});
        JComboBox<String> colorComboBox2 = new JComboBox<>(new String[]{"Piros", "Zöld", "Kék"});

        JButton gomb = new JButton("mehet");
        JTextField elso=new JTextField(20);
        JTextField masodik=new JTextField(20);
        pan1.setLayout(new FlowLayout());
        pan1.add(new JLabel("Első játékos neve"));
        pan1.add(elso);
        pan1.add(colorComboBox);
        pan2.setLayout(new FlowLayout());
        pan2.add(new JLabel("Második játékos neve"));
        pan2.add(masodik);
        pan2.add(colorComboBox2);
        pan3.add(gomb);
        gomb.addActionListener(e -> {
            String szin=(String) colorComboBox.getSelectedItem();
            if (szin.equals("Piros")) {
                game.players.get(0).setColor(Colors.RED);
                game.players.get(0).setImage(new ImageIcon("C:\\Users\\Hanga\\Yavalath\\piros.png"));
            } else if (szin.equals("Zöld")) {
                game.players.get(0).setColor(Colors.GREEN);
                game.players.get(0).setImage(new ImageIcon("C:\\Users\\Hanga\\Yavalath\\zöld.png"));

            } else if (szin.equals("Kék")) {
                game.players.get(0).setColor(Colors.BLUE);
                game.players.get(0).setImage(new ImageIcon("C:\\Users\\Hanga\\Yavalath\\kék.png"));

            }
            String szin2=(String) colorComboBox2.getSelectedItem();
            switch(szin2){
                case "Piros": game.players.get(1).setColor(Colors.RED);
                game.players.get(1).setImage(new ImageIcon("C:\\Users\\Hanga\\Yavalath\\piros.png"));
                break;
                case "Zöld": game.players.get(1).setColor(Colors.GREEN);
                game.players.get(1).setImage(new ImageIcon("C:\\Users\\Hanga\\Yavalath\\zöld.png"));
                break;
                case "Kék": game.players.get(1).setColor(Colors.BLUE);
                game.players.get(1).setImage(new ImageIcon("C:\\Users\\Hanga\\Yavalath\\kék.png"));
                break;

            }
            game.players.get(0).setName(elso.getText());
            game.players.get(1).setName(masodik.getText());
            if(game.players.get(0).getColor().equals(game.players.get(1).getColor())) return;
            set.setVisible(false);
            map.setVisible(true);
        });
        set.setLayout(new GridLayout(3, 0));
        set.add(pan1);
        set.add(pan2);
        set.add(pan3);
        set.setVisible(true);

        game.setTurn(game.players.get(0));
        indit();
    }

    /**
     * Paraméteres konstruktor, ami a betöltést hívja meg a megfelelő paraméterekkel
     * @param map - MapFrame objektum
     * @param game - GameLogic objektum
     */
    public Game(MapFrame map, GameLogic game) {
        loadGame(map, game);
    }

    /**
     * Betölti a játékot a kapott paraméterek segítségével
     * Beállítja az ablakot és elindítja a játékot
     * @param mf - a játék megjelenítése
     * @param gl - a játék logikája
     */
    public void loadGame(MapFrame mf, GameLogic gl){
        map=mf;
        game=gl;
        map.setSize(1500, 1500);
        map.add(map.Menu(map.table, game), BorderLayout.NORTH);
        map.setVisible(true);
        indit();
    }


    /**
     * A játékot indító metódus
     * Hozzáadja a logikai mezőkhöz a megjelenítést, illetve
     * a játékot működtető gomb funkciókat
     * A field szövegmező szövegét mindig az aktuális játékos nevére állítja
     */
    public void indit(){
        for(int i = 0; i< map.table.getRows(); i++){
            for(int j = 0; j< map.table.getColumns(); j++){
                if(map.table.getButton().get(i).get(j)!=null){
                    HexagonButton butt=map.getButton().get(map.getPalya().getButton().get(i).get(j));
                    butt.addActionListener(new PlayerListener(butt));
                }
            }
        }
        field=new JLabel();
        map.add(field, BorderLayout.EAST);
        field.setVisible(true);
        field.setText(game.players.get(0).getName() + " lépése jön");

        vege=new JButton("Játék vége ... Kilépés");
        vege.setPreferredSize(new Dimension(200, 50));
        map.add(vege,BorderLayout.WEST);
        vege.setVisible(false);
        vege.addActionListener(e -> {
            game.end("2 Players", map.table, game);
            map.setVisible(false);
        });
    }
    /**
     * A táblát alkotó gombok listener osztálya
     * paraméterként egy gombot tárol, amire meghívjuk a függvényt
     */
    public class PlayerListener implements ActionListener {
        HexagonButton tile;

        /**
         * Meghívja a lépést végző step() függvényt a kattintott gomb és a soron lévő játékossal
         * Amennyiben a lépés megtehető elvégzia szükségen változtatásokat a megjelenítésben
         * A szöveget átállítja a következő játékos nevére
         * Ugyanezt megcsinálja a másik játékossal
         * ellneőrzi, hogy vége van-e a játéknak.
         * Ha igen, megjeleníti a végét jelző és működtető gombot
         * @param tile - gomb
         */
        PlayerListener(HexagonButton tile){this.tile=tile;}

        @Override
        public void actionPerformed(ActionEvent e) {
            Coordinate coord = game.step(new Coordinate(tile.getRow(), tile.getCol()), game.players.get(0));
            if(coord!=null){
                ImageIcon image=game.players.get(0).getImage();
                map.getButton().get(map.getPalya().getButton().get(coord.getX()).get(coord.getY())).setIcon(image);
                field.setText(game.getTurn().getName() +" lépése jön");
            }else{
                Coordinate cord = game.step(new Coordinate(tile.getRow(), tile.getCol()), game.players.get(1));
                if(cord!=null){
                    ImageIcon image=game.players.get(1).getImage();
                    map.getButton().get(map.getPalya().getButton().get(cord.getX()).get(cord.getY())).setIcon(image);
                    field.setText(game.players.get(0).getName() +" lépése jön");
                }
            }
            if(game.getNyert()) {
                vege.setVisible(true);
                game.nextPlayer();
            }
            if(game.getVesztett()){
                vege.setVisible(true);
            }
        }
    }
}
