import java.util.ArrayList;
import java.util.List;

/**
 * 
 * The game class contains all the logic for running a game of Skat. It manages
 * the code flow, sets up new games and validates user input to prevent invalid
 * user input or attempts at cheating.<br>
 * <br>
 * <em>Currently there is no handling of bad input</em>
 * 
 */
public class Game {
	private static final int PLAYER_COUNT = 3;
	private static final int[] LEGAL_BID_VALUES = { 18, 20, 22, 23, 24, 27, 30,
			33, 35, 36, 40, 44, 45, 46, 48, 50, 54, 55, 59, 60, 63, 66, 70, 72,
			77, 81, 84, 88, 90, 96, 99, 100, 108, 110, 120, 121, 132, 144, 160,
			168, 192 };

	private int dealer;
	private int firstToPlayIndex;
	private int trickNum;
	private int highestBid;
	private int highestBidder; // Once bidding ends, highestBidder is the
								// declarer. No need to save that twice.
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
	private void resetPlayers(IPlayer player1, IPlayer player2, IPlayer player3) {
		// Initialize our player instances.
		players[0] = new PlayerInfo(player1);
		players[1] = new PlayerInfo(player2);
		players[2] = new PlayerInfo(player2);
	}

	/**
	 * Tells each player their index.
	 */
	private void assignIndexes() {
		// Loop through our player information, grab our player and set their
		// index.
		for (int i = 0; i < players.length; i++)
			players[i].getPlayer().assignIndex(i);
	}

	/**
	 * Create an empty skat pile and an empty pile for cards that have been
	 * played, and create a populated deck.
	 */
	private void createGamePiles() {
		skat = new Pile();
		cardsPlayed = new Pile();
		deck = new Deck();
	}

	/**
	 * Deal three consecutive cards to each player, two cards to the skat, four
	 * consecutive cards to each player, three consecutive cards to each player.
	 */
	private void dealCards() {
		// Give three cards to each player.
		for (int i = 0; i < players.length; i++)
			deck.dealCards(players[i].getHandPile(), 3);

		// Put two cards in the skat.
		deck.dealCards(skat, 2);

		// Give four cards to each player again.
		for (int i = 0; i < players.length; i++)
			deck.dealCards(players[i].getHandPile(), 4);

		// Give three cards to each player again.
		for (int i = 0; i < players.length; i++)
			deck.dealCards(players[i].getHandPile(), 3);

		// Note, we could've just dealt 10 cards straight up to the player..
		// We didn't do this to maintain the traditional conventions, in-case
		// it would've ended up with deducted marks on our part.
	}

	/**
	 * Given a current bid integer, grabs the next possible bid, -1 if it's the last bid already.
	 * @param currentBid The current bid we had, used to obtain the next bid.
	 * @return Returns the next bid following currentBid.
	 */
	private int nextBid(int currentBid) {
		// Loop for all of our bid values.
		for (int i = 0; i < LEGAL_BID_VALUES.length; i++) {
			// If we found the bid
			if (LEGAL_BID_VALUES[i] == currentBid) {
				// If it's the last bid, return -1.
				if (LEGAL_BID_VALUES[i] == LEGAL_BID_VALUES[LEGAL_BID_VALUES.length - 1])
					return -1;
				// Otherwise return the next bid
				return LEGAL_BID_VALUES[i + 1];
			}
		}
		return -2; // Shouldn't ever happen
	}

	/**
	 * Begins bidding between the three players. Assigns a declarer when bidding
	 * ends.
	 */
	private void initiateBidding() {
		int frontHand = (dealer + 1) % PLAYER_COUNT;
		int middleHand = (dealer + 2) % PLAYER_COUNT;
		int rearHand = (dealer + 3) % PLAYER_COUNT;

		boolean frontHandWonFirst = true; // set these defaults to the most
											// likely (least likely to have to
											// change them)
		boolean rearHandWon = false;

		int currentBid = 18; //
		int who = -1; // the person who made the highest bid so far. -1 means no
						// bid yet.

		// bid between middleHand and frontHand, until one of them passes.
		while (true) {
			PlayerInfo pi = players[middleHand];
			IPlayer player = pi.getPlayer();
			boolean canMatch = !(who == frontHand); // we can match so long as
													// frontHand doesn't hold
													// the highest bid

			if (player.bid(currentBid, pi.getHandPile(), canMatch)) {
				if (canMatch)
					who = middleHand;
				else {
					int t = nextBid(currentBid);
					if (t == -1) {
						// TODO report non-critical error
						// System.out.println("Player " + middleHand +
						// " forced to pass, as they cannot bid that high");
						break; // frontHand won this bidding, and it's defaulted
								// to true
					} else {
						currentBid = t;
						who = middleHand;
					}
				}

			} else
				break; // frontHand won this bidding, and it's defaulted to true

			pi = players[frontHand];
			player = pi.getPlayer();
			canMatch = true;

			if (player.bid(currentBid, pi.getHandPile(), canMatch))
				who = frontHand;
			else {
				frontHandWonFirst = false; // tell the next loop that front hand
											// lost
				break;
			}
		}
		// one person has passed
		int otherBidder = frontHand
				+ ((!frontHandWonFirst ? 1 : 0) % PLAYER_COUNT);

		while (true) {
			PlayerInfo pi = players[rearHand];
			IPlayer player = pi.getPlayer();
			boolean canMatch = !(who == otherBidder);

			if (player.bid(currentBid, pi.getHandPile(), canMatch)) {
				if (canMatch)
					who = rearHand;
				else {
					int t = nextBid(currentBid);
					if (t == -1) {
						// TODO report non-critical error
						// System.out.println("Player " + rear +
						// " forced to pass, as they cannot bid that high");
						break; // frontHand won this bidding, and it's defaulted
								// to true
					} else {
						currentBid = t;
						who = rearHand;
					}
				}
			} else
				break; // rearHandWon=false;

			pi = players[otherBidder];
			player = pi.getPlayer();
			canMatch = true;

			if (player.bid(currentBid, pi.getHandPile(), canMatch))
				who = otherBidder;
			else {
				rearHandWon = true;
				break;
			}
		}

		if (rearHandWon) {
			highestBidder = rearHand;
		} else {
			if (frontHandWonFirst) {
				highestBidder = frontHand;
			} else {
				highestBidder = middleHand;
			}
		}

	}

	/**
	 * Defines the type of game to be played, including all applicable
	 * variables.
	 */
	private void setGameType() {
		// Ask the declarer (highestBidder) if they want to see the skat.
		PlayerInfo declarerInfo = this.players[this.highestBidder];
		IPlayer declarerPlayer = declarerInfo.getPlayer();
		Pile declarerHand = declarerInfo.getHandPile();
		boolean viewSkat = declarerPlayer.decideTakeSkat(declarerHand.copy());

		// If they want to view the skat..
		if (viewSkat) {
			// Let them decide on what cards to take.
			Pile newSkat = declarerPlayer.giveSkat(declarerHand.copy(),
					skat.copy());

			// Verify skat length.
			if (newSkat.getNumCards() != 2) {
				// TODO: Skat is invalid size, report critical error to stats.
				return;
			}

			// TODO: Do the actual moving and verification of cards.

			// Loop through the cards of the old skat
			int z = 0;
			for (int i = 0; i < skat.getNumCards(); i++) {
				// If the new skat doesn't contain an old skat card.
				if (!newSkat.containsCard(skat.getCard(i))) {
					Card cardReplaced = skat.getCard(i);
					Card replacedBy = null;
					// There must've been a card we replaced it with.
					for (int x = z; x < newSkat.getNumCards(); x++, z++) {
						if (!skat.containsCard(newSkat.getCard(i))) {
							replacedBy = newSkat.getCard(i);
							break;
						}
					}

					// Error checking, make sure the replacedBy card is in
					// declarerHand..
					if (!declarerHand.containsCard(replacedBy)) {
						// TODO: The new card in the skat wasn't in declarer
						// hand.
						// We should report an error here to stats.
					}

					// Swap the cards in skat with the hand.
					skat.replaceCards(cardReplaced, replacedBy);
					declarerHand.replaceCards(replacedBy, cardReplaced);
				}
			}
		}

		// Ask the declarer which type of game they want to play.
		GameTypeOptions chosenGameType = declarerPlayer
				.decideGameType(declarerHand.copy());

		// Confirm that their GameTypeOptions object is a valid combination of
		// game types.
		boolean validGame = this.isValidGameType(chosenGameType, viewSkat);

		// If valid, update gameType, multiplier and baseVal variables
		// accordingly.
		if (validGame) {
			// Set our game-type.
			this.gameType = chosenGameType;

			// TODO: Update multiplier/base values.
		} else {
			// TODO: Decide on what to do when invalid.
			// Maybe just use our statistics class to
			// invoke a critical error and terminate?
			return;
		}

		// Push GameTypeOptions object to each player.
		for (PlayerInfo pi : this.players)
			pi.getPlayer().setGameType(this.gameType, this.highestBidder);
	}

	/**
	 * Determines if the given game type is valid based on the rules of Skat.
	 * 
	 * @param gameType
	 *            the type of game to be evaluated
	 * @param tookSkat
	 *            indicates if the user took the skat.
	 * @return True if gameType is valid, and False otherwise.
	 */
	private boolean isValidGameType(GameTypeOptions gameType, boolean tookSkat) {
		// Get our options
		GameTypeOptions.TrumpSuit trump = gameType.getTrumpSuit();
		GameTypeOptions.SkatHandType skathandType = gameType.getHandType();
		GameTypeOptions.GameType actualGameType = gameType.getGameType();
		boolean ouvert = gameType.getOuvert();
		boolean schwarz = gameType.getSchwarz();
		boolean schneider = gameType.getSchneider();

		// Can't declare it as a hand game if you took the skat
		if (skathandType == GameTypeOptions.SkatHandType.Hand && tookSkat) {
			// TODO: Report non-critical error (SHOULDN'T DECLARE HAND GAME IF
			// YOU TOOK SKAT)
			return false;
		} else if (skathandType == GameTypeOptions.SkatHandType.Skat
				&& !tookSkat) {
			// TODO: Report non-critical error (SHOULDN'T DECLARE SKAT GAME IF
			// YOU DIDN'T TAKE SKAT)
			return false;
		}

		// Can't be a suit game without a suit.
		if (actualGameType == GameTypeOptions.GameType.Suit
				&& trump == GameTypeOptions.TrumpSuit.None) {
			// TODO: Report critical error (CAN'T BE SUIT GAME WITH NO SUIT)
			return false;
		}

		// Can't be a null or grand game with a suit.
		if (((actualGameType == GameTypeOptions.GameType.Grand) | (actualGameType == GameTypeOptions.GameType.Null))
				&& trump != GameTypeOptions.TrumpSuit.None) {
			// TODO: Report critical error (CAN'T BE GRAND OR NULL GAME WITH A
			// TRUMP SUIT)
			return false;
		}

		// If it's a skat game, and not null, you can't declare anything of
		// schwarz/schneider/ouvert.
		if ((skathandType == GameTypeOptions.SkatHandType.Skat && actualGameType != GameTypeOptions.GameType.Null)
				&& (schwarz | schneider | ouvert)) {
			// TODO: Report non-critical error (CAN'T DECLARE
			// SCHWARZ/SCHNEIDER/OUVERT IN SKAT GAME)
			// (we should just ignore that they did this and continue? what
			// about ouvert though?)
			return false;
		}

		// If it's a null game, you can't declare schwarz and schneider.
		if (actualGameType == GameTypeOptions.GameType.Null
				&& (schwarz | schneider)) {
			// TODO: Report non-critical error (CAN'T DECLARE NULL GAME AND
			// SCHWARZ/SCHNEIDER)
			return false;
		}

		// If they have ouvert true, you can't have schwarz false
		if (ouvert && !schwarz) {
			// TODO: Report non-critical error (CAN'T DECLARE OUVERT AND
			// NOT SCHWARZ) (we fix it up).
			return false;
		}

		return true;
	}

	/**
	 * Each player plays a card into the cardsPlayed Pile.
	 */
	private void playTrick() {
		// Create a player index
		int playerIndex = firstToPlayIndex;

		// Loop for each player..
		for (int i = 0; i < PLAYER_COUNT; i++) {
			// Get the player
			PlayerInfo curPlayerInfo = players[playerIndex];
			IPlayer curPlayer = curPlayerInfo.getPlayer();
			Pile curHand = curPlayerInfo.getHandPile();
			Card playedCard = null;

			// We'll let our user try to play a valid card as many times as
			// cards they have..
			for (int x = 0; x < curHand.getNumCards(); x++) {
				playedCard = curPlayer.playTurn(curHand.copy(),
						cardsPlayed.copy(), firstToPlayIndex);

				// Check if the card is valid, if it isn't, we make them try
				// again.
				if (!isValidCard(playedCard)) {
					// TODO: Report non-critical error (played invalid card,
					// we'll let them try again).
					playedCard = null;
				} else
					break;
			}

			// If our player somehow didn't pick a card by now, our strategy is
			// a total failure..
			if (playedCard == null) {
				// TODO: Report critical error (player couldn't pick a valid
				// card after multiple tries).
				return;
			}

			// If our card isn't even in our hand, there's a real problem
			if (!curHand.containsCard(playedCard)) {
				// TODO: Report critical error (player chose a card they didn't
				// even have to play for their trick...)
				return;
			}

			// Remove the card from their hand pile, add it to cardsPlayed
			curHand.removeCard(playedCard);
			cardsPlayed.addCard(playedCard);

			// Increment our player index.
			playerIndex = (playerIndex + 1) % PLAYER_COUNT;
		}
	}

	/**
	 * Determines if the given card is valid.
	 * 
	 * @param card
	 *            the Card to evaluate.
	 * @return True if the card is value, and False otherwise.
	 */
	private boolean isValidCard(Card card) {
		// Note: this is a helper function to be used in playTrick to confirm
		// validity.
		// TODO
		// Based on the current Game Type, and the cards that have already been
		// played (if any),
		// determine whether or not the given card is valid.
		// Return true if it is valid, and false otherwise.
		return true;
	}

	/**
	 * Determines if a bid is valid.
	 * 
	 * @param bid
	 *            The bid to evaluate
	 * @param highestBid
	 *            The previous highest bid
	 * @return True if valid, false otherwise
	 */
	private boolean isValidBid(int bid, int highestBid) {
		if (bid > highestBid) {
			for (int i = 0; i < LEGAL_BID_VALUES.length; i++) {
				if (bid == LEGAL_BID_VALUES[i])
					return true;
			}
		}
		return false;
	}

	/**
	 * Determine which player wins the trick, move the cards they won to their
	 * TricksWon pile, and let each player know who won the trick and which
	 * cards they won.
	 */
	private void winTrick() {
		// TODO
		// Examine cardsPlayed pile and determine which player won the trick.
		// Change firstToPlay to be the index of the player who won the trick.
		// Show each player who won the trick and which cards were played in the
		// trick (call endTrick on each player).
		// Move cards in cardsPlayed pile to the TricksWon pile of the player
		// who won.
	}

	/**
	 * Determine a given card's strength, based on the current game type.
	 * Strength: Card A beats Card B if Card A's strength is greater than Card
	 * B's strength in this type of game.
	 * 
	 * @param card
	 *            The card to determine the strength of.
	 * @return the card's strength in the context of the current game type.
	 */
	private int cardStrength(Card card) {
		// TODO
		// For a given card and a defined Game Type, determine the strength of
		// the card.
		// Return the strength of the card.
		return 0;
	}

	/**
	 * Determine how many card points a given player has won.
	 * 
	 * @param playerIndex
	 *            The player whose card points to count.
	 * @return The total card points the player has won.
	 */
	private int countCardPoints(int playerIndex) {
		// Get our number of cards
		int sum = 0;
		int numCards = players[playerIndex].getTricksWonPile().getNumCards();

		// Loop through each card for this player.
		for (int i = 0; i < numCards; i++) {
			// Add to our sum
			sum += players[playerIndex].getTricksWonPile().getCard(i)
					.getPointValue();
		}

		// Return our sum
		return sum;
	}

	/**
	 * Determine the value of the game.
	 * 
	 * @param baseVal
	 *            The base value.
	 * @param multiplier
	 *            The multiplier.
	 * @return The game value.
	 */
	private int gameValue() {
		// TODO
		// Determine the value of this game using the base value and the
		// multiplier.
		// Recall that Null games have set values and dont use a base value and
		// multiplier.
		// Return the game value.
		return 0;
	}

	/**
	 * Add or subtract points from the declarer's GameScore.
	 */
	private void assignGamePoints() {
		// TODO
		// Based on the number of card points the declarer won, the declarer's
		// final bid, and the game value,
		// determine whether or not the declarer wins or loses game points,
		// and add or subtract the appropriate number to/from their GameScore.
	}

	/**
	 * Performs general setup necessary to start a game of skat.
	 */
	public void setupGame(IPlayer player1, IPlayer player2, IPlayer player3) {
		// Setup our players
		this.resetPlayers(player1, player2, player3);
		this.assignIndexes();

		// When we start a round, we will increment dealer, and we want the
		// first round to be with dealer = 0..
		dealer = -1;
	}

	/**
	 * The main playing aspect of the game. Manages bidding, and all ten tricks
	 * of a game of skat. Performs end-of-game calculations and cleanup.
	 */
	public void playRound() {
		// Reset our players piles (hand and won pile) -- necessary if this is
		// not the first game played with these Players.
		for (int i = 0; i < players.length; i++)
			players[i].resetPiles();

		// Create the game piles
		this.createGamePiles();

		// Increment our dealer.
		dealer = (dealer + 1) % PLAYER_COUNT;

		// Shuffle and deal cards
		this.deck.shuffle();
		this.dealCards();

		// Manage bidding and game type declaration.
		this.initiateBidding();
		this.setGameType();

		// Play the 10 tricks of a game of skat.
		for (int i = 0; i < 10; i++) {
			trickNum = i;
			this.playTrick();
			this.winTrick();
		}

		// Once 10 tricks have been played, count card points, determine whether
		// or not the declarer won, update game scores.
		this.assignGamePoints();
	}
}
