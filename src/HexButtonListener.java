import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HexButtonListener implements ActionListener {

    HexagonButton tile;
    HexButtonListener(HexagonButton tile){
        this.tile=tile;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Graphics g=tile.getGraphics();
        tile.paintComponent(g);
        tile.setBackground(Color.BLACK);
    }


}
