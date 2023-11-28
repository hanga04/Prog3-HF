package Yavalath;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * A játék logikáját megvalósító osztály
 */
public class GameLogic implements Serializable {
    /**
     * Menu objektum, a játék indításához
     */
    static Menu menu;
    /**
     * Eltároljuk a játékmódót a könnyebb használat érdekében
     */
    static String jatekmod;
    /**
     * A pályát adó tábla
     */
    Table table;
    /**
     * Eltároljuk a játékosokat egy 2 hosszúságú listában
     */
    ArrayList<Player> players=new ArrayList<>(2);
    /**
     * Tároljuk, hogy melyik játékos van éppen soron
     */
    private Player turn;
    /**
     * Tároljuk, hogy véget ért-e a játék győzelemmel
     */
    private boolean nyert;
    /**
     * Tároljuk, hogy véget ért-e a játék veszítéssel
     */
    private boolean vesztett;

    /**
     * beállítja a játékmódót a táblát a paraméterek alapján
     * és a játék végét jelző booleanokat hamisra állítja
     * @param table - logikai tábla
     * @param j - játékmód
     * @throws IOException
     */
    public GameLogic(Table table, String j) throws IOException {
        jatekmod=j;
        this.table = table;
        nyert = false;
        vesztett = false;
        players.add(0, new Player());
        players.add(1, new Player());
    }

    /**
     * Elindítja a programot, egy Menu létrehozásával
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        menu=new Menu();
        menu.setVisible(true);
    }

    /**
     * Elindítja a megfelelő játékmódot a kapott paraméter alapján
     * @param s - játékmód
     * @throws IOException
     */
    public static void jatek_inditas(String s) throws IOException {
        Game game;
        Demo demo;
        Single single;
        switch (s) {
            case "Demo" -> {
                demo = new Demo();
            }
            case "1 Player" -> {
                try {
                    single = new Single();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case "2 Players" -> {
                game=new Game();
            }
        }
    }

    /**
     * Meghívja a játék utáni ablakot megjelenítő metódust a szükséges paraméterekkel
     * @param s - játékmód
     * @param table - logikai tábla (pálya)
     * @param gl - játéklogika
     */
    public void end(String s, Table table, GameLogic gl){
        Menu.jatekVege(s, table, gl);
    }

    /**
     * A paraméterként kapott játékos lépését végzi
     * Amennyiben nem az adott játékos van soron visszatér.
     * Hogyha a játékos egy gép, akkor az AIlép() metódust hivja meg
     * Különben, ha olyan mezőre kattintottunk (paraméterként meg van adva), ami már foglalt szintén visszatér,
     * ha nem, akkor meghívja a lep() metódust ami elvégzi a megfelelő változtatásokat
     * @param coord - a kattintott gomb, amit változtatni szeretnénk
     * @param player -  a játékos akinek a lépését végezzük
     * @return
     */
    public Coordinate step(Coordinate coord, Player player){
        if(getTurn().equals(player))
        {
            if (player.getName().contains("Gép")) {
                coord=AIlep(player);
            } else {
                if(table.getButton().get(coord.getX()).get(coord.getY()).getSzin()!= Colors.BASIC) {
                    return null;
                }else{
                    lep(coord, player);
                }
            }
            table.setLastidx(coord);
            nextPlayer();
            return coord;
        }
        return null;
    }

    /**
     * Beállítja a kapott koordinátájú mezőt és képet a játékos színére
     * meghívja a játék végét ellenőrző metódust, ha az igazzal tér vissza beállítja a megfelelő
     * logikai értéket igazra (nyert/vesztett)
     * @param coord - változtatandó mező koordinátája
     * @param player - soron lév játékos
     */
    public void lep(Coordinate coord, Player player){
        if(table.getButton().get(coord.getX()).get(coord.getY()).getSzin() != Colors.BASIC) return;
        table.getButton().get(coord.getX()).get(coord.getY()).setIcon(player.getImage());
        table.getButton().get(coord.getX()).get(coord.getY()).setSzin(player.getColor());


        if (winner(table.getButton().get(coord.getX()).get(coord.getY()), 3)) {
            setNyert(true);
        } else {
            if (winner(table.getButton().get(coord.getX()).get(coord.getY()), 2)) {
                setVesztett(true);
            }
        }

    }

    /**
     * A gép lépéseit végző metódus
     * generál két számot, amik a választott mező koordinátái lesznek
     * Ha az adott koordinátájú mező null vagy már foglalt, akkor újat generál
     * A generált mezőn elvégzi a megfelelő változtatásokat
     * meghívja a játék végét figyelő metódust és átállítja az értékeket, ha az szükséges
     * @param player - játékos, akinek a lépését végzi
     * @return
     */
    public Coordinate AIlep(Player player) {
        Random rand = new Random();
        int i = rand.nextInt(9);
        int j = rand.nextInt(9);

        while (table.getButton().get(i).get(j) == null || table.getButton().get(i).get(j).getSzin() != Colors.BASIC) {
            i = rand.nextInt(8);
            j = rand.nextInt(8);
        }
        table.getButton().get(i).get(j).setSzin(player.getColor());
        table.getButton().get(i).get(j).setIcon(player.getImage());

        table.setLastidx(new Coordinate(i,j));
        if (winner(table.getButton().get(i).get(j), 3)) {
            setNyert(true);
        } else {
            if (winner(table.getButton().get(i).get(j), 2)) {
                setVesztett(true);
            }
        }
        return new Coordinate(i,j);
    }

    /**
     * Ellenőrzi, hogy a megtett lépés hatására nyert-e/vesztett-e az egyik játékos
     * Az adott mezőre megnézi, hogy a 3 irányban hány egyforma színű van egymás mellett
     * Ehhez minden irányba meghívja a megfelelő check metódust
     * @param tile - megváltoztatott mező
     * @param szam - Hány egymás melletti összegyűlését figyelje
     * @return - Ha egy adott irányba van szam mennyiségű azonos mező, akkor igaz, különben hamis
     */
    public boolean winner(Hexagon tile, int szam) {
        int sordb = 0;
        int oszlopdb = 0;
        int atlodb = 0;
        sordb = checkneighbour(tile, 0, 1) + check2(tile, 0, 1);
        oszlopdb = checkneighbour(tile, 1, 0) + check2(tile, 1, 0);
        atlodb = checkneighbour(tile, -1, 1) + check2(tile, -1, 1);
        return sordb == szam || oszlopdb == szam || atlodb == szam;
    }

    /**
     * Megnézi, hogy az adott gombtól +i és +j-re lévő gomb színe megegyezik-e a sajátjával
     * Hogyha igen, akkkor növeli a darabszámot és arra a szomszédra meghívja rekurzívan ugyanezt a függvényt
     * Ellenőrzi, hogy a következő gomb még mindig a táblán legyen (index out of bounds megelőzése)
     * @param tile - gomb, aminek a szomszédját ellenőrizzük
     * @param i - vízszintes irányba való haladás mértéke
     * @param j - függőleges irányba való haladás mértéke
     * @return - ahány szomszédos mezőt találtunk
     */
    public int checkneighbour(Hexagon tile, int i, int j) {
        int db = 0;
        int row = tile.getRow();
        int col = tile.getCol();
        if (row < 8 && row > 0 && col < 8) {
            Hexagon comp = table.getButton().get((row + i)).get((col + j));
            if (comp != null) {
                if (tile.getSzin().equals(comp.getSzin())) {
                    db += 1;
                    db = db + checkneighbour(comp, i, j);
                }
            }
        }
        return db;
    }

    /**
     * Azonos a checkneighbour fügvénnyel, de itt az i és j értékeket kivonja a kapott gomb koordinátáiból
     * @param tile - kapott gomb, amit ellenőrzünk
     * @param i - vízszintes haladás
     * @param j - függőleges haladás
     * @return - ahány szomszédos mezőt találtunk
     */
    public int check2(Hexagon tile, int i, int j) {
        int db = 0;
        int row = tile.getRow();
        int col = tile.getCol();
        if (row > 0 && row < 8 && col > 0) {
            Hexagon comp2 = table.getButton().get((row - i)).get((col - j));
            if (comp2 != null) {
                if (tile.getSzin().equals(comp2.getSzin())) {
                    db += 1;
                    db = db + check2(comp2, i, j);
                }
            }

        }
        return db;
    }

    /**
     * Beállítja, hogy a következő játékos köre jöjjön
     */
    public void nextPlayer() {
        if (getTurn().equals(players.get(0))) {
            turn = players.get(1);
        } else {
            turn = players.get(0);
        }
    }

    /**
     * Fájlba menti az aktuális állást
     */
    public void save() {
        try {
            FileOutputStream fos = new FileOutputStream("gamelogic");
            ObjectOutputStream ous = new ObjectOutputStream(fos);
            ous.writeObject(this);
            ous.close();
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    /**
     * Beállítja a táblát a kapott paraméternek
     * @param table
     */
    public void setTable(Table table){
        this.table = table;
    }

    /**
     * @return - nyert értéke
     */
    public boolean getNyert() {
        return nyert;
    }

    /**
     * Beállítja a nyert értékét
     * @param b - boolean érték
     */
    public void setNyert(boolean b) {
        nyert = b;
    }

    /**
     * @return - vesztett értéke
     */
    public boolean getVesztett() {
        return vesztett;
    }

    /**
     * Beállítja a vesztett értékét
     * @param b - boolean érték
     */
    public void setVesztett(boolean b) {
        vesztett = b;
    }

    /**
     *
     * @return - a soron következő játékos
     */
    public Player getTurn(){
        return turn;
    }

    /**
     * Beállítja a soron következő játkost
     * @param player - játékos
     */
    public void setTurn(Player player){
        turn=player;
    }

    /**
     *
     * @return - aktuális játékmód
     */
    public String getJatekmod(){
        return jatekmod;
    }

    /**
     * Visszaadja a játékosokat
     * @return - játékosok listája
     */
    public ArrayList<Player> getPlayers(){
        return players;
    }
}
