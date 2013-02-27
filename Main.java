
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
		// TODO
		// Have main accept an input, convert to integer: int numOfGames
		
		Game game = new Game();
		game.setupGame();
		
		// TODO
		// Loop this method as many times as specified by main input numOfGames.
		game.playRound();
	}

}

