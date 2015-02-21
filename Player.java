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
    protected ConsoleIO cio = new ConsoleIO("That's not right", 0, 100, 100);
    
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
        sort(faceup);
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
            if(hand.size() != 0 || faceup.size() != 0){
                if(hand.size() != 0){
                    String cinh = "the cards in your hand are:\n";
                    for(int i = 0; i < hand.size(); i++){
                        cinh += (i + 1) + ") " + hand.get(i).getShortName() + "\n";
                    }
                    cio.typeln(cinh);
                    int numPlay = 1, index;
                    Card c;
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
                            for(int i = 0; i < hand.size(); i++){
                                Card currC = hand.get(i);
                                if(currC.value == c.value){
                                    sameVals++;
                                    if(i < index - 1){
                                        index = i + 1;
                                    }
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
                    while(hand.size() < 3 && !d.isEmpty()){
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
                    String cinf = "the cards in your faceups are:\n";
                    for(int i = 0; i < faceup.size(); i++){
                        cinf += (i + 1) + ") " + faceup.get(i).getShortName() + "\n";
                    }
                    cio.typeln(cinf);
                    int numPlay = 1, index;
                    Card c;
                    do{
                        while(true){
                            index = (int)cio.in("enter the index of the card you wish to play", 0);
                            if(index >= 1 && index <= faceup.size()){
                                break;
                            }
                        }
                        c = faceup.get(index - 1);
                        if(!currP.validMove(c)){
                            cio.typeln("that move is illegal");
                        }else{
                            int sameVals = 0;
                            for(int i = 0; i < faceup.size(); i++){
                                Card currC = faceup.get(i);
                                if(currC.value == c.value){
                                    sameVals++;
                                    if(i < index - 1){
                                        index = i + 1;
                                    }
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
					System.out.println("Playing " + facedown.get(0) + " from facedowns");
                    facedown.remove(0);
                }else{
                    currP.add(facedown.get(0));
					System.out.println("Playing " + facedown.get(0) + " from facedowns");
                    facedown.remove(0);
                    hand.addAll(currP.pickup());
                    cio.typeln(name + " picked up pile");
                    sort(hand);
                    return 0;
                }
                if(hand.size() == 0 && facedown.size() == 0){
                    return 2;
                }else{
                    return 0;
                }
            }
        }else{
            hand.addAll(currP.pickup());
            cio.typeln(name + " picked up pile");
            sort(hand);
            return 0;
        }
    }
    
    public int firstMove(Pile currP, Deck d){
    	cio.typeln("Making First Move from hand: " + hand);
        Card c = hand.get(0);
        int index = 0;
        for(int i = 0; i < 3; i++){
            c = hand.get(i);
            index = i;
            if(c.value != 2 && c.value != 10){
                break;
            }else{
                if(i == 2){
                    index = 0;
                    c = hand.get(0);
                }
            }
        }
        int numPlayable = 0;
        if(c.value != 2 && c.value != 10){
            for(Card currC: hand){
                if(currC.value == c.value){
                    numPlayable++;
                }
            }
        }else{
            numPlayable = 1;
        }
        for(int i = 0; i < numPlayable; i++){
            currP.add(hand.get(index));
            hand.remove(index);
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
            currP.clear();
            return 1;
        }
    }
    
    /*checks the hand to see if a player can move based on their hand and the pile p
      returns true if the player can move and false if they can't
    */
    public boolean canMove(Pile p){
        if(p.isEmpty() || (hand.isEmpty() && faceup.isEmpty())){
            return true;
        }else{
            if(!hand.isEmpty()){
                for(Card c: hand){
                    if(p.validMove(c)){
                        return true;
                    }
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
    
	// Returns the lower of two cards
	private Card getLowerCard(Card a, Card b) {
		if (a.value < b.value) {
			return a;
		} else if (b.value < a.value) {
			return b;
		} else {
			return (suits.indexOf(a.suit) < suits.indexOf(b.suit)) ? a : b;
		}
	}

	// Returns a merged sorted ArrayList from two sorted ArrayLists
	private ArrayList<Card> merge(ArrayList<Card> a, ArrayList<Card> b) {
		int ia, ib;
		ia = ib = 0;
		ArrayList<Card> merged = new ArrayList<Card>();
		while (ia < a.size() && ib < b.size()) {
			if (getLowerCard(a.get(ia), b.get(ib)) == a.get(ia)) {
				merged.add(a.get(ia));
				ia++;
			} else {
				merged.add(b.get(ib));
				ib++;
			}
		}
		while (ia < a.size()) {
			merged.add(a.get(ia));
			ia++;
		}
		while (ib < b.size()) {
			merged.add(b.get(ib));
			ib++;
		}
		return merged;
	}

	// Recursively merge-sorts an ArrayList of cards in-place
	public void sort(ArrayList<Card> cards) {
		if (cards.size() <= 1){
			return;
		} else {
			ArrayList<Card> left = new ArrayList<Card>();
			ArrayList<Card> right = new ArrayList<Card>();
			left.addAll(cards.subList(0, (int) cards.size()/2));
			right.addAll(cards.subList(left.size(), cards.size()));
			sort(left);
			sort(right);
			ArrayList<Card> tempCards = merge(left, right);
			cards.clear();
			cards.ensureCapacity(tempCards.size());
			cards.addAll(tempCards);
		}
	}
    
    public void swapHand(){
        ArrayList<Card> hf = new ArrayList<Card>(6);
        hf.addAll(hand);
        hf.addAll(faceup);
        hand.clear();
        faceup.clear();
        System.out.println("We got to here");
        for(int i = 0; i < 3; i++){
            String out = "Cards available are:\n";
            for(int j = 0; j < hf.size(); j++){
                out += (j + 1) + ") " + hf.get(j).getShortName() + "\n";
            }
            cio.typeln(out);
            do{
                int index = (int)cio.in("Enter the index of the card you wish to add to your faceup: ", 0);
            }while(index < 1 || index > hf.size());
            faceup.add(hf.get(index - 1));
            hf.remove(index -1);
        }
        hand.addAll(hf);
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
        }while(!d.isEmpty());
    }
}
