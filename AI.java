import java.util.*;

public class AI{

	private boolean nextNextWinning;
	private ArrayList<Card> hand, faceup, opFaceup;
	private int opFaceDown, opHand;
	private Pile pile;

	public AI(ArrayList<Card> hand, ArrayList<Card> faceup, ArrayList<Card> opFaceup, int opFaceDown, int opHand, Pile pile) {
		nextNextWinning = false;
		this.hand = hand;
		this.faceup = faceup;
		this.opFaceup = opFaceup;
		this.opFaceDown = opFaceDown;
		this.opHand = opHand;
		this.pile = pile;
	}

	private void update(opFaceDown, opHand){
		this.opFaceDown = opFaceDown;
		this.opHand = opHand;
	}

	private boolean canCompleteFour(ArrayList<Card> hand, Pile pile) {
	//checks if the AI can complete a four of a kind
		if(hand.isEmpty()){return false;}
		ArrayList<Card> temp = new ArrayList<Card>(hand);
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
		if(handCount==0||fdCount==0){
			return false;
		}
		return ((handCount+fdCount < 3) && (handCount == 0 || fdCount == 0));
	}

	public int logical(){
		ArrayList<Card> reserve = (hand.isEmpty())?faceup:hand;
		if(canCompleteFour(reserve, pile)){
			return 100;
			//100 means complete the four
		}
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (Card card : reserve){
			values.append(new Integer(card.value));
		}
		Collections.sort(values);
		if(isWinning(opHand,opFaceDown)) {

		}
	}

	public static void main(String[] args){
		ArrayList<Card> h = new ArrayList<Card>();
        h.add(new Card("spades", 10));
        h.add(new Card("hearts", 3));
        h.add(new Card("hearts", 4));
        ArrayList<Card> f = new ArrayList<Card>();
        f.add(new Card("spades", 7));
        f.add(new Card("hearts", 5));
        f.add(new Card("hearts", 8));
		Pile p = new Pile();
		p.add(new Card("spades", 3));
		p.add(new Card("clubs", 3));
		p.add(new Card("diamonds", 3));
		AI driver = new AI(h,f,new ArrayList<Card>(),0,0,p);
        System.out.println(driver.logical());
	}
}
