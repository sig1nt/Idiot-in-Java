import java.util.*;

public class Game {
	
	private final static ConsoleIO console = new ConsoleIO("Invalid input.", 0, 80, 0);
	protected String[] takenNames = new String[5];
	ArrayList<Player> players = new ArrayList<Player>();
	Deck deck = new Deck();
	Pile pile = new Pile();
	int humanCount;
	int cpuCount;
	int numPlayers;

	/* Constructor, builds a Deck deck, a Pile pile,
	 * and an ArrayList<Player> players
	 */
	public Game() {
		humanCount = getPlayerCount(true);
		cpuCount = getPlayerCount(false);
		numPlayers = humanCount+cpuCount;
		if (numPlayers == 0) {
			System.out.println("0 total players, so game over.");
			System.exit(1);
		} else if  (numPlayers == 1) {
			System.out.println("1 player, so game over.");
			System.out.println("Not much fun to play with yourself, eh?");
			System.exit(1);
		}
		for (int i=0; i < humanCount; i++) {
			players.add(new Player(true, getPlayerName(i), deck));
		}
		for (int i=0; i < cpuCount; i++) {
			players.add(new CPU("CPU-" + (i+1), deck, players));
		}
    }

	// Determines how many players of each type in Human, CPU to instantiate
	private int getPlayerCount(boolean isHuman) {
		int p;
		if (isHuman) {
			p = (int) console.in("How many human players? ", 0);
		} else {
			p = (int) console.in("How many computer players? ", 0);
		}
		return p;
	}

	// Gets names for human players
	private String getPlayerName(int i) {
		String name = "";
		while (name.length() == 0) {
			name = (String) console.in("Please input your name: ", "");
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
	private int goesFirst(ArrayList<Player> players) {
		Player goesFirst = players.get(0);
		Card lowCard = goesFirst.hand.get(0);
		Card c;
		ArrayList<String> suits = new ArrayList<String>();
		suits.addAll(Arrays.asList("Clubs", "Diamonds", "Spades", "Hearts"));
		for (Player p : players.subList(1, players.size()-1)) {
			c = p.hand.get(0);
			if (c.value < lowCard.value) {
				lowCard = c;
				goesFirst = p;
			} else if (c.value == lowCard.value
					&& suits.indexOf(c.suit) < suits.indexOf(lowCard.suit)) {
				lowCard = c;
				goesFirst = p;
			}
		}
		return players.indexOf(goesFirst);
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
    public void play() {
		int i = goesFirst(players);
		int moveExitStatus;
		console.typeln(players.get(i).name + "'s turn");
		moveExitStatus = players.get(i).firstMove(pile, deck);
		if (moveExitStatus == 0) {
			if (i == numPlayers-1) {
				i = 0;
			} else {
				i++;
			}
		}
		while (true) {
			console.typeln(players.get(i).name + "'s turn");
			moveExitStatus = players.get(i).move(pile, deck);
			//System.out.println("Deck size: " + deck.empty());
			switch (moveExitStatus) {
				case 0:
					if (i == numPlayers-1) {
						i = 0;
					} else {
						i++;
					}
					break;
				case 2:
					winGame(players.get(i));
				case 1:
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
