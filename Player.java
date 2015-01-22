//Implementation of the Player Class

import java.util.*;

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
        sortHand();
    }
    
    public int move(Pile currP, Deck d){
        String s = "Cards in the Pile are:\n";
        s += currP.checkTop().getShortName() + "\n";
        s += "the cards in your hand are:\n";
        for(int i = 0; i < hand.size(); i++){
            s += (i + 1) + ") " + hand.get(i).getShortName() + "\n";
        }
        cio.typeln(s);
        boolean chosen = false;
        if(canMove(currP)){
            int numPlay = 1, index;
            Card c;
            do{
                index = (int)cio.in("enter the index of the card you wish to play", 0);
                c = hand.get(index - 1);
                if(!currP.validMove(c)){
                    cio.typeln("that move is illegal");
                }else{
                    int sameVals = 0;
                    for(Card currC: hand){
                        if(currC.value == c.value){
                            sameVals++;
                        }
                    }
                    if(sameVals > 1){
                        do{
                            numPlay = (int)cio.in("How many Do you want to play? (Up to" + sameVals + ")", 0);
                        }while(numPlay < 1 || numPlay > sameVals);
                    }
                    chosen = true;
                }
            }while(!chosen);
            for(int i = 0; i < numPlay; i++){
                currP.add(hand.get(index - 1));
            }
            for(int i = 0; i < numPlay; i++){
                hand.remove(index - 1);
            }
            while(hand.size() < 3){
                hand.add(d.draw());
            }
            sortHand();
            cio.type("Played");
            if(c.value != 10 && !currP.fourKind()){
                return 0;
            }else{
                return 1;
            }
        }else{
            hand.addAll(currP.pickup());
            cio.type("Picked up pile");
            sortHand();
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
    
    public void sortHand(){
        Card[] tempHand = hand.toArray(new Card[1]);
        hand.clear();
        int highestFilled = 0;
        for(int i = 0; i < tempHand.length; i++){
            if(tempHand[i] == null){
                break;
            }
            highestFilled = i;
        }
        for(int i = 0; i < highestFilled + 1; i++){
            Card currSmallest = tempHand[i];
            int index = i;
            for(int j = i + 1; j < highestFilled + 1; j++){
                if(tempHand[j].value < currSmallest.value){
                    currSmallest = tempHand[j];
                    index = j;
                }
            }
            if(i != index){
                Card temp = tempHand[i];
                tempHand[i] = currSmallest;
                tempHand[index] = temp;
            }
            hand.add(tempHand[i]);
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
        Deck d = new Deck();
        Player p = new Player(true, "Kent",d);
        Pile pi = new Pile();
        do{
            pi.add(d.draw());
            p.move(pi, d);
        }while(!d.empty());
    }
}