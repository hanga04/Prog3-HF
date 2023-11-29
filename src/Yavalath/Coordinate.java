package Yavalath;

import java.io.Serializable;

/**
 * Koordinátákat tartalmazó osztály
 */
public class Coordinate implements Serializable {
    /**
     * A koordináta vízszintes és függőleges irányú értékeit tárolja
     */
    private int x;
    private int y;

    /**
     * Beállítja a megadott paramtéreknek megfelelően
     * @param i
     * @param j
     */
    public Coordinate(int i, int j){
        x=i;
        y=j;
    }

    /**
     * visszatér a vízszintes tengelyen felvett értékkel
     * @return
     */
    public int getX(){
        return x;
    }

    /**
     * visszatér a függőleges tengelyen felvett értékkel
     * @return
     */
    public int getY(){
        return y;
    }
}
