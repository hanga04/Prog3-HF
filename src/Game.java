import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Game extends JFrame {
    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private HexagonButton[][] button=new HexagonButton[ROWS][COLUMNS];
    static int lepesszam=0;




    Game() throws IOException {
        super("HexagonFrame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1500,1500);
        // setLocation(new Point(500, 0));


        int x = 439;
        int offsetY = 0;

        for(int i=0; i<ROWS; i++){
            for(int j=0; j<COLUMNS; j++){
                if(i+j<4 || i+j > 12) {
                    button[i][j]=null;
                } else {
                    button[i][j] = new HexagonButton(i, j);
                    add(button[i][j]);
                    button[i][j].setBounds(x, offsetY, 95, 110);
                    button[i][j].addActionListener(new PlayerListener(button[i][j]));
                    x+=94;
                }
            }
            offsetY+=81;
            if(i<4) {
                x=390-(i*47);
            }else{
                x=390+(i-6)*47;
            }
        }
    }

    public static class PlayerListener implements ActionListener {
            HexagonButton tile;

            PlayerListener(HexagonButton tile){this.tile=tile;}

        @Override
        public void actionPerformed(ActionEvent e) {
            if(tile.isUsed()) return;
            if(lepesszam%2==0){
                tile.setIcon(new ImageIcon("C:\\Users\\Hanga\\ProgHF\\piros.png"));
            }else{
                tile.setIcon(new ImageIcon("C:\\Users\\Hanga\\ProgHF\\zöld.png"));
            }
            tile.setUsed(true);
            lepesszam++;

        }
    }
}