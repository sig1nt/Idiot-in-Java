public class Game {
	
	private final static ConsoleIO console = new ConsoleIO("Invalid input.", 0, 80, 0);
	private static String[5] takenNames;

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
			console.in(int p, "How many human players? ");
		} else {
			console.in(int p, "How many computer players? ");
		}
		return p;

	// Gets names for human players
	private String getPlayerName(int i) {
		String name = "";
		while (name.length() == 0) {
			name = console.in(name, "Please input your name: ");
			for (int t = i; t >= 0; t--) {
				if (takenNames[t] == name) {
					System.out.println("That name is taken.");
					name = "";
				}
			}	
		}
		takenNames[i] = name;
		return name;
	}

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
	// and deals with a player getting to goAgain
	private int nextPlayer(int i, int numPlayers, boolean goAgain) {
		if (goAgain) {
			return i;
		} else if ( i < numPlayers ) {
			return i++;
		} else {
			return 0;
		}
	}

	// Eventually, this should close a thread and return to the game lobby manager
	private void winGame(Player player) {
		System.out.println("Game over, " + player.name + " wins!");
		System.exit(0);
	}

	/* Loop that executes the actual game.
	 * Iterates through players and 
	 * finishes when one player.move returns 2
	 */
    public static void main() {
		boolean gameOver = false;
		int indexFirst = players.indexOf(goesFirst(players));
		int i;
		int moveExitStatus;
		boolean goAgain = false;
		while (not gameOver) {
			for (i=indexFirst; i < Player.numPlayers; i=nextPlayer(i, Player.numPlayers, goAgain)) {
				goAgain = false;
				moveExitStatus = players[i].move(Game.pile, Game.deck);
				if (moveExitStatus == 2) {
					gameOver = true;
					break;
				} else if (moveExitStatus == 1) {
					goAgain = true;
				}
			}
		}
		winGame(players[i]);
	}
}
