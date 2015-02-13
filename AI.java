import java.util.*;

public class AI{

	private ArrayList<Card> hand, faceup;
	private ArrayList<Player> players;

	public AI(ArrayList<Card> hand, ArrayList<Card> faceup, ArrayList<Player> players) {
		this.hand = hand;
		this.faceup = faceup;
		this.players = players;
	}

	//checks if the AI can complete a four of a kind
	private int canCompleteFour(ArrayList<Card> handy, Pile pile) {
		if(handy.isEmpty()){return -1;}
		ArrayList<Card> temp = new ArrayList<Card>(handy);
		if(!pile.isEmpty()){temp.add(pile.checkTop());}
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (Card c : temp){
			values.add(c.value);
		}
		Collections.sort(values);
		for(int i = 0; i<(values.size()-3); i++){
			if(values.get(i)==values.get(i+3)){
				//System.out.println(values.get(i) + " " + values.get(i+3));
				return values.get(i);
			}
		}
		return -1;
	}

	//determines if the passed player is about to win
	private boolean isWinning(Player opp){
		return ((opp.hand.size()+opp.facedown.size() < 3) && (opp.hand.size() == 0 || opp.facedown.size() == 0));
	}

	//this finds the value of the card that we're going to play
	private int getPlayValue(Pile pile, int fdCount){
		//System.out.println("\nHand I am handed:" + hand);
		//System.out.println("\nPile I'm given:" + pile.seeThree());
		//System.out.println("\nFaceups: " + faceup);
		ArrayList<Card> reserve = (hand.isEmpty()||hand.get(0)==null)?faceup:hand;
		int fourCheck = canCompleteFour(reserve,pile);
		if(fourCheck != -1){return fourCheck;}
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (Card card : reserve){
			if(pile.validMove(card)){
				values.add(new Integer(card.value));
			}
		}
		//System.out.println("\n Cards in reserve:" + values);
		Collections.sort(values);
		if(isWinning(players.get(0))) {
			//System.out.println("One person is winning");
			if(values.get(values.size()-1)>10){
				return(int)(values.get(values.size()-1));
			} else if(values.contains(7)){
				return 7;
			} else if(values.get(values.size()-1) < 10){
				return(int)(values.get(values.size()-1));
			} else {
				return 10;
			}
		} if(players.size()>1 && isWinning(players.get(1))) {
			//System.out.println("Someone far away is winning");
			for (Integer i : values){
				if((int)(i)!=7 && (int)(i) != 2 && (int)(i) != 10){
					return (int)(i);
				}
			}
			if((int)(values.get(0)) == 7) {
				return 7;
			} else if((int)(values.get(0)) == 2) {
				return 2;
			} else if((int)(values.get(0)) == 2){
				return 10;
			}
        }
        //System.out.println("Default");
		for (Integer i : values){
			if((int)(i)!=10 && (int)(i)!=2){
				return (int)(i);
			}
		}
		if((int)(values.get(0)) == 2){
			return 2;
		}
		else{
			return 10;
		}
	}

	//takes the value from logic and finds the card in your hand/faceups
	public Card logical(Pile pilein, int fdCountin){
		int value = getPlayValue(pilein, fdCountin);
		System.out.println("Value of Card I will play:" + value);
		ArrayList<Card> reserve = (hand.isEmpty()||hand.get(0)==null)?faceup:hand;
		for (Card c : reserve){
			//The error is here, it's getting a null pointer
			if(c.value == value){
				//System.out.println("\nCard I am sending:" + c);
				return c;
			}
		}
		return new Card();
	}

	/*public static void main(String[] args){
		ArrayList<Card> h = new ArrayList<Card>();
        h.add(new Card("spades", 10));
        h.add(new Card("hearts", 8));
        h.add(new Card("hearts", 13));
        ArrayList<Card> f = new ArrayList<Card>();
        f.add(new Card("spades", 7));
        f.add(new Card("hearts", 5));
        f.add(new Card("hearts", 8));
		Pile p = new Pile();
		p.add(new Card("spades", 6));
		p.add(new Card("clubs", 6));
		p.add(new Card("diamonds", 14));
		ArrayList<Player> op = new ArrayList<Player>();
		Deck d = new Deck();
		Player player = new Player(true,"test",d);
		ArrayList<Card> ophand = new ArrayList<Card>();
		ophand.add(new Card("hearts",12));
		player.hand = ophand; 
		//player.facedown = new ArrayList<Card>();
		op.add(player);
		AI driver = new AI(h,f,op);
        System.out.println(driver.logical(p,3).toString());
	}*/
}
