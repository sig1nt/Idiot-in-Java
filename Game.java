public class Game {
	
	private final static ConsoleIO console = new ConsoleIO("Invalid input.", 0, 80, 0);
	protected String[5] takenNames;

	// Constructor, builds a Deck deck, a Pile pile, and an ArrayList<Player> players
	public Game() {
		Deck deck = new Deck();
		Pile pile = new Pile();
		int humanCount = getPlayerCount(true);
		int cpuCount = getPlayerCount(false);
		ArrayList<Player> players = new ArrayList();
		for (int i=0; i < humanCount; i++) {
			players.add(new Player(true, getPlayerName(i), deck));
		}
		for (int i=0; i < cpuCount; i++) {
			players.add(new CPU("CPU-" + (i+1), deck, players));
		}
    }

	// Determines how many players of each type in Human, CPU to instantiate
	private int getPlayerCount(boolean isHuman) {
		if (isHuman) {
			console.in(int p, "How many human players? ");
		} else {
			console.in(int p, "How many computer players? ");
		}
		return p;
	}

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

	// Takes the list of players w/ sorted hands and determines who goes first.
	private Player goesFirst(ArrayList<Player> players) {
		Card lowCard = players[0].hand[0];
		Player goesFirst;
		ArrayList<String> suits = new ArrayList().addAll(Arrays.asList("Clubs",
				"Diamonds", "Spades", "Hearts"));
		for (Player p : players) {
				c = p.hand[0];
				if (c.value <= lowCard.value) {
					if (c.value == lowCard.value) {
						if (suits.indexOf(c.suit) < suits.indexOf(lowCard.suit)) {
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

	// Eventually, this should close a thread and return to the lobby manager
	private void winGame(Player player) {
		System.out.println("Game over, " + player.name + " wins!");
		System.exit(0);
	}

	/* Loop that executes the actual game.
	 * Iterates through players, wrapping around, and
	 * finishes when a player.move() returns 2
	 */
    public play() {
		int i = players.indexOf(goesFirst(players));
		int moveExitStatus;
		while (true) {
			if (players[i].human) {
				moveExitStatus = players[i].move(pile, deck);
			} else {
				moveExitStatus = players[i].move(pile, deck, players);
			}
			switch (moveExitStatus) {
				case 2:
					winGame(players[i]); // no break b/c exit is a black hole
				case 1:
					break;
				case 2:
					if (i == numPlayers) {
						i = 0;
					} else {
						i++;
					}
					break;
				default:
					break;
			}
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.play();
	}
}
