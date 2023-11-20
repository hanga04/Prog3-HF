import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MyFrame extends JFrame {

    Game game=new Game();
    Demo demo=new Demo();

    Single single=new Single();
    private final JButton bemutato=new JButton("Demo");
    private final JButton gep_ellen=new JButton("1 Player");
    private final JButton jatek=new JButton("2 Players");
    private final JButton betolt=new JButton("Load game");




    MyFrame() throws IOException {
        super("Yavalath");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750,500);
        setResizable(true);

        JPanel panel=new JPanel();


        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 150));

        bemutato.setPreferredSize(new Dimension(100,50));
        gep_ellen.setPreferredSize(new Dimension(100,50));
        jatek.setPreferredSize(new Dimension(100,50));
        betolt.setPreferredSize(new Dimension(100,50));

        panel.add(bemutato);
        bemutato.addActionListener(new DemoListener(bemutato));
        panel.add(gep_ellen);
        gep_ellen.addActionListener(new SingleListener(gep_ellen));
        panel.add(jatek);
        jatek.addActionListener(new GameListener(jatek));
        panel.add(betolt);
        add(panel, BorderLayout.CENTER);

    }

    public class MyButtonListener implements ActionListener {
        JButton button;

        MyButtonListener(JButton button){this.button=button; }

        @Override
        public void actionPerformed(ActionEvent e) {
            game.setVisible(true);
            setVisible(false);
        }
    }

    public class DemoListener implements ActionListener {
        JButton button;

        DemoListener(JButton button){this.button=button; }

        @Override
        public void actionPerformed(ActionEvent e) {
            demo.setVisible(true);
            game.setVisible(false);
            single.setVisible(false);
        }
    }

    public class SingleListener implements ActionListener {
        JButton button;

        SingleListener(JButton button){this.button=button; }

        @Override
        public void actionPerformed(ActionEvent e) {
            demo.setVisible(false);
            game.setVisible(false);
            single.setVisible(true);
        }
    }

    public class GameListener implements ActionListener {
        JButton button;

        GameListener(JButton button){this.button=button; }

        @Override
        public void actionPerformed(ActionEvent e) {
            demo.setVisible(false);
            game.setVisible(true);
            single.setVisible(false);
        }
    }
}
