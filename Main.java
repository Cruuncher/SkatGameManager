import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 
 * The main loop.  Contains no logic and simply creates players and sets them to an instance of Game to play Skat.<br>
 * <br>
 * <em>Currently there is no handling of bad input</em>
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// Read the number of games.
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		int numOfGames = 1;
		try {
			System.out.println("Please enter a number of rounds to play for this game:");
			numOfGames = Integer.parseInt(br.readLine());
			br.close();
			isr.close();
		} catch (Exception e) {
			e.printStackTrace();
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
	}

}

