package Yavalath;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;

/**
 * Ez az osztály működteti a 0 személyes játékot/bemutató játékot
 */
public class Demo implements Serializable {
    /**
     * Gomb ami a gépek lépését irányítja
     */
    private JButton step;
    /**
     * Gomb, ami a játék végét jelzi és meghívja
     */
    private JButton vege;
    /**
     * MapFrame objektum, ami a megjelenítést végzi
     */
    private  MapFrame map;
    /**
     * GameLogic objektum, ami a játék logikáját adja
     */
    private GameLogic game;


    /**
     * Létrehozza a MapFrame objektumot, beállítja a megfelelő megjelenítést
     * Létrehozza a GameLogic objektumot, beállítja a játékosokat
     * Meghívja a játékot indító függvényt
     * @throws IOException
     */
    public Demo() throws IOException {
        map=new MapFrame("Demo");
        map.setSize(1500, 1500);
        game=new GameLogic(map.table, "Demo");
        map.add(map.Menu(map.table, game), BorderLayout.NORTH);
        map.setVisible(true);
        indit();

        game.players.add(0, new Player("Gép1", "piros"));
        game.players.add(1, new Player("Gép2", "zöld"));
        game.setTurn(game.players.get(0));
    }

    /**
     * Paraméteres konstruktor, ami a betöltést hívja meg a megfelelő paraméterekkel
     * @param map - MapFrame objektum
     * @param game - GameLogic objektum
     */
    public Demo(MapFrame map, GameLogic game){
        loadDemo(map, game);
    }

    /**
     * Betölti a játékot a kapott paraméterek segítségével
     * Beállítja az ablakot és elindítja a játékot
     * @param mf - a játék megjelenítése
     * @param gl - a játék logikája
     */
    public void loadDemo(MapFrame mf, GameLogic gl){
        this.map=mf;
        this.game=gl;
        map.setSize(1500, 1500);
        map.add(map.Menu(map.table, game), BorderLayout.NORTH);
        map.setVisible(true);
        indit();
    }

    /**
     * A játékot indító metódus
     * Hozzáadja a játékot működtető gombokat és funkcióikat
     */
    public void indit(){
        step = new JButton("Next step");
        map.setLayout(new BorderLayout());
        step.setPreferredSize(new Dimension(100, 50));
        map.add(step, BorderLayout.EAST);
        step.addActionListener(new StepListener());

        vege=new JButton("Játék vége ... Kilépés");
        vege.setPreferredSize(new Dimension(200,50));
        map.add(vege, BorderLayout.WEST);
        vege.setVisible(false);
        vege.addActionListener(e -> {
            game.end("Demo", map.table, game);
            map.setVisible(false);
        });

    }
    /**
     * A gép lépését működtető gomb listenerje
     */
    public class StepListener implements ActionListener {

        /**
         * A gomb nyomására meghívja a step() függvényt (0,0) koordinátákkal és a játékossal
         * Ha a lépés megtehető beállítja a színt és a mezőt a megfelelő színűre.
         * Ezután ugyanezt elvégzi a másik játékossal is
         * ellenőrzi, hogy a játéknak vége van-e
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            Coordinate cord=game.step(new Coordinate(0,0), game.players.get(0));
            if(cord!=null){
                ImageIcon image=game.players.get(0).getImage();
                map.getButton().get(map.getPalya().getButton().get(cord.getX()).get(cord.getY())).setIcon(image);
            }else{
                Coordinate coord=game.step(new Coordinate(0,0), game.players.get(1));
                if(coord!=null){
                    ImageIcon image=game.players.get(1).getImage();
                    map.getButton().get(map.getPalya().getButton().get(coord.getX()).get(coord.getY())).setIcon(image);
                }
            }

            if(game.getNyert()) {
                vege.setVisible(true);
                step.setVisible(false);
                game.nextPlayer();
            }
            if(game.getVesztett()){
                vege.setVisible(true);
                step.setVisible(false);
            }
        }
    }
}
