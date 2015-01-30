import java.util.*;

public class AI{

	private ArrayList<Card> hand, faceup;
	private ArrayList<Player> players;

	public AI(ArrayList<Card> hand, ArrayList<Card> faceup, ArrayList<Player> players) {
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

	private boolean isWinning(Player opp){
	//determines if the opponent is about to win
		return ((opp.hand.size()+opp.facedown.size() < 3) && (opp.hand.size() == 0 || opp.facedown.size() == 0));
	}

	private int logic(Pile pile, int fdCount){
		if(hand.isEmpty() && faceup.isEmpty()){return 0; /* this means use a facedown card */}
		ArrayList<Card> reserve = (hand.isEmpty())?faceup:hand;
		if(canCompleteFour(reserve, pile)){
			return pile.checkTop().value;
		}
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (Card card : reserve){
			values.add(new Integer(card.value));
		}
		Collections.sort(values);
		if(isWinning(players.get(0))) {
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

	public Card logical(Pile pilein, int fdCountin){
		int value = logic(pilein, fdCountin);
		ArrayList<Card> reserve = (hand.isEmpty())?faceup:hand;
		for (Card c : reserve){
			if(c.value == value){return c;}
		}
		return new Card("errors",12);

	}

	public static void main(String[] args){
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
		p.add(new Card("diamonds", 6));
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
	}
}
