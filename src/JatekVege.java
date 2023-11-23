import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class JatekVege {
    JFrame frame;
    String jatekmod;
    JatekVege(String s){
        jatekmod=s;
        frame=new JFrame("Játék vége");

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(750,500);
        frame.setResizable(true);

        JPanel gombok = new JPanel();
        gombok.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 150));

        JButton new_game = new JButton("Új játék");
        new_game.setPreferredSize(new Dimension(100,50));
        new_game.addActionListener(new ButtonListener(new_game));
        JButton kilepes = new JButton("Kilépés");
        kilepes.setPreferredSize(new Dimension(100,50));
        kilepes.addActionListener(new ButtonListener(kilepes));
        JButton mentes = new JButton("Mentés");
        mentes.setPreferredSize(new Dimension(100,50));
        mentes.addActionListener(new ButtonListener(mentes));

        gombok.add(new_game);
        gombok.add(kilepes);
        gombok.add(mentes);

        JPanel szoveg=new JPanel();
        JLabel kiiras=new JLabel("Játék vége ... Győztes neve:");
        szoveg.add(kiiras);
        frame.add(gombok, BorderLayout.CENTER);
        frame.add(szoveg, BorderLayout.NORTH);
        frame.setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        JButton button;
        ButtonListener(JButton button){this.button=button; }
        @Override
        public void actionPerformed(ActionEvent e) {
            switch(button.getText()){
                case "Új játék": uj_jatek(jatekmod);
                frame.setVisible(false);
                break;
                case "Kilépés" : frame.setVisible(false);
                break;
                //case "Mentés" : ment();
                //break;
            }
        }
    }

    public void uj_jatek(String s){
        Game game;
        Demo demo;
        Single single;
        switch (s) {
            case "Demo" -> {
                try {
                    demo = new Demo();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                demo.setVisible(true);
            }
            case "Single" -> {
                try {
                    single = new Single();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                single.setVisible(true);
            }
            case "Game" -> {
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
