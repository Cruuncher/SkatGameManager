

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
	private static final int PLAYER_COUNT = 3;
	
	private int firstToPlayIndex;
	private int trickNum;
	private int highestBid;
	private int highestBidder;	//Once bidding ends, highestBidder is the declarer. No need to save that twice.
	private PlayerInfo[] players;
	private Pile cardsPlayed;
	private Pile skat;
	private Deck deck;
	private GameTypeOptions gameType;
	private int baseVal;
	private int multiplier;
	
	public Game() {
		// Initialize our player array.
		this.players = new PlayerInfo[PLAYER_COUNT];
	}
	
	/**
	 * Initializes new instances of our player information for our players.
	 */
	public void resetPlayers(){
		// Initialize our player instances.
		for(int i = 0; i < players.length; i++)
			players[i] = new PlayerInfo();
	}
	
	/**
	 * Tells each player their index.
	 */
	private void assignIndexes(){
		// Loop through our player information, grab our player and set their index.
		for(int i = 0; i < players.length; i++)
			players[i].getPlayer().assignIndex(i);
	}
	
	/**
	 * Create an empty skat pile and an empty pile for cards that have been played, and create a populated deck.
	 */
	private void createGamePiles(){
		skat = new Pile();
		cardsPlayed = new Pile();
		deck = new Deck();
	}
	
	/**
	 * Deal three consecutive cards to each player, two cards to the skat,
	 * four consecutive cards to each player, three consecutive cards to each player.
	 */
	private void dealCards(){
		// Give three cards to each player.
		for(int i = 0; i < players.length; i++)
			deck.dealCards(players[i].getHandPile(), 3);
		
		// Put two cards in the skat.
		deck.dealCards(skat, 2);
		
		// Give four cards to each player again.
		for(int i = 0; i < players.length; i++)
			deck.dealCards(players[i].getHandPile(), 4);
		
		// Give three cards to each player again.
		for(int i = 0; i < players.length; i++)
			deck.dealCards(players[i].getHandPile(), 3);
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
		// If valid, update gameType, multiplier and baseVal variables accordingly.
		// Push GameTypeOptions object to each player.
	}
	
	/**
	 * Determines if the given game type is valid based on the rules of Skat.
	 * @param gameType the type of game to be evaluated
	 * @return True if gameType is valid, and False otherwise.
	 */
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
	
	/**
	 * Determines if the given card is valid.
	 * @param card the Card to evaluate.
	 * @return True if the card is value, and False otherwise.
	 */
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
	 * Strength: Card A beats Card B if Card A's strength is greater than Card B's strength in this type of game.
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
		// Setup our players
		this.resetPlayers();
		this.assignIndexes();
	}
	
	/**
	 * The main playing aspect of the game.
	 * Manages bidding, and all ten tricks of a game of skat.
	 * Performs end-of-game calculations and cleanup.
	 */
	public void playRound(){
		// Reset our players piles (hand and won pile) -- necessary if this is not the first game played with these Players.
		 for(int i = 0; i < players.length; i++)
			players[i].resetPiles();
		
		// Create the game piles
		this.createGamePiles();
		
		// TODO
		// Shuffle and deal cards
		// Manage bidding and game type declaration. 
		// Play the 10 tricks of a game of skat.
		// Once 10 tricks have been played, count card points, determine whether or not the declarer won, update game scores.
		// Perform end-of-game cleanup.
	}
}
