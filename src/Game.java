import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.*;


public class Game extends HexagonFrame {
    private static JLabel field;
    private JButton vege;



    Game() throws IOException {
        super("Game");
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1500,1500);
        setLepesszam(0);
        indit();

        /*JPanel panel=new JPanel();
        JTextField nev1=new JTextField(20);
        JTextField nev2=new JTextField(20);
        String[] szinek={"piros", "zöld"};
        JComboBox<String> colors=new JComboBox<>(szinek);
        panel.setLayout(new GridLayout(2,1));
        panel.add(nev1);
        panel.add(nev2);
        panel.add(colors);
        add(panel, BorderLayout.NORTH);*/
    }

    public void indit(){
        for(int i=0; i<getRows(); i++){
            for(int j=0; j<getColumns(); j++){
                if(button[i][j]!=null){
                    button[i][j].addActionListener(new PlayerListener(button[i][j]));
                }
            }
        }
        field=new JLabel();
        add(field, BorderLayout.EAST);
        field.setVisible(true);
        field.setText("Első játékos lép");

        vege=new JButton("Játék vége ... Kilépés");
        vege.setPreferredSize(new Dimension(200, 50));
        add(vege,BorderLayout.WEST);
        vege.setVisible(false);
        vege.addActionListener(new EndListener("Game"));
    }

    public class PlayerListener implements ActionListener {
            HexagonButton tile;

            PlayerListener(HexagonButton tile){this.tile=tile;}

        @Override
        public void actionPerformed(ActionEvent e) {
            if(tile.isUsed()) return;
            if(getLepesszam()%2==0){
                tile.setIcon(new ImageIcon("C:\\Users\\Hanga\\ProgHF\\piros.png"));
                tile.setSzin(Field.RED);
                field.setText("második játékos lép");
            }else{
                tile.setIcon(new ImageIcon("C:\\Users\\Hanga\\ProgHF\\zöld.png"));
                tile.setSzin(Field.GREEN);
                field.setText("Első játékos lép");
            }
            tile.setUsed(true);
            setLepesszam(getLepesszam()+1);
            setLastidx(tile.getRow(), tile.getCol());

            if(winner(tile, 3)) {
                vege.setVisible(true);

            } else{
                if(winner(tile,2)) {
                    vege.setVisible(true);

                }
            }
        }
    }
}