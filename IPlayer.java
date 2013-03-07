
/**
 * 
 * An interface for players to implement.  Contains the necessary methods to be called by game to play Skat.<br>
 * <br>
 *
 */
public interface IPlayer {
	
	/**
	 * Assign this player an index.
	 * (Skat is a 3-player game, so the index will be either 0, 1, or 2)
	 * @param playerIndex The player's assigned index.
	 */
	public void assignIndex(int playerIndex);
	
	/**
	 * Determine whether this player wants to bid, match, or pass.
	 * @param currentHighestBid The current highest bid that the player must pass or outbid/match.
	 * @param hand The player's hand.
	 * @param canMatch True if this player is allowed to match the current highest bid, and false if they must outbid the current highest bid.
	 * @return True if this player wants to bid/match, and false if they want to pass. 
	 */
	public boolean bid(int currentHighestBid, Pile hand, boolean canMatch);
	
	/**
	 * (For the declarer player only)
	 * Ask if the player wants to view the skat.
	 * @param hand The player's hand.
	 * @return True if the player wants to view the skat, and False otherwise.
	 */
	public boolean decideTakeSkat(Pile hand);
	
	/**
	 * (For the declarer player only)
	 * Give the declarer the skat.
	 * The declarer should examine the Cards in the skat, and if they like, replace the Cards in the skat with Cards from their hand.
	 * @param hand The players current hand.
	 * @param skat The skat Pile containing two cards.
	 * @return The skat Pile containing two cards.
	 */
	public Pile giveSkat(Pile hand, Pile skat);
	
	/**
	 * (For the declarer player only)
	 * Choose the type of game to play.
	 * @param hand The player's hand.
	 * @return A GameTypeOptions object that indicates how the game should be played.
	 */
	public GameTypeOptions decideGameType(Pile hand);
	
	/**
	 * Tell the player the type of game being played, and who declared the game type.
	 * Defender players should store the declarer's playerIndex, so they know (by process of elimination)
	 * the playerIndex of the other defender.
	 * @param gameType A GameTypeOptions object designating the current style of game.
	 * @param declarerIndex The player index of the declarer.
	 */
	public void setGameType(GameTypeOptions gameType, int declarerIndex);
	
	/**
	 * If the declarer chooses an Ouvert game, this method will be used to pass the other players the declarer's hand
	 * to simulate the concept of the declarer playing an open-hand game.
	 * This method will be called before the players are asked to play their turns, so they may use this information in their strategy.
	 * @param declHand the declarer's hand.
	 */
	public void giveDeclarersHand(Pile declHand);
	
	/**
	 * Choose a Card from your hand to play this turn.
	 * @param hand The player's current hand.
	 * @param cardsPlayed The cards that have already been played this round, indexed in the order they were played.
	 * @param whoPlayedFirstCard The index of the player who played the first card this round.
	 * @return Returns the Card you wish to play this round.
	 */
	public Card playTurn(Pile hand, Pile cardsPlayed, int whoPlayedFirstCard);

	/**
	 * Tells the player who won the trick this round, and which cards were played in the trick.
	 * @param cardsPlayed The pile of cards played, indexed in the order they were played.
	 * @param whoWonIndex The index of the player who won the trick.
	 */
	public void endTrick(Pile cardsPlayed, int whoWonIndex);

	/**
	 * Tell the player whether or not the declarer won, and the updated game scores of each player.
	 * @param decWon True if the declarer won, and False otherwise.
	 * @param scores The updated game scores of each player, organized by player index.
	 */
	public void endGameInfo(boolean decWon, int[] scores);
	
}
