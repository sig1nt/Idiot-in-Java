public class Game {
	
	public Game() {
		Deck deck = new Deck();
		Pile pile = new Pile();
		Player[Player.numPlayers] players;
		for (int i=0; i < Player.numHuman; i++) {
			players[i] = new Player(true, getName(i), deck);
		}
		for (int i=0; i < Player.numCPUs; i++) {
			players[i] = new Player(false, "CPU-" + i, deck);
		}
    }

	private String getName(int i) {
		// TODO
		// Console out "Please input your name: "
		// name = Console in
		// Eventually, make it parameterless
		return "Test-" + i;

	/* Takes the list of players with sorted hands and determines who goes first.
	 * @param Player[]
	 * @return Player
	 */
	private Player goesFirst(Player[] players) {
		Card lowCard = players[0].hand[0];
		Player goesFirst;
		String[] suits_array = {"Clubs", "Diamonds", "Spades", "Hearts"};
		List<String> suits = Arrays.asList(suits_array); // Is this how I arrayList?
		for (Player p : players) {
				c = p.hand[0];
				if (c.value <= lowCard.value) {
					if (c.value == lowCard.value ) {
						if (suits.indexOf(c.suit) <  suits.indexOf(lowCard.suit)) {
							lowCard = c;
							goesFirst = p;
						}
					}
					else {
						lowCard = c;
						goesFirst = p;
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
		boolean gameOver = false;
		// TODO determine who goes first, and resulting play order clockwise
		while (not gameOver) {
			// TODO
		}
	}
}
