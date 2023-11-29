package Yavalath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

/**
 * Ez az osztály működteti az egyszemélyes játékot
 */
public class Single implements Serializable {

    /**
     * Gomb ami a gép lépését irányítja
     */
    private JButton gep;
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
     * A játékos nevének és színének beállításához egy új ablakot hoz létre,
     * az ott kapott értékeket állítja be a játékos tulajdonságainak
     * Meghívja a játékot indító függvényt
     * @throws IOException
     */
    public Single() throws IOException {
        map=new MapFrame("1 Player");
        map.setSize(1500, 1500);
        game=new GameLogic(map.table, "1 Player");
        map.add(map.Menu(map.table, game), BorderLayout.NORTH);
        map.setVisible(true);

        game.players.add(0, new Player());
        game.players.add(1, new Player("Gép", "zöld"));
        game.setTurn(game.players.get(0));

        JFrame set=new JFrame();
        set.setSize(750, 500);
        JPanel pan=new JPanel();
        JPanel pn2=new JPanel();
        JComboBox<String> combo=new JComboBox<>(new String[]{"Piros", "Kék"});
        pan.setLayout(new FlowLayout());
        pn2.setLayout(new FlowLayout());
        JButton gomb=new JButton("mehet");
        JTextField nev=new JTextField(20);
        pan.add(new JLabel("Játékos neve"));
        pan.add(nev);
        pan.add(combo);
        pn2.add(gomb);
        gomb.addActionListener(e -> {
            game.players.get(0).setName(nev.getText());
            String szin=(String) combo.getSelectedItem();
            if (szin.equals("Piros")) {
                game.players.get(0).setColor(Colors.RED);
                game.players.get(0).setImage(new ImageIcon("C:\\Users\\Hanga\\Yavalath\\piros.png"));
            } else if (szin.equals("Kék")) {
                game.players.get(0).setColor(Colors.BLUE);
                game.players.get(0).setImage(new ImageIcon("C:\\Users\\Hanga\\Yavalath\\kék.png"));

            }
            set.setVisible(false);
            map.setVisible(true);
        });
        set.setLayout(new GridLayout(2, 0));
        set.add(pan);
        set.add(pn2);
        set.setVisible(true);

        indit();
    }

    /**
     * Paraméteres konstruktor, ami a betöltést hívja meg a megfelelő paraméterekkel
     * @param map - MapFrame objektum
     * @param game - GameLogic objektum
     */
    public Single(MapFrame map, GameLogic game){
        loadSingle(map, game);
    }


    /**
     * Betölti a játékot a kapott paraméterek segítségével
     * Beállítja az ablakot és elindítja a játékot
     * @param map - a játék megjelenítése
     * @param game - a játék logikája
     */
    public void loadSingle(MapFrame map, GameLogic game){
        this.map=map;
        this.game=game;
        map.setSize(1500, 1500);
        map.add(map.Menu(map.table, game), BorderLayout.NORTH);
        map.setVisible(true);
        indit();
    }

    /**
     * A játékot indító metódus
     * Hozzáadja a logikai mezőkhöz a megjelenítést, illetve
     * a játékot működtető gomb funkciókat
     */
    public void indit() {
        for (int i = 0; i < map.getPalya().getRows(); i++) {
            for (int j = 0; j < map.getPalya().getColumns(); j++) {
                if (map.getPalya().getButton().get(i).get(j) != null) {
                    HexagonButton butt=map.getButton().get(map.getPalya().getButton().get(i).get(j));
                    butt.addActionListener(new MeListener(butt));
                }
            }
        }
        gep = new JButton("Gép lépése");
        gep.setPreferredSize(new Dimension(150, 50));
        map.add(gep, BorderLayout.EAST);
        gep.setVisible(false);
        gep.addActionListener(new GepListener());

        vege=new JButton("Játék vége ... Kilépés");
        vege.setPreferredSize(new Dimension(200,50));
        map.add(vege, BorderLayout.WEST);
        vege.setVisible(false);
        vege.addActionListener(e -> {
            game.end("1 Player", map.table, game);
            map.setVisible(false);
        });
    }

    /**
     * A táblát alkotó gombot listener osztálya
     * paraméterként egy gombot tárol, amire meghívjuk a függvényt
     */
    public class MeListener implements ActionListener {
        HexagonButton tile;

        /**
         * Konstruktor
         *
         * @param tile - a gomb, amire meghívjuk a listenert
         */
        MeListener(HexagonButton tile) {
            this.tile = tile;
        }
        /**
         * Meghíja a step() függvényt a kattintott gomb és a soron lévő játékos paraméterekkel
         * Ha a lépés megtehető az adott mezőnek a színét  és képét átállítja a megfelelő színűre,
         * A gép lépését indító gombot láthatóvá teszi.
         * Ellenőrzi, hogy vége van-e a játéknak, ha igen, láthatóvá teszi a játék végét jelző gombot
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Coordinate coord = game.step(new Coordinate(tile.getRow(), tile.getCol()), game.players.get(0));
            if(coord!=null){
                ImageIcon image=game.players.get(0).getImage();
                map.getButton().get(map.getPalya().getButton().get(coord.getX()).get(coord.getY())).setIcon(image);
                gep.setText("Gép lépése");
                gep.setVisible(true);
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

    /**
     * A gép lépését működtető gomb listenerje
     */
    public class GepListener implements ActionListener {
        /**
         * A gomb nyomására meghívja a step() függvényt (0,0) koordinátákkal és a játékossal
         * Ha a lépés megtehető beállítja a színt és a mezőt a megfelelő színűre,
         * ellenőrzi, hogy a játéknak vége van-e
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            Coordinate cord=game.step(new Coordinate(0,0), game.players.get(1));
            if(cord!=null){
                ImageIcon image=game.players.get(1).getImage();
                map.getButton().get(map.getPalya().getButton().get(cord.getX()).get(cord.getY())).setIcon(image);
                gep.setText(game.getTurn().getName() + " lépése");

                if(game.getNyert()) {
                    vege.setVisible(true);
                }
                if(game.getVesztett()){
                    vege.setVisible(true);
                }
            }
        }
    }
}
