import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;

public class Menu extends JFrame {
    

    Menu() throws IOException {
        super("Yavalath");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750,500);
        setResizable(true);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 150));

        JButton bemutato = new JButton("Demo");
        bemutato.setPreferredSize(new Dimension(100,50));
        JButton gep_ellen = new JButton("1 Player");
        gep_ellen.setPreferredSize(new Dimension(100,50));
        JButton jatek = new JButton("2 Players");
        jatek.setPreferredSize(new Dimension(100,50));
        JButton betolt = new JButton("Load game");
        betolt.setPreferredSize(new Dimension(100,50));
        JButton kilep = new JButton("Close game");
        kilep.setPreferredSize(new Dimension(100,50));


        panel.add(bemutato);
        bemutato.addActionListener(new ButtonListener(bemutato));
        panel.add(gep_ellen);
        gep_ellen.addActionListener(new ButtonListener(gep_ellen));
        panel.add(jatek);
        jatek.addActionListener(new ButtonListener(jatek));
        panel.add(betolt);
        panel.add(kilep);
        kilep.addActionListener(new CloseListener());
        add(panel, BorderLayout.CENTER);
    }

    public static class ButtonListener implements ActionListener {
        JButton button;

        ButtonListener(JButton button){this.button=button; }

        @Override
        public void actionPerformed(ActionEvent e) {
            Game game;
            Demo demo;
            Single single;
            switch (button.getText()) {
                case "Demo" -> {
                    try {
                        demo = new Demo();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    demo.setVisible(true);
                }
                case "1 Player" -> {
                    try {
                        single = new Single();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    single.setVisible(true);
                }
                case "2 Players" -> {
                    try {
                        game = new Game();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    game.setVisible(true);
                }
            }
        }
    }

    public static class CloseListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
