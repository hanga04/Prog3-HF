public class Game {
    Player p1;
    Player p2;

    Game(Player p1, Player p2){
        this.p1=p1;
        this.p2=p2;
        while(this.vege()){
            p1.jatekos_lep();
            p2.jatekos_lep();
        }
    }

    /**Ez ellenőrzi, hogy van-e 3/4 egymás mellett vagy hogy betelt a tábla
     * Technicly ez a lényegi része a programnak, probably sokára fogom megcsinálni
     * de ha ez működik akk elv jók vagyunk
     */
    public boolean vege(){
        return true;
    }

}
