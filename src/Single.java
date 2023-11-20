import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class Single extends JFrame{
        private static final int ROWS = 9;
        private static final int COLUMNS = 9;
        private HexagonButton[][] button=new HexagonButton[ROWS][COLUMNS];
        JButton gep;
        static int lepesszam=0;



        Single() throws IOException {
            super("HexagonFrame");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(1500,1500);
            // setLocation(new Point(500, 0));

            gep = new JButton("Computer's turn");
            //setLayout(new GridLayout());
            gep.setPreferredSize(new Dimension(100, 50));
            add(gep, BorderLayout.EAST);
            gep.setLocation(new Point(500, 200));
            gep.setVisible(false);
            gep.addActionListener(new GepListener());

            int x = 439;
            int offsetY =50;

            for(int i=0; i<ROWS; i++){
                for(int j=0; j<COLUMNS; j++){
                    if(i+j<4 || i+j > 12) {
                        button[i][j]=null;
                    } else {
                        button[i][j] = new HexagonButton(i, j);
                        add(button[i][j]);
                        button[i][j].setBounds(x, offsetY, 95, 110);
                        button[i][j].addActionListener(new MeListener(button[i][j]));
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

        public class MeListener implements ActionListener{
            HexagonButton tile;


            MeListener(HexagonButton tile)  {this.tile=tile;}

            @Override
            public void actionPerformed(ActionEvent e) {
                if(tile.isUsed() || lepesszam%2!=0 ) return;
                tile.setIcon(new ImageIcon("C:\\Users\\Hanga\\ProgHF\\piros.png"));
                tile.setUsed(true);
                gep.setVisible(true);
                lepesszam++;
            }
        }

        public class GepListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                lep(new ImageIcon("C:\\Users\\Hanga\\ProgHF\\zöld.png"));
                lepesszam++;
            }
            public void lep(ImageIcon icon) {
                Random rand = new Random();
                int i = rand.nextInt(9);
                int j = rand.nextInt(9);

                while (button[i][j] == null || button[i][j].isUsed()) {
                    i = rand.nextInt(8);
                    j = rand.nextInt(8);
                }
                button[i][j].setIcon(icon);
                button[i][j].setUsed(true);
                gep.setVisible(false);

            }
        }
    }
