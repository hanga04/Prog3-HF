import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Random;

public abstract class HexagonFrame extends JFrame {

    private static final int ROWS = 9;
    private static final int COLUMNS = 9;
    private int lepesszam;
    protected HexagonButton[][] button;
    private int lasti;
    private int lastj;
    private boolean nyert=false;
    private boolean vesztett=false;

    HexagonFrame(String s) throws IOException {
        super(s);
        lepesszam = 0;
        kiepit();
        this.add(Menu(), BorderLayout.NORTH);
    }

    protected void kiepit() throws IOException {
        button=new HexagonButton[ROWS][COLUMNS];
        int x = 439;
        int offsetY = 0;

        for(int i=0; i<ROWS; i++){
            for(int j=0; j<COLUMNS; j++){
                if(i+j<4 || i+j > 12) {
                    button[i][j]=null;
                } else {
                    button[i][j] = new HexagonButton(i, j);
                    add(button[i][j]);
                    button[i][j].setBounds(x, offsetY, 95, 110);
                    x+=94;
                }
            }
            offsetY+=81;
            if(i<4) {
                x=390-(i*47);
            }else{
                x=390+(i-6)*47;
            }
        }
    }
    public JMenuBar Menu(){
        JMenuBar menu=new JMenuBar();
        JMenu jtek=new JMenu("játék");

        JMenuItem ujjatek=new JMenuItem("visszavonás", KeyEvent.VK_A);
        JMenuItem kilep=new JMenuItem("kilépés", KeyEvent.VK_B);
        JMenuItem ment=new JMenuItem("mentés", KeyEvent.VK_C);

        ujjatek.addActionListener(new UndoListener());
        kilep.addActionListener(new ExitListener());

        jtek.add(ujjatek);
        jtek.add(kilep);
        jtek.add(ment);

        menu.add(jtek);
        return menu;
    }

    public class ExitListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
        }
    }

    public class UndoListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
                button[lasti][lastj].setIcon(new ImageIcon("C:\\Users\\Hanga\\ProgHF\\alap.png"));
                button[lasti][lastj].setSzin(Field.BASIC);
                button[lasti][lastj].setUsed(false);
                setLepesszam(getLepesszam()-1);
        }
    }

    class EndListener implements ActionListener {
        String s;
        EndListener(String s){
            this.s=s;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            JatekVege vege=new JatekVege(s);
            setVisible(false);
        }
    }

    public void AIlep(ImageIcon icon, Field field) {
        Random rand = new Random();
        int i = rand.nextInt(9);
        int j = rand.nextInt(9);

        while (button[i][j] == null || button[i][j].isUsed()) {
            i = rand.nextInt(8);
            j = rand.nextInt(8);
        }
            button[i][j].setSzin(field);
            button[i][j].setIcon(icon);
            button[i][j].setUsed(true);
            setLastidx(i,j);
        if(winner(button[i][j], 3)) {
            setNyert(true);
        } else{
            if(winner(button[i][j],2)) {
                setVesztett(true);
            }
        }
    }

    public int checkneighbour(HexagonButton tile, int i, int j) {
        int db = 0;
        if (tile.getRow() < 8 && tile.getRow()>0 && tile.getCol() < 8 ) {
            HexagonButton comp = button[tile.getRow() + i][tile.getCol() + j];
            if (comp != null) {
                if (tile.getSzin().equals(comp.getSzin())) {
                    db += 1;
                    db = db + checkneighbour(comp, i, j);
                }
            }
        }
        return db;
    }
    public int check2(HexagonButton tile, int i, int j){
        int db=0;
        if(tile.getRow()>0 && tile.getRow()<8 && tile.getCol()>0){
            HexagonButton comp2=button[tile.getRow() - i][tile.getCol() - j];
            if(comp2!=null){
                if(tile.getSzin().equals(comp2.getSzin())){
                    db+=1;
                    db=db + check2(comp2, i,j);
                }
            }

        }
        return db;
    }
    public boolean winner(HexagonButton tile, int szam){
        int sordb=0;
        int oszlopdb=0;
        int atlodb=0;
        sordb=checkneighbour(tile,0,1)+ check2(tile, 0,1);
        oszlopdb=checkneighbour(tile, 1,0) + check2(tile, 1, 0);
        atlodb=checkneighbour(tile, -1,1) + check2(tile, -1,1);
        return sordb == szam || oszlopdb == szam || atlodb == szam;
    }

    public int getRows(){
        return ROWS;
    }

    public int getColumns(){
        return COLUMNS;
    }

    public void setLastidx(int i, int j){
        lasti=i;
        lastj=j;
    }

    public int getLepesszam(){
        return lepesszam;
    }

    public void setLepesszam(int i){
        lepesszam=i;
    }

    public boolean getNyert(){
        return nyert;
    }
    public void setNyert(boolean b){
        nyert=b;
    }
    public boolean getVesztett(){
        return vesztett;
    }
    public void setVesztett(boolean b){
        vesztett=b;
    }

    public abstract void indit();
}