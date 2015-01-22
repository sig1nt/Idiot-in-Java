public class Game {
	
	public Game(int humanCount, int cpuCount) {
		Deck deck = new Deck();
		Pile pile = new Pile();
		Player[humanCount+cpuCount] players;
		// Create # of human players w/ hand, fu, fd
		// Create # of cpu players w/ hand, fu, fd
		// Stuff them all into players array
    }

	/* Takes the list of players and determines who goes first.
	 * @param Player[]
	 * @return Player
	 */
	private Player goesFirst(Player[] players) {
		Card lowCard = players[0]; // TODO min(players[0].hand)
		Player goesFirst;
		for (Player p : players) { // way to collapse this down?
			for (Card c : p.hand) {
				if (c.value <= lowCard.value) {
					if (c.value == lowCard.value ) {
						// TODO if (c.suit < lowCard.suit) {
						lowCard = c;
						goesFirst = p;
					}
					else {
						lowCard = c;
						goesFirst = p;
					}
				}
		    }
		}
		return goesFirst;
	}

	/* Loop that executes the actual game.
	 * Iterates through players and 
	 * finishes when one has no hand and no fd
	 */
    public static void main() {
		boolean gameOver = false; // A bool is fine for this, right?
		while (not gameOver) {
			// TODO
		}
	}
}
