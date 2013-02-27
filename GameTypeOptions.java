
/**
 * 
 * The GameTypeOptions class allows for the creation of an object which will represent the style of play 
 * during the current game of skat.<br>
 * <br>
 *
 */
public class GameTypeOptions {

	private SkatHandType handType;
	private GameType gameType;
	private TrumpSuit trumpSuit;
	private boolean ouvert;
	private boolean schneider;
	private boolean schwarz;
	
	/**
	 * The constructor for a GameTypeOptions object. 
	 * Only the declarer player should use this constructor.
	 * @param handtype Skat game or Hand game.
	 * @param gametype Suit game, Grand game, or Null game.
	 * @param trumpsuit No trump, Clubs, Spades, Hearts, or Diamonds.
	 * @param _ouvert True to set as an Ouvert game, False otherwise.
	 * @param _schneider True to set as a Schneider game, False otherwise.
	 * @param _schwarz True to set as a Schwarz game, False otherwise.
	 */
	public GameTypeOptions(SkatHandType handtype, GameType gametype, TrumpSuit trumpsuit, boolean _ouvert, boolean _schneider, boolean _schwarz){
		handType = handtype;
		gameType = gametype;
		trumpSuit = trumpsuit;
		ouvert = _ouvert;
		schneider = _schneider;
		schwarz = _schwarz;
	}
	
	public enum SkatHandType {
		Hand,
		Skat
	}
	public enum GameType {
		Suit,
		Grand,
		Null
	}
	public enum TrumpSuit {
		None,
		Clubs,
		Spades,
		Hearts,
		Diamonds
	}

	/**
	 * Getter method to let players see if it's a Suit, Grand, or Null game.
	 * @return the current game type.
	 */
	public GameType getGameType(){
		return this.gameType;
	}
	
	/**
	 * Getter method to let players see the trump suit of the game.
	 * @return the current trump suit.
	 */
	public TrumpSuit getTrumpSuit(){
		return this.trumpSuit;
	}
	
	/**
	 * Getter method to let players know if the declarer declared Ouvert.
	 * @return true if the declarer declared Ouvert, and false otherwise.
	 */
	public boolean getOuvert(){
		return this.ouvert;
	}
	
	/**
	 * Getter method to let players know if the declarer declared Schneider.
	 * @return true of the declarer declared Schneider, and false otherwise.
	 */
	public boolean getSchneider(){
		return this.schneider;
	}
	
	/**
	 * Getter method to let players know if the declarer declared Schwarz.
	 * @return true if the declarer declared Schwarz, and false otherwise.
	 */
	public boolean getSchwarz(){
		return this.schwarz;
	}
}