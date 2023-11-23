import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Demo extends HexagonFrame {
    private JButton step;
    private JButton vege;



    Demo() throws IOException {
        super("Demo");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1500, 1500);
        setLepesszam(0);
        indit();
    }

    public void indit(){
        step = new JButton("Next step");
        setLayout(new BorderLayout());
        step.setPreferredSize(new Dimension(100, 50));
        add(step, BorderLayout.EAST);
        step.addActionListener(new StepListener());

       vege=new JButton("Játék vége ... Kilépés");
        vege.setPreferredSize(new Dimension(200,50));
        add(vege, BorderLayout.WEST);
        vege.setVisible(false);
        vege.addActionListener(new EndListener("Demo"));

    }
    public class StepListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

                if (getLepesszam() % 2 == 0) {
                    AIlep(new ImageIcon("C:\\Users\\Hanga\\ProgHF\\piros.png"), Field.RED);
                    setLepesszam(getLepesszam()+1);
                } else {
                    AIlep(new ImageIcon("C:\\Users\\Hanga\\ProgHF\\zöld.png"), Field.GREEN);
                    setLepesszam(getLepesszam()+1);
               // }
                }
                if(getNyert()) {
                    vege.setVisible(true);
                    step.setVisible(false);
                }
                if(getVesztett()){
                    vege.setVisible(true);
                    step.setVisible(false);
                }
        }
    }
}




