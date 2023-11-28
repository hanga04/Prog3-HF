package Yavalath;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * A játéktábla logikai megvalósítása
 */
public class Table implements Serializable {
    /**
     * eltároljuk a tábla méretét
     */
        private static final int ROWS = 9;
        private static final int COLUMNS = 9;

    /**
     * Ebben tároljuk a tábla mezőit
     */
    protected ArrayList<ArrayList<Hexagon>> button;
    /**
     * A visszalépéshez eltároljuk, melyik mező lett megutóbb módosítva
     */
        Coordinate lastidx;

    /**
     * Pálya konstruktora, létrehozzuk a listát és megtöltjük a
     * mezőkkel, megfelelően indexelve
     * @throws IOException
     */
    public Table() throws IOException {
            button=new ArrayList<>(81);
            lastidx=new Coordinate(0,0);

            for(int i=0; i<ROWS; i++){
                button.add(new ArrayList<>());
                for(int j=0; j<COLUMNS; j++){
                    if(i+j<4 || i+j > 12) {
                        button.get(i).add(j, null);
                    } else {
                        button.get(i).add(j, new Hexagon(i, j));

                    }
                }
            }
        }

    /**
     * visszaadja a pálya sorainak számát
     * @return
     */
        public int getRows(){
            return ROWS;
        }

    /**
     * visszaadja a tábla oszlopainak számát
     * @return
     */
        public int getColumns(){
            return COLUMNS;
        }

    /**
     * Visszaadja a pályát tartalmazó listát
     * @return
     */
        public ArrayList<ArrayList<Hexagon>> getButton(){
            return button;
        }

    /**
     * beállítja a lastidx értékét
     * @param coord
     */
        public void setLastidx(Coordinate coord){
            lastidx=coord;
        }

    /**
     * visszaadja a legutóbb változtatott Yavalath.Hexagon objektumot
     * @return
     */
    public Hexagon getLastHex(){
            return button.get(lastidx.getX()).get(lastidx.getY());
        }

    /**
     * fájlba menti az aktuális pályát
     */
    public void save() {
        try {
            FileOutputStream fos = new FileOutputStream("palya");
            ObjectOutputStream ous = new ObjectOutputStream(fos);
            ous.writeObject(this);
            ous.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

}
