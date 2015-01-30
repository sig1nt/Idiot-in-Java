 //Implementation of the Player Class

import java.util.*;

public class Player{
    //static vars
    protected static int numHuman = 0, numCPUs = 0, numPlayers = 0;
    private static ArrayList<String> suits;
    
    //non-static vars
    protected boolean human;
    protected ArrayList<Card> hand, facedown;
    protected ArrayList<Card> faceup;
    protected String name;
    protected ConsoleIO cio = new ConsoleIO("That's not right", 10, 100, 10);
    
    //For use with the CPU class
    public Player(){
        numPlayers++;
        hand = new ArrayList<Card>();
        facedown = new ArrayList<Card>();
        faceup = new ArrayList<Card>();
        suits = new ArrayList<String>();
        suits.addAll(Arrays.asList("Clubs", "Diamonds", "Spades", "Hearts"));
    }
    
    //For use with human players
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
        suits = new ArrayList<String>();
        suits.addAll(Arrays.asList("Clubs", "Diamonds", "Spades", "Hearts"));
        name = n;
        for(int i = 0; i < 3; i++){
            facedown.add(d.draw());
            faceup.add(d.draw());
            hand.add(d.draw());
        }
        sortHand();
    }
    
    /*Allows a human to choose their their move, given a certain Pile currP, and then populating the hand up to 3 cards from the deck D
      Return values tell game how to respond to the move:
      0: go to next player
      1: clear Pile then go to next player
      2: end game, current player wins
    */
    public int move(Pile currP, Deck d){
        String s = "Cards in the Pile are:\n";
        s += currP.checkTop().getShortName() + "\n";
        cio.typeln(s);
        boolean chosen = false;
        if(canMove(currP)){
            if(!hand.isEmpty() || !faceup.isEmpty()){
                if(!hand.isEmpty()){
                    String cinh = "the cards in your hand are:\n";
                    for(int i = 0; i < hand.size(); i++){
                        cinh += (i + 1) + ") " + hand.get(i).getShortName() + "\n";
                    }
                    cio.typeln(cinh);
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
                        hand.remove(index - 1);
                    }
                    while(hand.size() < 3){
                        hand.add(d.draw());
                    }
                    sortHand();
                    cio.type("Played");
                    if(hand.isEmpty() && facedown.isEmpty()){
                        return 2;
                    }
                    if(c.value != 10 && !currP.fourKind()){
                        return 0;
                    }else{
                        return 1;
                    }
                }else{
                    String cinf = "the cards in your hand are:\n";
                    for(int i = 0; i < faceup.size(); i++){
                        cinf += (i + 1) + ") " + faceup.get(i).getShortName() + "\n";
                    }
                    cio.typeln(cinf);
                    int numPlay = 1, index;
                    Card c;
                    do{
                        index = (int)cio.in("enter the index of the card you wish to play", 0);
                        c = faceup.get(index - 1);
                        if(!currP.validMove(c)){
                            cio.typeln("that move is illegal");
                        }else{
                            int sameVals = 0;
                            for(Card currC: faceup){
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
                        currP.add(faceup.get(index - 1));
                        faceup.remove(index - 1);
                    }
                    cio.type("Played");
                    if(hand.isEmpty() && facedown.isEmpty()){
                        return 2;
                    }
                    if(c.value != 10 && !currP.fourKind()){
                        return 0;
                    }else{
                        return 1;
                    }
                }
            }else{
               if(currP.validMove(facedown.get(0))){
                    currP.add(facedown.get(0));
                    facedown.remove(0);
                }else{
                    hand.addAll(currP.pickup());
                    cio.type("Picked up pile");
                    sortHand();
                    return 0;
                } 
            }
        }else{
            hand.addAll(currP.pickup());
            cio.type("Picked up pile");
            sortHand();
            return 0;
        }
        cio.type("Hey");
        return -1;
    }
    
    /*checks the hand to see if a player can move based on their hand and the pile p
      reutrns true if the player can move and false if they can't
    */
    public boolean canMove(Pile p){
        for(Card c: hand){
            if(p.validMove(c)){
                return true;
            }
        }
        for(Card c: faceup){
            if(p.validMove(c)){
                return true;
            }
        }
        return false;
    }
    
    public void sort(ArrayList<Card> cards){
        Card[] tempCards = cards.toArray(new Card[1]);
        cards.clear();
        int highestFilled = 0;
        for(int i = 0; i < tempCards.length; i++){
            if(tempCards[i] == null){
                break;
            }
            highestFilled = i;
        }
        for(int i = 0; i < highestFilled + 1; i++){
            Card currSmallest = tempCards[i];
            int index = i;
            for(int j = i + 1; j < highestFilled + 1; j++){
                if(tempCards[j].value < currSmallest.value && suits.indexOf(tempCards[j].suit) < suits.indexOf(currSmallest.suit)){
                    currSmallest = tempCards[j];
                    index = j;
                }
            }
            if(i != index){
                Card temp = tempCards[i];
                tempCards[i] = currSmallest;
                tempCards[index] = temp;
            }
            hand.add(tempCards[i]);
        }
    }
    
    //sorts the hand of the player by number and suit
    public void sortHand(){
        sort(hand);
    }
    
    //Gets the size of the player's hand
    public int handsize(){
        return hand.size();
    }
    
    //Gets number of cards facedown for a player
    public int numDown(){
        return facedown.size();
    }
    
    //Returns a String with the name and hand of the player
    public String toString(){
        String s = name + "\n";
        for(Card c: hand){
            s += c.toString() + " ";
        }
        return s;
    }

    //FOR TESTING ONLY
    public static void main(String [] args){
        Random r = new Random();
        Deck d = new Deck();
        Player p = new Player(true, "Kent", d);
        Pile pi = new Pile();
        do{
            pi.add(d.draw());
            p.move(pi, d);
        }while(!d.empty());
    }
}