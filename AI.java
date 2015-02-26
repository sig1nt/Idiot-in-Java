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
	/*private int canCompleteFour(ArrayList<Card> handy, Pile pile) {
		if(handy.isEmpty()){return -1;}
		ArrayList<Card> temp = new ArrayList<Card>(handy);
		if(temp.size()>=4){
			for(int i = 0; i < temp.size()-3; i++){
				if(temp.get(i)==temp.get(i+3)){return temp.get(i).value;}
			}
		}
		if
		ArrayList<Card> topThree = pile.seeThree();
		int numPlayable = 0;
		Card sentinel = pile.checkTop();
		for(Card c: temp){
			if(c.value==sentinel.value){numPlayable++;}
		}
		for(Card c: topThree){
			if(c.value==sentinel.value){numPlayable++;}
			else{break;}
		}
		if(numPlayable==4){return sentinel.value;}
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
	}*/

	private int canCompleteFour(ArrayList<Card> reserve, Pile pile){
		ArrayList<Card> useableCards = new ArrayList<Card>(reserve);
		ArrayList<Card> pTop = new ArrayList<Card>(pile.seeThree());
		if(!pTop.isEmpty()){
			int size = pTop.size();
			useableCards.add(pTop.remove(size-1));
			size--;
			Card temp;
			while(!pTop.isEmpty()){
				temp = pTop.remove(pTop.size()-1);
				if(temp.value==useableCards.get(useableCards.size()-1).value) {
					useableCards.add(temp);
				}
				else{
					break;
				}
			}
		}
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (Card c : useableCards){
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
		System.out.println("\nHand I am handed:" + hand);
		System.out.println("\nPile I'm given:" + pile.seeThree());
		System.out.println("\nFaceups: " + faceup);
		ArrayList<Card> reserve = (hand.isEmpty()||hand.get(0)==null)?faceup:hand;
		ArrayList<Card> validReserve = new ArrayList<Card>();
		for (Card card : reserve){
			if(pile.validMove(card)){
				validReserve.add(card);
			}
		}
		int moveFour = canCompleteFour(validReserve, pile);
		if(moveFour!=-1){System.out.println("Complete Four");return moveFour;}
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (Card card : validReserve){
			values.add(new Integer(card.value));
		}
		System.out.println("\n Cards in reserve:" + values);
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
		System.out.println("Value of Card I will play: " + value);
		ArrayList<Card> reserve = (hand.isEmpty()||hand.get(0)==null)?faceup:hand;
		for (Card c : reserve){
			if(c.value == value){
				System.out.println("\nCard I am sending: " + c);
				return c;
			}
		}
		return new Card();
	}

	//First three cards in the hand, last three as faceups
	public ArrayList<Card> strategizeFaceups(){
		ArrayList<Card> reserve = new ArrayList<Card>(hand);
		reserve.addAll(faceup);
		ArrayList<Card> goodCards = new ArrayList<Card>();
		Player p = new Player();
		p.sort(reserve);
		//System.out.println(reserve);
		for (int i = 0; i < reserve.size(); i++){
			if(!(reserve.get(i).value == 10 || reserve.get(i).value == 7 || reserve.get(i).value == 2)){
				goodCards.add(reserve.get(i));
			}
		}
		for (int i = 0; i < reserve.size(); i++){
			if(reserve.get(i).value == 10 || reserve.get(i).value == 7 || reserve.get(i).value == 2){
				goodCards.add(reserve.get(i));
			}
		}
		//System.out.println(goodCards);
		return goodCards;

	}

	public static void main(String[] args){
		ArrayList<Card> h = new ArrayList<Card>();
		h.add(new Card("Spades",11));
        h.add(new Card("Spades", 10));
        //h.add(new Card("Hearts", 7));
        h.add(new Card("Hearts", 3));
        ArrayList<Card> f = new ArrayList<Card>();
        f.add(new Card("Spades", 7));
        f.add(new Card("Hearts", 5));
        f.add(new Card("Hearts", 8));
		Pile p = new Pile();
		p.add(new Card("Diamonds", 6));
		p.add(new Card("Clubs", 6));
		p.add(new Card("Diamonds", 5));
		ArrayList<Player> op = new ArrayList<Player>();
		Deck d = new Deck();
		Player player = new Player(true,"test",d);
		ArrayList<Card> ophand = new ArrayList<Card>();
		ophand.add(new Card("Hearts",12));
		player.hand = ophand; 
		//player.facedown = new ArrayList<Card>();
		op.add(player);
		AI driver = new AI(h,f,op);
        //System.out.println(driver.logical(p,3).toString());
        driver.strategizeFaceups();
	}
}
