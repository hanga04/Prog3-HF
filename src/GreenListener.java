import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;

public class GreenListener implements ActionListener {

    HexagonButton tile;
    boolean first_player=true;
    GreenListener(HexagonButton tile){
        this.tile=tile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tile.setIcon(new ImageIcon("C:\\Users\\Hanga\\ProgHF\\zöld.png"));

    }
}

