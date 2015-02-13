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
    protected ConsoleIO cio = new ConsoleIO("That's not right", 10, 100, 100);
    
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
        sort(hand);
    }
    
    /*Allows a human to choose their their move, given a certain Pile currP, and then populating the hand up to 3 cards from the deck D
      Return values tell game how to respond to the move:
      0: go to next player
      1: clear Pile then go to next player
      2: end game, current player wins
    */
    public int move(Pile currP, Deck d){
        String s = "";
        if(!currP.isEmpty()){
            s += "Cards in the Pile are:\n";
            for(Card c: currP.seeThree()){
                s += c.getShortName() + "\n";
            }
        }else{
            s += "There are no cards in the pile";
        }
        cio.typeln(s);
        boolean chosen = false;
        if(canMove(currP)){
            if(hand.get(0) != null || faceup.size() != 0){
                if(!(hand.get(0) == null)){
                    String cinh = "the cards in your hand are:\n";
                    for(int i = 0; i < hand.size(); i++){
                        cinh += (i + 1) + ") " + hand.get(i).getShortName() + "\n";
                    }
                    cio.typeln(cinh);
                    int numPlay = 1, index;
                    Card c = new Card();
                    do{
                        while(true){
                            index = (int)cio.in("enter the index of the card you wish to play", 0);
                            if(index >= 1 && index <= hand.size()){
                                break;
                            }
                        }
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
                                    numPlay = (int)cio.in("How many Do you want to play? (Up to " + sameVals + ")", 0);
                                }while(numPlay < 1 || numPlay > sameVals);
                            }
                            chosen = true;
                        }
                    }while(!chosen);
                    for(int i = 0; i < numPlay; i++){
                        currP.add(hand.get(index - 1));
                        hand.remove(index - 1);
                    }
                    while(hand.size() < 3 && !d.empty()){
                        hand.add(d.draw());
                    }
                    sort(hand);
                    if(hand.isEmpty() && facedown.isEmpty()){
                        return 2;
                    }
                    if(c.value != 10 && !currP.fourKind()){
                        return 0;
                    }else{
                        currP.clear();
                        return 1;
                    }
                }else{
                    String cinf = "the cards in your hand are:\n";
                    for(int i = 0; i < faceup.size(); i++){
                        cinf += (i + 1) + ") " + faceup.get(i).getShortName() + "\n";
                    }
                    cio.typeln(cinf);
                    int numPlay = 1, index;
                    Card c = new Card();
                    do{
                        index = (int)cio.in("enter the index of the card you wish to play", 0);
                        if(index<1 || index>hand.size()){
                            cio.typeln("Please enter a valid hand index.\n");
                            continue;
                        }
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
                    if(hand.isEmpty() && facedown.isEmpty()){
                        return 2;
                    }
                    if(c.value != 10 && !currP.fourKind()){
                        return 0;
                    }else{
                        currP.clear();
                        return 1;
                    }
                }
            }else{
               if(currP.validMove(facedown.get(0))){
                    currP.add(facedown.get(0));
                    facedown.remove(0);
                }else{
                    currP.add(facedown.get(0));
                    facedown.remove(0);
                    hand.addAll(currP.pickup());
                    cio.typeln(name + " picked up pile");
                    sort(hand);
                    return 0;
                } 
            }
        }else{
            hand.addAll(currP.pickup());
            cio.typeln(name + " picked up pile");
            sort(hand);
            return 0;
        }
        return -1;
    }
    
    public int firstMove(Pile currP, Deck d){
    	cio.typeln("Making First Move");
        Card c = hand.get(0);
        int numPlayable = 0;
        if(c.value != 2 && c.value != 10){
            for(Card currC: hand){
                if(currC.value == c.value){
                    numPlayable++;
                }
            }
        } else {
			// Play one two if we have one
			// Play one ten in we have one
		}
        for(int i = 0; i < numPlayable; i++){
            currP.add(hand.get(0));
            hand.remove(0);
        }
        cio.typeln(name + " played " + numPlayable + " " + c.value + "s");
        while(hand.size() < 3){
            hand.add(d.draw());
        }
        sort(hand);
        cio.typeln("Ending First Move");
        if(c.value != 10){
            return 0;
        }else{
            return 1;
        }
    }
    
    /*checks the hand to see if a player can move based on their hand and the pile p
      returns true if the player can move and false if they can't
    */
    public boolean canMove(Pile p){
        if(p.isEmpty() || (hand.size() == 0 && faceup.size() == 0)){
            return true;
        }else{
            System.out.println("Hand: " + hand.isEmpty());
            if(!(hand.get(0) == null)){
                for(Card c: hand){
                    if(p.validMove(c)){
                        return true;
                    }
                    cio.typeln(c.value + " was not valid");
                }
            }else{
                for(Card c: faceup){
                    if(p.validMove(c)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public void sort(ArrayList<Card> cards){
		// is cards.length() ever 0? if so, check here and return cards
        if(cards.size() != 0 && cards.get(0) == null){
            cards.remove(0);
        }
        Card[] tempCards = cards.toArray(new Card[1]);
        cards.clear();
		int index;
        for(int i = 0; i < tempCards.length; i++){
            Card currSmallest = tempCards[i];
            index = i;
            for(int j = i + 1; j < tempCards.length; j++){
                if(tempCards[j].value < currSmallest.value){
                    currSmallest = tempCards[j];
                    index = j;
                }else{
                    if(tempCards[j].value == currSmallest.value && suits.indexOf(tempCards[j].suit) < suits.indexOf(currSmallest.suit)){
                        currSmallest = tempCards[j];
                        index = j;
                    }
                }
            }
            if(i != index){
                Card temp = tempCards[i];
                tempCards[i] = currSmallest;
                tempCards[index] = temp;
            }
            cards.add(tempCards[i]);
        }
    }

	public Card getLowerCard(Card a, Card b) {
		if (a.value < b.value) {
			return a;
		} else if (b.value < a.value) {
			return b;
		} else {
			return (suits.indexOf(a.suit) < suits.indexOf(b.suit)) ? a : b;
	}

	private void merge(ArrayList<Card> left, ArrayList<Card> right) {
		int ia, ib;
		ia = ib = 0;
		ArrayList<Card> merged = new ArrayList<Card>;
		while (ia < a.size() && ib < b.size()) {
			if (a[ia] < b[ib]) {
				merged.add(a[ai]);
				ia++;
			} else {
				merged.add(b[ib]);
				ib++;
			}
		}
		while (ia < a.size()) {
			merged.add(a[ia]);
			ia++;
		}
		while (ib < b.size()) {
			merged.add(b[ib]);
			ib++;
		}
		return merged;
	}

	public void sort(ArrayList<Card> cards) {
		if (cards.size() < 2){
			return cards;
		} else {
			// sort
		}
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
