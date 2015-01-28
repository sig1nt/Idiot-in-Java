import java.util.*;

public class AI{

	private boolean nextNextWinning;
	private ArrayList<Card> hand, faceup;
	private ArrayList<Player> players;

	public AI(ArrayList<Card> hand, ArrayList<Card> faceup, ArrayList<Player> players) {
		nextNextWinning = false;
		this.hand = hand;
		this.faceup = faceup;
		this.players = players;
	}

	private boolean canCompleteFour(ArrayList<Card> handy, Pile pile) {
	//checks if the AI can complete a four of a kind
		if(handy.isEmpty()){return false;}
		ArrayList<Card> temp = new ArrayList<Card>(handy);
		temp.addAll(pile.seeThree());
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (Card c : temp){
			values.add(c.value);
		}
		Collections.sort(values);
		for(int i = 0; i<(values.size()-3); i++){
			if(values.get(i)==values.get(i+1) && values.get(i)==values.get(i+2)&&values.get(i)==values.get(i+3)){
				return true;
			}
		}
		return false;
	}

	private boolean isWinning(int handCount, int fdCount){
	//determines if the opponent is about to win
		return ((handCount+fdCount < 3) && (handCount == 0 || fdCount == 0));
	}

	public int logical(Pile pile, int fdCount){
		if(hand.isEmpty() && faceup.isEmpty()){return 1000; /* this means use a facedown card */}
		ArrayList<Card> reserve = (hand.isEmpty())?faceup:hand;
		if(canCompleteFour(reserve, pile)){
			return 100;
			//100 means complete the four
		}
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (Card card : reserve){
			values.add(new Integer(card.value));
		}
		Collections.sort(values);
		if(isWinning(players.get(0).handsize(),players.get(0).numDown())) {
			if(values.get(values.size()-1)>10){
				return(int)(values.get(values.size()-1));
			} else if(values.contains(7)){
				return 7;
			} else if(values.get(values.size()-1) < 10){
				return(int)(values.get(values.size()-1));
			} else {
				return 10;
			}
		}
		if(players.size()>1){
			if(isWinning(players.get(1).handsize(),players.get(1).numDown())) { 
				if((int)(values.get(0)) != 7 && (int)(values.get(0))!=10 && (int)(values.get(0))!=2){
					return (int)(values.get(0));
				} else if((int)(values.get(0)) == 7) {
					return 7;
				} else if((int)(values.get(0)) == 2) {
					return 2;
				} else {
					return 10;
				}
			}
		}
		if((int)values.get(0) != 2 && (int)(values.get(0)) != 10){
			return (int)values.get(0);
		} else if((int)(values.get(0))== 2){
			return 2;
		} else {
			return 10;
		}
	}

	/*public static void main(String[] args){
		ArrayList<Card> h = new ArrayList<Card>();
        h.add(new Card("spades", 10));
        h.add(new Card("hearts", 3));
        h.add(new Card("hearts", 4));
        ArrayList<Card> f = new ArrayList<Card>();
        f.add(new Card("spades", 7));
        f.add(new Card("hearts", 5));
        f.add(new Card("hearts", 8));
		Pile p = new Pile();
		//p.add(new Card("spades", 3));
		p.add(new Card("clubs", 3));
		p.add(new Card("diamonds", 3));
		ArrayList<Player> o = new ArrayList<Player>();
		Deck d = new Deck();
		o.add(new CPU("test",d));
		AI driver = new AI(h,f,o);
        System.out.println(driver.logical(p,3));
	}*/
}
