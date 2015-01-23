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
		for(int i = 0; i < ; i++){
			System.out.println(h.get(i).toString());
		}
		return true;
	}

	public static void main(String[] args){
		ArrayList<Card> h = new ArrayList<Card>();
        h.add(new Card("spades", 10));
        h.add(new Card("hearts", 4));
        h.add(new Card("hearts", 3));
		Pile p = new Pile();
		/*Iterator<Card> itor = h.iterator();
		for(Card i = itor.next(); itor.hasNext();itor.next()){
			System.out.println(i.toString());
		}*/
		System.out.println("----------------------------------------------");
        canCompleteFour(h,p);
	}
}

