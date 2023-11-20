
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class RedListener implements ActionListener {

    HexagonButton tile;
    boolean first_player=true;
    RedListener(HexagonButton tile){
        this.tile=tile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tile.setIcon(new ImageIcon("C:\\Users\\Hanga\\ProgHF\\piros.png"));
        ImageIcon image=new ImageIcon("C:\\Users\\Hanga\\ProgHF\\alap.png");
        if(Objects.equals(tile.getIcon(), image)) System.out.println("haha");
    }
}
