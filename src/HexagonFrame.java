import javax.swing.*;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class HexagonFrame extends JFrame {
    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    public HexagonButton[][] button=new HexagonButton[ROWS][COLUMNS];



    HexagonFrame() throws IOException {
        super("HexagonFrame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1500,1500);

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
}