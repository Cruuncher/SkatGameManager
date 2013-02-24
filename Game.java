package edu.piggottk.skat;

/**
 * 
 * The game class contains all the logic for running a game of Skat.  It manages the code flow, sets up new games and validates
 * user input to prevent invalid user input or attempts at cheating.<br>
 * <br>
 * <em>Currently there is no handling of bad input</em>
 * 
 * @author Kelly
 *
 */
public class Game {
	
	private int firstToPlay;
	private int trickNum;
	private int highestBid;
	private int highestBidder;	//Once bidding ends, highestBidder is the declarer. No need to save that twice.
	private IPlayer player0;
	private IPlayer player1;
	private IPlayer player2;
	private int player0GameScore;
	private int player1GameScore;
	private int player2GameScore;
	private Pile player0Hand;
	private Pile player1Hand;
	private Pile player2Hand;
	private Pile player0TricksWon;
	private Pile player1TricksWon;
	private Pile player2TricksWon;
	private Pile cardsPlayed;
	private Pile skat;
	private Deck deck;
	private GameTypeOptions gameType;
	private int baseVal;
	private int multiplier;
	
	/**
	 * Creates three players.
	 */
	public void setPlayers(IPlayer player0, IPlayer player1, IPlayer player2){
		// Create three players, given three different Player classes created by the groups.
		// For now, it uses DummyPlayer for all of them, which I created.
		this.player0 = player0;
		this.player1 = player1;
		this.player2 = player2;
	}
	
	/**
	 * Tells each player their index.
	 */
	private void assignIndexes(){
		player0.assignIndex(0);
		player1.assignIndex(1);
		player2.assignIndex(2);
	}
	
	/**
	 * Assign a specific Player to be the first one to play their turn.
	 * @param playerIndex the index of the specific player.
	 */
	private void assignFirstToPlay(int playerIndex){
		firstToPlay = playerIndex;
	}
	
	/**
	 * Create an empty hand pile, and an empty tricks pile for each player.
	 */
	private void createPlayerPiles(){
		player0Hand = new Pile();
		player1Hand = new Pile();
		player2Hand = new Pile();
		
		player0TricksWon = new Pile();
		player1TricksWon = new Pile();
		player2TricksWon = new Pile();
	}
	
	/**
	 * Create an empty skat pile and an empty pile for cards that have been played.
	 */
	private void createGamePiles(){
		skat = new Pile();
		cardsPlayed = new Pile();
	}
	
	/**
	 * Create the deck, and shuffle the cards.
	 */
	private void prepareDeck(){
		deck = new Deck();
		// TODO
		// Create all the cards.
		// Put them in the deck.
		deck.shuffle();
	}
	
	/**
	 * Deal three consecutive cards to each player, two cards to the skat,
	 * four consecutive cards to each player, three consecutive cards to each player.
	 */
	private void dealCards(){
		deck.dealCards(player0Hand, 3);
		deck.dealCards(player1Hand, 3);
		deck.dealCards(player2Hand, 3);
		deck.dealCards(skat, 2);
		deck.dealCards(player0Hand, 4);
		deck.dealCards(player1Hand, 4);
		deck.dealCards(player2Hand, 4);
		deck.dealCards(player0Hand, 3);
		deck.dealCards(player1Hand, 3);
		deck.dealCards(player2Hand, 3);
	}
	
	/**
	 * Begins bidding between the three players. Assigns a declarer when bidding ends.
	 */
	private void initiateBidding(){
		// TODO
		// One by one, ask each player to bid.
		// Players must either bid higher than the current highest bid, or pass.
		// Once two of the three players have passed, assign the remaining player with the highest bid to be the declarer.
		// **NOTE** Use proper bidding with Forehand/Middlehand/Rearhand?????
	}
	
	/**
	 * Defines the type of game to be played, including all applicable variables.
	 */
	private void setGameType(){
		// TODO
		// Ask the declarer (highestBidder) if they want to see the skat.
		// If yes, show them the skat.
		// Ask the declarer which type of game they want to play.
		// (Have them create a GameTypeOptions object)
		// Confirm that their GameTypeOptions object is a valid combination of game types.
		// If valid, update multiplier and baseVal accordingly.
		// Push GameTypeOptions object to each player.
	}
	
	private boolean isValidGameType(GameTypeOptions gameType){
		// Note: this is a helper function to be used in setGameType to confirm validity.
		// TODO
		// Based on the rules of Skat, determine whether the GameTypeOptions object the declarer created is valid
		// Return true if it is valid, and false otherwise.
		return true;
	}
	
	/**
	 * Each player plays a card into the cardsPlayed Pile.
	 */
	private void playTrick(){
		// TODO
		// Ask whichever player is firstToPlay to playTurn.
		// Confirm that the card they chose to play is valid.
		// Remove the card from their hand pile and add it to the cardsPlayed pile
		// Continuing clockwise...
		// Ask the next player to playTurn.
		// Confirm that the card they chose to play is valid.
		// Remove the card from their hand pile and add it to the cardsPlayed pile.
		// Continuing clockwise...
		// Ask the next player to playTurn.
		// Confirm that the card they chose to play is valid.
		// Remove the card from their hand pile and add it to the cardsPlayed pile.
	}
	
	private boolean isValidCard(Card card){
		// Note: this is a helper function to be used in playTrick to confirm validity.
		// TODO
		// Based on the current Game Type, and the cards that have already been played (if any),
		// determine whether or not the given card is valid.
		// Return true if it is valid, and false otherwise.
		return true;
	}
	
	/**
	 * Determine which player wins the trick, move the cards they won to their TricksWon pile,
	 * and let each player know who won the trick and which cards they won.
	 */
	private void winTrick(){
		// TODO
		// Examine cardsPlayed pile and determine which player won the trick.
		// Change firstToPlay to be the index of the player who won the trick.
		// Show each player who won the trick and which cards were played in the trick (call endTrick on each player).
		// Move cards in cardsPlayed pile to the TricksWon pile of the player who won.
	}
	
	/**
	 * Determine a given card's strength, based on the current game type.
	 * Strength: Card A beats Card B if Card A's strength is greater than Card B's strength.
	 * @param card The card to determine the strength of.
	 * @return the card's strength in the context of the current game type.
	 */
	private int cardStrength(Card card){
		// TODO
		// For a given card and a defined Game Type, determine the strength of the card.
		// Return the strength of the card.
		return 0;
	}
	
	/**
	 * Determine how many card points a given player has won.
	 * @param playerIndex The player whose card points to count.
	 * @return The total card points the player has won.
	 */
	private int countCardPoints(int playerIndex){
		// TODO
		// For each Card in the given player's TricksWon pile, sum the card points.
		// Return the sum.
		return 0;
	}
	
	/**
	 * Determine the value of the game.
	 * @param baseVal The base value. 
	 * @param multiplier The multiplier.
	 * @return The game value.
	 */
	private int gameValue(int baseVal, int multiplier){
		// TODO
		// Determine the value of this game using the base value and the multiplier.
		// Recall that Null games have set values and dont use a base value and multiplier.
		// Return the game value.
		return 0;
	}
	
	/**
	 * Add or subtract points from the declarer's GameScore.
	 */
	private void assignGamePoints(){
		// TODO
		// Based on the number of card points the declarer won, the declarer's final bid, and the game value,
		// determine whether or not the declarer wins or loses game points,
		// and add or subtract the appropriate number to/from their GameScore.
	}
	
	/**
	 * Performs general setup necessary to start a game of skat.
	 */
	public void setupGame(){
		// TODO
		// Perform pre-game setup.
		// Deal cards, manage bidding, get declarer to set game type. 
	}
	
	/**
	 * The main playing aspect of the game.
	 * Manages all ten tricks of a game of skat.
	 */
	public void beginPlay(){
		// TODO
		// Play the 10 tricks of a game of skat.
	}
	
	/**
	 * Determine whether or not the declarer won, update game score accordingly.
	 * Perform general end-game cleanup in order to prepare for another game of skat.
	 */
	public void endPlay(){
		// TODO
		// Once 10 tricks have been played, count card points, determine whether or not the declarer won, update game scores.
		// Perform end-of-game cleanup.
	}
}
