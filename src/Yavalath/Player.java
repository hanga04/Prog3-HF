package Yavalath;

import javax.swing.*;
import java.io.Serializable;

/**
 * Játékos adatait eltároló osztály
 */
public class Player implements Serializable {
    /**
     * Eltárolja a képet amik az ő mezőin jelennek meg
     */
    private ImageIcon image;
    /**
     * Játékos neve
     */
    private String name;
    /**
     * Játékos színe
     */
    private Colors color;

    /**
     * Default konstruktor, a nevet üresen hagyja, a színt és a mezőt pirosra állítja
     */
    public Player(){
        name="";
        color= Colors.RED;
        image=new ImageIcon("C:\\Users\\Hanga\\Yavalath\\piros.png");
    }


    /**
     * Konstruktor
     * Beállítja a nevét a megadott névre
     * a színt a megadott színre és a képet
     * a megadott szín alapján a megfelelő színű képre
     */
    public Player(String name, String szin){
        this.name=name;
        switch (szin) {
            case "piros" -> {
                image = new ImageIcon("C:\\Users\\Hanga\\Yavalath\\piros.png");
                color = Colors.RED;
            }
            case "zöld" -> {
                image = new ImageIcon("C:\\Users\\Hanga\\Yavalath\\zöld.png");
                color = Colors.GREEN;
            }
            case "kék" -> {
                image = new ImageIcon("C:\\Users\\Hanga\\Yavalath\\kék.png");
                color = Colors.BLUE;
            }
        }
    }

    /**
     * visszaadja a játékos nevét
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     * Beállítja a játékos nevét
     * @param name - név, amit beállít
     */
    public void setName(String name){this.name=name;}

    /**
     * visszaadja a játékos képét
     * @return
     */
    public ImageIcon getImage(){
        return image;
    }

    /**
     * visszaadja a játékos színét
     * @return
     */
    public Colors getColor(){
        return color;
    }

    /**
     * Beállítja a játékos színét
     * @param f - szín
     */
    public void setColor(Colors f){color=f;}

    /**
     * Beállítja a játékos képét
     * @param ic - kép
     */
    public void setImage(ImageIcon ic){image=ic;}

}
