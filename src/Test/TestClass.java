package Test;

import Yavalath.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.io.IOException;

public class TestClass {
    Player testPlayer1;
    Player testPlayer2;
    Player testPlayer3;
    Hexagon testHex1;
    Hexagon testHex2;
    GameLogic testGameLogic;
    Table testTable;
    Coordinate coord;
    @Before
    public void setUp() throws Exception {
        testPlayer1=new Player("Teszt Elek", "zöld");
        testPlayer2=new Player();
        testPlayer3=new Player("Gép 1", "zöld");

        testHex1=new Hexagon(1,2);
        testHex2=new Hexagon(5, 6);

        testTable =new Table();
        testGameLogic = new GameLogic(testTable, "Game");

        coord=new Coordinate(0,0);
    }

    @Test
    public void testPlayers(){
        Assert.assertEquals("Nem jó a default konstruktor", "", testPlayer2.getName());
        Assert.assertEquals("Nem jó a default konstruktor", Colors.RED, testPlayer2.getColor());
        Assert.assertEquals("Nem jó a konstruktor", "Teszt Elek", testPlayer1.getName());
        Assert.assertEquals("Nem jó a konstruktor", Colors.GREEN, testPlayer1.getColor());
        Assert.assertEquals("Nem jó a konstruktor", new ImageIcon("C:\\Users\\Hanga\\Yavalath\\zöld.png").toString(), testPlayer1.getImage().toString());
    }

    @Test
    public void testHexagons(){
        Assert.assertEquals("Rosszul állítja be a színt",new ImageIcon("C:\\Users\\Hanga\\Yavalath\\alap.png").toString(), testHex1.getIcon().toString());
        Assert.assertEquals("Rosszul adja vissza a sort", 1, testHex1.getRow());
        Assert.assertEquals("Rosszul adja vissza az oszlopot", 6, testHex2.getCol());
    }

    @Test
    public void testTable(){
        Assert.assertNull("Rosszul építi a pályát", testTable.getButton().get(1).get(1));
        Assert.assertNotNull("Rosszul építi a pályát", testTable.getButton().get(6).get(5));
    }

    @Test
    public void testGameLogic() throws IOException {
        testGameLogic.setTurn(testGameLogic.getPlayers().get(0));
        testGameLogic.nextPlayer();
        Assert.assertEquals("Nem jó a konstruktor", "Game", testGameLogic.getJatekmod());
        Assert.assertEquals("Nem jó a következő kör beállítása", testGameLogic.getPlayers().get(1), testGameLogic.getTurn());
    }

    @Test
    public void testWinner(){
        testGameLogic.setTurn(testPlayer1);
        testGameLogic.step(new Coordinate(4,5), testPlayer1);
        testGameLogic.setTurn(testPlayer1);
        testGameLogic.step(new Coordinate(4,6), testPlayer1);
        testGameLogic.setTurn(testPlayer1);
        testGameLogic.step(new Coordinate(4, 7), testPlayer1);
        Assert.assertEquals("Nem jól ellenőrzi a szomszédokat", 2, testGameLogic.checkneighbour(testTable.getButton().get(4).get(5), 0, 1));
        Assert.assertEquals("Nem jól ellenőrzi a szomszédokat", 0, testGameLogic.check2(testTable.getButton().get(4).get(5), 0, 1));
        Assert.assertTrue("Nem jó a vesztes ellenőrzés", testGameLogic.winner(testTable.getButton().get(4).get(5), 2));
        Assert.assertFalse("Nem jó a nyertes ellenőrzés", testGameLogic.winner(testTable.getButton().get(4).get(5), 3));
    }

    @Test
    public void testGep(){
        testGameLogic.setTurn(testPlayer3);
        Assert.assertNotEquals("Nem lépett az AI", coord, testGameLogic.step(coord, testPlayer3));
    }



}