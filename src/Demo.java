import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class Demo extends JFrame {

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private HexagonButton[][] button = new HexagonButton[ROWS][COLUMNS];
    private JButton step;
    private JButton exit;


    Demo() throws IOException {
        super("Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1500, 1500);
        step = new JButton("Next step");
        setLayout(new BorderLayout());
        step.setPreferredSize(new Dimension(100, 50));
        add(step, BorderLayout.EAST);
        step.addActionListener(new StepListener());

        exit=new JButton("Játék vége ... Kilépés");
        exit.setPreferredSize(new Dimension(100,50));
        add(exit, BorderLayout.NORTH);
        exit.setVisible(false);
        exit.addActionListener(new ExitListener());


        int x = 439;
        int offsetY = 0;

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLUMNS; j++) {
                if (i + j < 4 || i + j > 12) {
                    button[i][j] = null;
                } else {
                    button[i][j] = new HexagonButton(i, j);
                    add(button[i][j]);
                    button[i][j].setBounds(x, offsetY, 95, 110);
                    x += 94;
                }
            }
            offsetY += 81;
            if (i < 4) {
                x = 390 - (i * 47);
            } else {
                x = 390 + (i - 6) * 47;
            }
        }
    }

    public class StepListener implements ActionListener {
        int click = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            if(click == 10) {
                exit.setVisible(true);
                step.setVisible(false);

            }else {
                if (click % 2 == 0) {
                    lep(new ImageIcon("C:\\Users\\Hanga\\ProgHF\\piros.png"));
                    click++;
                } else {
                    lep(new ImageIcon("C:\\Users\\Hanga\\ProgHF\\zöld.png"));
                    click++;
                }
            }
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
        }
    }

    public class ExitListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }
}




