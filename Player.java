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
    
    public int move(Pile currP, Deck d){
        String s = "the cards in your hand are:\n";
        for(int i = 0; i < hand.size(); i++){
            s += (i + 1) + ") " + c.getShortName() + "\n";
        }
        cio.typeln(s);
        boolean chosen = false;
        if(canMove(currP){
            do{
                int index = (int)cio.ask("enter the index of the card you wish to play", 0);
                Card c = hand.get(index - 1);
                if(!Pile.validMove(c)){
                    cio.typeln("that move is illegal");
                }else{
                    int sameVals = 0;
                    for(Card currC: hand){
                        if(currC.value == c.value){
                            sameVals++;
                        }
                    }
                    int numPlay;
                    if(sameVals > 1){
                        do{
                            numPlay = (int)cio.out("How many Do you want to play? (Up to" + numSame + ")",0);
                        }while(numPlay < 1 || numPlay > sameVals);
                    }
                }
            }while(!chosen);
            for(int i = index - 1; i < index + numPlay - 1; i++){
                currP.add(hand.get(i));
                hand.delete(i);
            }
            while(hand.size() > 3){
                hand.add(d.draw());
            }
            if(c.value != 10 && !currP.fourKind()){
                return 0;
            }else
                return 1;
            }
        }else{
            hand.add(currP.pickup());
            return 0;
        }
    }
    
    public boolean canMove(Pile p){
        for(Card c: hand){
            if(p.validMove(c)){
                return true;
            }
        }
        return false;
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