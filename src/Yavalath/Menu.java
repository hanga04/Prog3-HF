package Yavalath;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * Kezdőmenüt megvalósító osztály, ablakot jelenít meg
 */
public class Menu extends JFrame implements Serializable {

    /**
     * Létehozza az ablakot, beállítja a tulajdonságokat
     * Létrehozza a gombokat és a hozzájuk tartozó funkciókat amikkel a különböző
     * játékmódokat el lehet indítani
     * @throws IOException
     */
    Menu() throws IOException {
        super("Yavalath");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750, 500);
        setResizable(true);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 150));

        JButton bemutato = new JButton("Demo");
        bemutato.setPreferredSize(new Dimension(100, 50));
        JButton gep_ellen = new JButton("1 Player");
        gep_ellen.setPreferredSize(new Dimension(100, 50));
        JButton jatek = new JButton("2 Players");
        jatek.setPreferredSize(new Dimension(100, 50));
        JButton betolt = new JButton("Load game");
        betolt.setPreferredSize(new Dimension(100, 50));
        JButton kilep = new JButton("Close game");
        kilep.setPreferredSize(new Dimension(100, 50));


        panel.add(bemutato);
        bemutato.addActionListener(e -> {
            try {
                GameLogic.jatek_inditas("Demo");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(gep_ellen);
        gep_ellen.addActionListener(e -> {
            try {
                GameLogic.jatek_inditas("1 Player");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(jatek);
        jatek.addActionListener(e -> {
            try {
                GameLogic.jatek_inditas("2 Players");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(betolt);
        betolt.addActionListener(e -> {
            try {
                load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(kilep);
        kilep.addActionListener(e -> System.exit(0));
        add(panel, BorderLayout.CENTER);
    }

    /**
     * Fájlból betölti a játékot
     * Beolvas egy Table és egy GameLogic objektumot
     * GameLogicnak beállítja a tábláját a Tablenek
     * a játékmód függvényében létrehoz (betölt) egy játékot a megfelelő paraméterekkel
     * @throws IOException
     */
    public void load() throws IOException {
        Table ujP = null;
        MapFrame ujMF;
        GameLogic ujGL = null;
        try {
            FileInputStream fis2 = new FileInputStream("palya");
            ObjectInputStream ois2 = new ObjectInputStream(fis2);
            ujP = (Table) ois2.readObject();

            FileInputStream fis1 = new FileInputStream("gamelogic");
            ObjectInputStream ois1 = new ObjectInputStream(fis1);
            ujGL = (GameLogic) ois1.readObject();


        } catch (IOException | ClassNotFoundException ioE) {
            System.out.println(ioE);
        }

        assert ujGL != null;
        ujGL.setTable(ujP);
        switch (ujGL.getJatekmod()) {
            case "Demo" -> {
                ujMF = new MapFrame("Demo", ujP);
                Demo demo = new Demo(ujMF, ujGL);
            }
            case "1 Player" -> {
                ujMF = new MapFrame("1 Player", ujP);
                Single single = new Single(ujMF, ujGL);
            }
            case "2 Players" -> {
                ujMF = new MapFrame("2 Players", ujP);
                Game game = new Game(ujMF, ujGL);
            }
        }
    }

    /**
     * A játék vége után megjelenő ablak
     * létrehoz egy új ablakot a megfelelő tulajdonságokkal
     * Hozzáadja a gombokat és a hozzájuk tartozó funkciókat, amik a program további működését segítik
     * @param jatekmod - játékmód, amelyben a játék véget ért
     * @param table - játék végén a tábla állása
     * @param gl - játék végén a játék állása
     */
    public static void jatekVege(String jatekmod, Table table, GameLogic gl){

        JFrame frame=new JFrame("Játék vége");

        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(750,500);
        frame.setResizable(true);

        JPanel gombok = new JPanel();
        gombok.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 150));

        JButton new_game = new JButton("Új játék");
        new_game.setPreferredSize(new Dimension(100,50));
        new_game.addActionListener(e -> {
            try {
                GameLogic.jatek_inditas(jatekmod);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            frame.setVisible(false);
        });
        JButton kilepes = new JButton("Kilépés");
        kilepes.setPreferredSize(new Dimension(100,50));
        kilepes.addActionListener(e -> frame.setVisible(false));
        JButton mentes = new JButton("Mentés");
        mentes.setPreferredSize(new Dimension(100,50));
        mentes.addActionListener(e -> {
            table.save();
            gl.save();
        });

        gombok.add(new_game);
        gombok.add(kilepes);
        gombok.add(mentes);

        JPanel szoveg=new JPanel();
        JLabel kiiras=new JLabel("Játék vége ... Győztes neve: " + gl.getTurn().getName());
        szoveg.add(kiiras);
        frame.add(gombok, BorderLayout.CENTER);
        frame.add(szoveg, BorderLayout.NORTH);
        frame.setVisible(true);
    }

}
