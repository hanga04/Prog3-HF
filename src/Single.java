import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Ez az osztály működteti az egyszemélyes játékot
 * A JFrameből származik, mert egy Frame-t hoz létre
 * Tárolja a tábla méretét (ROWS, COLUMNS), egy gombokat tartalmazó 2D tömböt
 * ami a táblát fogja adni, illetve egy JButton gombot, ami a gép lépéseit teszi lehetővé
 * A lépésszám statikus változó azért szükséges, hogy tudjuk éppen melyik játékos van soron
 */
public class Single extends HexagonFrame {
    private JButton gep;
    private JButton vege;



    /**
     * Létrehozza a táblát, beállítja a frame alap tulajdonságait
     * Hozzáadja a gombot a megfelelő elrendezéssel, majd nem láthatóvá teszi
     *
     * @throws IOException
     */
    Single() throws IOException {
        super("Single");
        setSize(1500, 1500);
        setLepesszam(0);
        indit();
    }

    public void indit() {
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                if (button[i][j] != null) {
                    button[i][j].addActionListener(new MeListener(button[i][j]));
                }
            }
        }
        gep = new JButton("Computer's turn");
        gep.setPreferredSize(new Dimension(100, 50));
        add(gep, BorderLayout.EAST);
        gep.setVisible(false);
        gep.addActionListener(new GepListener());

        vege=new JButton("Játék vége ... Kilépés");
        vege.setPreferredSize(new Dimension(200,50));
        add(vege, BorderLayout.WEST);
        vege.setVisible(false);
        vege.addActionListener(new Demo.EndListener("Single"));
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
         * A játékos lépését hajtja végre, amennyiben az ő köre van
         * Csak olyan gomb nyomásakor reagál, amelyekre még nem lett kattintva
         * Ilyenkor az adott mezőnek a színét átállítja pirosra, a felhasználtságát igazra
         * A gép lépését indító gombot láthatóvá teszi
         * Megnöveli a lépésszámot
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (tile.isUsed() || getLepesszam() % 2 != 0) return;
            tile.setIcon(new ImageIcon("C:\\Users\\Hanga\\ProgHF\\piros.png"));
            tile.setUsed(true);
            gep.setVisible(true);
            tile.setSzin(Field.RED);
            setLepesszam(getLepesszam()+1);
            setLastidx(tile.getRow(), tile.getCol());
            if(winner(tile, 3)) {
                vege.setVisible(true);
            } else{
                if(winner(tile,2)) {
                    vege.setVisible(true);
                }
            }
        }
    }

    /**
     * A gép lépését működtető gomb listenerje
     */
    public class GepListener implements ActionListener {
        /**
         * A gomb nyomására meghívja a lep() függvényt a megfelelő színű képpel
         * lépésszámot növeli eggyel
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(getLepesszam()%2==0)return;
            AIlep(new ImageIcon("C:\\Users\\Hanga\\ProgHF\\zöld.png"), Field.GREEN);
            setLepesszam(getLepesszam()+1);
            if(getNyert()) {
                //kilep.setText("Nyertél");
                //kilep.setVisible(true);
                //gep.setVisible(false);
                JatekVege vege=new JatekVege("Single");
                setVisible(false);
            }
            if(getVesztett()){
                JatekVege vege=new JatekVege("Single");
                setVisible(false);
            }
        }
    }
}
