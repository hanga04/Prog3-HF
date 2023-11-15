import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class HexagonFrame extends JFrame {
    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private HexagonButton[][] button= new HexagonButton[ROWS][COLUMNS];
    private JTextField szoveg=new JTextField(20);
    private JPanel panel=new JPanel();


    HexagonFrame() throws IOException {
        super("HexagonFrame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1050,750);
       // setLocation(new Point(500, 0));

        int x = -10+400;
        int offsetY = 0;

        for(int i=0; i<ROWS; i++){
            if(i==0){
                int offsetX=x;
                for(int j=4; j<COLUMNS; j++){
                    button[i][j]=new HexagonButton(i,j);
                    add(button[i][j]);
                    button[i][j].setBounds(offsetX, offsetY, 105, 95);
                    button[i][j].addActionListener(new HexButtonListener(button[i][j]));
                    offsetX += 87;
                }
                offsetY+=76;
            }
            if(i==1){
               int offsetX=x-44;
                for(int j=3; j<COLUMNS; j++){
                    button[i][j]=new HexagonButton(i,j);
                    add(button[i][j]);
                    button[i][j].setBounds(offsetX, offsetY, 105, 95);
                    button[i][j].addActionListener(new HexButtonListener(button[i][j]));
                    offsetX += 87;
                }
                offsetY+=76;
            }
            if(i==2){
                int offsetX=x-88;
                for(int j=2; j<COLUMNS; j++){
                    button[i][j]=new HexagonButton(i,j);
                    add(button[i][j]);
                    button[i][j].setBounds(offsetX, offsetY, 105, 95);
                    button[i][j].addActionListener(new HexButtonListener(button[i][j]));
                    offsetX += 87;
                }
                offsetY+=76;
            }

            if(i==3){
                int offsetX=x-88-44;
                for(int j=1; j<COLUMNS; j++){
                    button[i][j]=new HexagonButton(i,j);
                    add(button[i][j]);
                    button[i][j].setBounds(offsetX, offsetY, 105, 95);
                    button[i][j].addActionListener(new HexButtonListener(button[i][j]));
                    offsetX += 87;
                }
                offsetY+=76;
            }
            if(i==4){
                int offsetX=x-88-88;
                for(int j=0; j<COLUMNS; j++){
                    button[i][j]=new HexagonButton(i,j);
                    add(button[i][j]);
                    button[i][j].setBounds(offsetX, offsetY, 105, 95);
                    button[i][j].addActionListener(new HexButtonListener(button[i][j]));
                    offsetX += 87;
                }
                offsetY+=76;
            }

            if(i==5){
                int offsetX=x-88-44;
                for(int j=0; j<COLUMNS-1; j++){
                    button[i][j]=new HexagonButton(i,j);
                    add(button[i][j]);
                    button[i][j].setBounds(offsetX, offsetY, 105, 95);
                    button[i][j].addActionListener(new HexButtonListener(button[i][j]));
                    offsetX += 87;
                }
                offsetY+=76;
            }
            if(i==6){
                int offsetX=x-88;
                for(int j=0; j<COLUMNS-2; j++){
                    button[i][j]=new HexagonButton(i,j);
                    add(button[i][j]);
                    button[i][j].setBounds(offsetX, offsetY, 105, 95);
                    button[i][j].addActionListener(new HexButtonListener(button[i][j]));
                    offsetX += 87;
                }
                offsetY+=76;
            }

            if(i==7){
                int offsetX=x-44;
                for(int j=0; j<COLUMNS-3; j++){
                    button[i][j]=new HexagonButton(i,j);
                    add(button[i][j]);
                    button[i][j].setBounds(offsetX, offsetY, 105, 95);
                    button[i][j].addActionListener(new HexButtonListener(button[i][j]));
                    offsetX += 87;
                }
                offsetY+=76;
            }

            if(i==8){
                int offsetX=x;
                for(int j=0; j<=COLUMNS-5; j++){
                    button[i][j]=new HexagonButton(i,j);
                    add(button[i][j]);
                    button[i][j].setBounds(offsetX, offsetY, 105, 95);
                    button[i][j].addActionListener(new HexButtonListener(button[i][j]));
                    offsetX += 87;
                }
            }
        }
    }
}
