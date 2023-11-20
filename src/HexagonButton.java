import javax.swing.*;
import java.io.IOException;

public class HexagonButton extends JButton {
    private static final long serialVersionUID = 1L;
    ImageIcon yourImage = new ImageIcon("C:\\Users\\Hanga\\ProgHF\\alap.png");
    private int row = 0;
    private int col = 0;
    private boolean used=false;


    public HexagonButton(int r, int c) throws IOException {
        //super(String.valueOf(r +":" + c));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setIcon(yourImage);

        row=r;
        col=c;
    }

  /*@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);

        Polygon hex=new Polygon();
        for(int i=0; i<SIDES; i++){
            hex.addPoint((int) (50+SIDE_LENGTH * Math.cos((i * 2 * Math.PI / SIDES)-33)),
                    (int) (50+SIDE_LENGTH * Math.sin((i * 2 * Math.PI / SIDES)-33)));
        }

        g.drawPolygon(hex);
    }
    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public ImageIcon getImage(){
        return yourImage;
    }*/

    public boolean isUsed(){
        return used;
    }

    public void setUsed(boolean b){
        used=b;
    }
}
