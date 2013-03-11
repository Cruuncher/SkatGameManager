package skatgame;

import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * The main entry point.  Contains no logic and simply creates players and sets them to an instance of Game to play Skat.<br>
 */
public class Main {

	/**
	 * Our main entry point into the application
	 * @param args Command line arguments given to the application (unused).
	 */
	public static void main(String[] args) {
		
		// Read the number of games.
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int numOfGames = 1;
		int tries = 0;
		while(true) {
			try {
				System.out.println("Please enter a number of rounds to play for this game:");
				numOfGames = Integer.parseInt(br.readLine());
				br.close();
				isr.close();
				break;
			} catch (Exception e) {
				tries++;
				if(tries == 3) {
					System.out.println("Failed to enter a number of rounds after 3 tries.. Terminating..");
					System.exit(0);
				}
				System.out.println("Invalid number entered, please try again..");
			}
		}
		
		// Set up our game, and set our player instances.
		Game game = new Game();
		IPlayer player1 = new DummyPlayer();
		IPlayer player2 = new DummyPlayer();
		IPlayer player3 = new DummyPlayer();
		game.setupGame(player1, player2, player3);
		
		// Play as many rounds as desired.
		for(int i = 0; i < numOfGames; i++) {
			game.playRound();
		}

		// Obtain our statistics.
		GameStats gameStats = game.concludeGame();
		System.out.print(gameStats.toString());
	}

}

