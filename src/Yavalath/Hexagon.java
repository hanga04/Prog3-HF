package Yavalath;

import javax.swing.*;
import java.io.IOException;
import java.io.Serializable;

/**
 * A pályát alkotó mezők logikai megvalósítása
 */
public class Hexagon implements Serializable {

    /**
     * Eltárolja, hogy melyik játékosé az adott mező
     * Ha még senkié BASIC színű
     */
    private Colors szin= Colors.BASIC;
    /**
     * Eltárolja hogy a mezőt milyen képpel kell kitölteni
     */
    ImageIcon yourImage;
    /**
     * Eltárolja az adott Yavalath.Hexagon indexeit
     */
    private int row = 0;
    private int col = 0;


    /**
     * Létrehoz egy Yavalath.Hexagon objektumot az adott indexű helyen
     * @param r - sor
     * @param c - oszlop
     * @throws IOException
     */
    public Hexagon(int r, int c) throws IOException {
        row=r;
        col=c;
        yourImage = new ImageIcon("C:\\Users\\Hanga\\Yavalath\\alap.png");
    }

    /**
     * visszadja az adott hexagon sorát
     * @return
     */
    public int getRow(){
        return row;
    }

    /**
     * visszaadja az adott hexagon oszlopát
     * @return
     */
    public int getCol(){
        return col;
    }

    /**
     * visszaadja a hexagon színát
     * @return
     */
    public Colors getSzin(){
        return szin;
    }

    /**
     * beállítja a hexagon színét
     * @param colors
     */
    public void setSzin(Colors colors){
        szin= colors;
    }

    /**
     * beállítja a hexagon képét
     */
    public ImageIcon getIcon() {
        return yourImage;
    }

    /**
     * Beállítja a mező képét
     * @param image - kép
     */
    public void setIcon(ImageIcon image){yourImage=image;}
}
