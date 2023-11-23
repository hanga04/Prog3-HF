import javax.swing.*;
import java.io.IOException;

public class HexagonButton extends JButton {
    private static final long serialVersionUID = 1L;
    private Field szin=Field.BASIC;
    ImageIcon yourImage = new ImageIcon("C:\\Users\\Hanga\\ProgHF\\alap.png");
    private int row = 0;
    private int col = 0;
    private boolean used=false;


    public HexagonButton(int r, int c) throws IOException {
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setIcon(yourImage);

        row=r;
        col=c;
    }
    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public boolean isUsed(){
        return used;
    }

    public void setUsed(boolean b){
        used=b;
    }

    public Field getSzin(){
        return szin;
    }

    public void setSzin(Field field){
        szin=field;
    }
}
