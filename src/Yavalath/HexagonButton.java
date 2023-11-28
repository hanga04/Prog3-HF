package Yavalath;

import javax.swing.*;
import java.io.IOException;
import java.io.Serializable;

/**
 * A grafikus megvalósításhoz szükséges gomb objektum
 */
public class HexagonButton extends JButton implements Serializable {

    //Colors szin= Colors.BASIC;
    /**
     * A gomb elhelyezkedésének paramétere
     */
    private int offsetX;
    /**
     * A gomb elhelyezkedésének paramétere
     */
    private int offsetY;
    /**
     * A gomb elhelyezkedésének paramétere
     */
    private int width;
    /**
     * A gomb elhelyezkedésének paramétere
     */
    private int heigth;
    /**
     * A gomb elhelyezkedésének paramétere
     */
    private int row;
    /**
     * A gomb elhelyezkedésének paramétere
     */
    private int col;
    /**
     * a gomb grafikai megjelnítése
     */
    ImageIcon yourImage = new ImageIcon("C:\\Users\\Hanga\\Yavalath\\alap.png");


    /**
     * Beállítja az objektumot a megfelelő paraméterekkel
     * @param x - vízszintes helyzet
     * @param y - függőleges helyzet
     * @param w - szélesség
     * @param h - magasság
     * @param i - vízszintes index
     * @param j - függőleges index
     * @throws IOException
     */
    public HexagonButton(int x, int y, int w, int h, int i, int j) throws IOException {
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setIcon(yourImage);
        offsetX=x;
        offsetY=y;
        width=w;
        heigth=h;
        setBounds(offsetX, offsetY, width, heigth);
        row=i;
        col=j;
    }

    /**
     * visszaadja a gomb sorát
     * @return - gomb sora
     */
    public int getRow(){
        return row;
    }

    /**
     * visszaadja a gomb oszlopát
     * @return - gomb oszlopa
     */
    public int getCol(){
        return col;
    }


}
