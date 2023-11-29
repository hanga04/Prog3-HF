package Yavalath;

import javax.swing.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;

/**
 * A játék megjelenítését megvalósító osztály
 */
public class MapFrame extends JFrame implements Serializable {
    /**
     * Eltárolja a logikai és grafikai mező párokat
     */
    HashMap<Hexagon, HexagonButton> button = new HashMap<>();
    /**
     * Eltárolja a megjelenítendő pályát
     */
    Table table;

    /**
     * 1 paraméteres konstruktor
     * beállítja az ablak címét a paraméterként kapott string segítségével
     * létrehoz egy új pályát
     * meghívja az epit() metódust, ami kiépíti a pályát
     * @param s - az ablak címe (játékmód)
     * @throws IOException
     */
    MapFrame(String s) throws IOException {
        super(s + " mode");
        table =new Table();
        epit();
    }

    /**
     * 2 paramtéeres konstruktor
     * hasonló az 1 paramétereshez, de itt a pályát a paraméterként kapott pályának állítja be
      * @param s - ablak cím
     * @param p - pálya
     * @throws IOException
     */
    MapFrame(String s, Table p) throws IOException {
        super(s+"mode");
        table =p;
        epit();
    }

    /**
     * Felépíti a pályát grafikusan
     * a pályában lévő hexagonokat tartalmazó listának minden eleméhez társít egy
     * HexagonButton objektumot és azt a megfelelő helyen és színnel kirajzolja
     * @throws IOException
     */
    public void epit() throws IOException {
        int x = 439;
        int offsetY = 0;

        for (int i = 0; i < table.getRows(); i++) {
            for (int j = 0; j < table.getColumns(); j++) {
                if (i + j < 4 || i + j > 12) {
                    button.put(null, null);
                } else {
                    Hexagon hex= table.getButton().get(i).get(j);
                    button.put(hex, new HexagonButton(x, offsetY, 95, 110, i, j));
                    button.get(hex).setIcon(hex.getIcon());
                    add(button.get(hex));
                    x += 94;
                }
            }
            offsetY += 81;
            if (i < 4) {
                x = 390 - (i * 47);
            } else {
                x = 390 + (i - 6) * 47;
            }
        }
    }

    /**
     * Egy menüt megvalósító függvény
     * Létrehozza a menüt
     * majd a gombokhoz funkciókat ad
     * @param p - a pálya aminek a módosítását a funkciók végzik
     * @param game - a gamelogic objektum amit a funkciók változtatnak
     * @return
     */
    public JMenuBar Menu(Table p, GameLogic game){
        JMenuBar menu=new JMenuBar();
        JMenu jtek=new JMenu("játék");

        JMenuItem undo=new JMenuItem("visszavonás");
        JMenuItem kilep=new JMenuItem("kilépés");
        JMenuItem ment=new JMenuItem("mentés");

        kilep.addActionListener(e-> setVisible(false));
        undo.addActionListener(e -> {
            if(p.lastidx.equals(new Coordinate(0,0))) {
                return;
            }else {
                button.get(p.getLastHex()).setIcon(new ImageIcon("C:\\Users\\Hanga\\Yavalath\\alap.png"));
                p.getLastHex().setSzin(Colors.BASIC);
                p.getLastHex().setIcon(new ImageIcon("C:\\Users\\Hanga\\Yavalath\\alap.png"));
                game.nextPlayer();

            }
        });
        ment.addActionListener(e -> {
            p.save();
            game.save();
        });

        jtek.add(undo);
        jtek.add(kilep);
        jtek.add(ment);

        menu.add(jtek);
        return menu;
    }

    /**
     * visszatér a tárolt pálya objektummal
     * @return
     */
    public Table getPalya(){
        return table;
    }

    /**
     * Visszaadja a hexagonbutton-hexagon párokat
     * @return
     */
    public HashMap<Hexagon, HexagonButton> getButton(){
        return button;
    }
}
