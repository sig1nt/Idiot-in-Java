public class Game {
	
	// Constructor, builds a Deck deck, a Pile pile, and an ArrayList<Player> players
	public Game() {
		Deck deck = new Deck();
		Pile pile = new Pile();
		int humanCount = getPlayerCount(true);
		int cpuCount = getPlayerCount(false);
		Player[humanCount+cpuCount] playersArray;
		for (int i=0; i < humanCount; i++) {
			playersArray[i] = new Player(true, getPlayerName(i), deck);
		}
		for (int i=0; i < cpuCount; i++) {
			playersArray[i] = new Player(false, "CPU-" + i, deck);
		}
		ArrayList<Player> players = new ArrayList().addAll(playersArray);
    }

	// Determines how many players of each type in Human, CPU to instantiate
	private int getPlayerCount(boolean isHuman) {
		if (isHuman) {
		    // TODO Console out "How many human players? "
			// return <int> Console in
		} else {
			// TODO Console out "How many cpu players? "
			// return <int> Console in
		}
		return 1;

	// Gets names for human players
	private String getPlayerName(int i) {
		// TODO
		// Console out "Please input your name: "
		// name = Console in
		// Eventually, make it parameterless
		return "Test-" + i;

	// Takes the list of players with sorted hands and determines who goes first.
	private Player goesFirst(ArrayList<Player> players) {
		Card lowCard = players[0].hand[0];
		Player goesFirst;
		ArrayList<String> suits = new ArrayList().addAll(Arrays.asList("Clubs", "Diamonds", "Spades", "Hearts"));
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

	// Lets player iteration wrap around the ArrayList<Player> players
	private int nextPlayer(int i, int numPlayers, boolean goAgain) {
		if (goAgain) {
			return i;
		} else if ( i < numPlayers ) {
			return i++;
		} else {
			return 0;
		}
	}

	/* Loop that executes the actual game.
	 * Iterates through players and 
	 * finishes when one has no hand and no fd
	 */
    public static void main() {
		boolean gameOver = false;
		int indexFirst = Game.players.indexOf(goesFirst(Game.players));
		int i;
		int moveExitStatus;
		boolean goAgain = false;
		while (not gameOver) {
			if (moveExitStatus == 2) {
				winGame(players[i]);
			}
			for (i=indexFirst; i < Player.numPlayers; i=nextPlayer(i, Player.numPlayers, goAgain)) {
				goAgain = false;
				moveExitStatus = players[i].move(Game.pile, Game.deck);
				if (moveExitStatus == 2) {
					break;
				} else if (moveExitStatus == 1) {
					goAgain = true;
				}
			}
		}
	}
}
