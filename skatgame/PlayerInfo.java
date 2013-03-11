package skatgame;

/**
 * 
 * PlayerInfo objects organize and contain all information pertaining to a particular IPlayer, including the instance of that IPlayer.<br>
 * 
 */
public class PlayerInfo {
	
	private IPlayer player;
	private int gameScore;
	private Pile hand;
	private Pile tricksWon;
	
	/**
	 * Creates a new PlayerInfo object which initializes an object of type IPlayer, initializes gameScore to 0,
	 * and creates the relevant player piles.
	 */
	public PlayerInfo(IPlayer playerInstance) {
		// Initialize our player (swapping between implementations of IPlayer would be here).
		this.player = playerInstance;
		this.gameScore = 0;
		this.resetPiles();
	}
	
	/**
	 * Getter method to get the IPlayer object.
	 * @return the instance of this player.
	 */
	public IPlayer getPlayer() {
		return this.player;
	}
	
	/**
	 * Getter method to get the player's game score.
	 * @return the player's game score.
	 */
	public int getGameScore() {
		return this.gameScore;
	}
	
	/**
	 * Setter method to update the player's game score.
	 * @param newScore the player's new updated score.
	 */
	public void setGameScore(int newScore) {
		this.gameScore = newScore;
	}
	
	/**
	 * Getter method to get the player's hand.
	 * @return the player's hand Pile.
	 */
	public Pile getHandPile() {
		return this.hand;
	}
	
	/**
	 * Getter method to get the player's tricks they've won.
	 * @return the player's tricksWon Pile.
	 */
	public Pile getTricksWonPile() {
		return this.tricksWon;
	}
	
	/**
	 * Creates new empty piles for the player's hand and tricksWon.
	 * Intended to be used at the beginning of a game before cards are dealt.
	 */
	public void resetPiles() {
		this.hand = new Pile();
		this.tricksWon = new Pile();
	}
}
