//Implementation of the Player Class

import java.util.ArrayList;
import java.util.Random;

public class Player{
    //static vars
    protected static int numHuman = 0, numCPUs = 0, numPlayers = 0;
    
    //non-static vars
    private boolean human;
    private ArrayList<Card> hand, facedown;
    protected ArrayList<Card> faceup;
    protected String name;
    private ConsoleIO cio = new ConsoleIO("That's not right", 10, 100, 10);
    
    public Player(boolean isHuman, String n, Deck d){
        human = isHuman;
        if(human){
            numHuman++;
        }else{
            numCPUs++;
        }
        numPlayers++;
        hand = new ArrayList<Card>();
        facedown = new ArrayList<Card>();
        faceup = new ArrayList<Card>();
        name = n;
        for(int i = 0; i < 3; i++){
            facedown.add(d.draw());
            faceup.add(d.draw());
            hand.add(d.draw());
        }
    }
    
    public void move(){
        String s = "";
        for(Card c: hand){
            s += c.getShortName() + " ";
        }
        cio.typeln(s);
        String c = (String)cio.ask("Which Card would you like to play", "");
        if(validMove(c)){
            cio.type
        }
    }
    
    public String toString(){
        String s = name + "\n";
        for(Card c: hand){
            s += c.toString() + " ";
        }
        return s;
    }
    
    public static void main(String [] args){
        Random r = new Random();
        Player[] p = new Player[5];
        Deck gameD = new Deck();
        for(int i = 0; i < 5; i++){
            p[i] = new Player(r.nextBoolean(), "Player" + (i + 1), gameD);
            System.out.println(p[i]);
        }
        System.out.println(p[0].numHuman + " " + p[0].numCPUs + " " + p[0].numPlayers);
    }
}