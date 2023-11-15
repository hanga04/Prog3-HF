import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HexagonButton extends JButton {
    private static final long serialVersionUID = 1L;
    private static final int SIDES = 6;
    private static final int SIDE_LENGTH = 50;
    public static final int LENGTH = 95;
    public static final int WIDTH = 105;
    private int row = 0;
    private int col = 0;
    public Color color=Color.BLACK;

    public HexagonButton(int r, int c) throws IOException {
        super(String.valueOf(r +":" + c));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(true);
        setPreferredSize(new Dimension(WIDTH, LENGTH));
        //setBackground(Color.GRAY);
        row=r;
        col=c;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.setColor(color);
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

}
