import java.util.*;

public class AI{

	private boolean nextNextWinning;

	AI() {
		nextNextWinning = false;
	}

	public static boolean canCompleteFour(ArrayList<Card> hand, Pile pile) {
		if(hand.isEmpty()){
			return false;
		}
		hand.addAll(pile.seeThree());
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (Card c : hand){
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

	public static void main(String[] args){
		ArrayList<Card> h = new ArrayList<Card>();
        h.add(new Card("spades", 10));
        h.add(new Card("hearts", 3));
        h.add(new Card("hearts", 4));
		Pile p = new Pile();
		p.add(new Card("spades", 3));
		p.add(new Card("clubs", 3));
		p.add(new Card("diambonds", 3));
        System.out.println(canCompleteFour(h,p));
	}
}

