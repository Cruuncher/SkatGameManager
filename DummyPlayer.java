

/**
 * 
 * A dummy player is a representation of a Skat player who plays valid cards with no AI behind it.<br>
 * <br>
 * <em>Currently there is no handling of bad input</em>
 *
 */
public class DummyPlayer implements IPlayer {
	
	private GameTypeOptions gameType;
	private int myPlayerIndex;
	private int declarerIndex;

	@Override
	public void assignIndex(int PlayerIndex) {
		myPlayerIndex = PlayerIndex;
	}
	
	@Override
	public Card playTurn(Pile hand, Pile playedCards,
			int playerIndexOfWhoStarted) {
		// TODO (right now, for the purpose of testing, return any card in hand)
		// [Strategy]
		// Examine what Cards have been played, and by which Player
		// (playedCards is designed such that the first Card in the Pile is the card that was played first)
		// Examine the Cards in your hand, and return the Card you decide to play.
		// Keep GameType variables in mind in order to pick valid Cards.
		return hand.getCard(0);
	}

	@Override
	public void endTrick(Pile trick, int winnerIndex) {
		// TODO
		// See who won the trick this round.
		// See which Cards were won in the trick
		// [Strategy] Useful for counting cards, etc.
	}

	@Override
	public boolean decideTakeSkat(Pile hand) {
		// TODO
		// Examine your hand.
		// [Strategy] Decide whether or not to view the skat.
		// Create a boolean retVal. Set as True to view the skat, False otherwise.
		return false;
	}

	@Override
	public Pile giveSkat(Pile skat, Pile hand) {
		// TODO
		// Add the two cards in the skat to your hand.
		// [Strategy] Choose any two cards to discard.
		// Remove discard cards from your hand, put them in the skat.
		// Return the skat. 
		return skat;
	}

	@Override
	public boolean bid(int currentHighestBid, Pile hand, boolean canMatch) {
		// TODO
		// Examine canMatch -- determine whether or not you may match the current highest bid,
		// or if you must bid higher.
		// Examine your hand.
		// [Strategy] Decide whether to bid/match, or pass.
		// Return true if you want to bid/match, or false if you want to pass.
		return false;
	}

	@Override
	public GameTypeOptions decideGameType(Pile hand) {
		// TODO (Currently returns null. After implementation, should return a GameTypeOptions object.)
		// Examine your hand.
		// [Strategy] Decide which type of game to play.
		// Create the corresponding type of GameTypeOptions object, and return it.
		// Keep in mind, only certain combinations of game type variables are valid.
		// (For example, you cannot have a game that is both Skat and Schneider)
		return new GameTypeOptions(GameTypeOptions.SkatHandType.Hand, GameTypeOptions.GameType.Suit, GameTypeOptions.TrumpSuit.Diamonds, false, false, false);
	}

	@Override
	public void setGameType(GameTypeOptions gameType, int declarerIndex) {
		// TODO
		// Keep a record of the current game type, to use when determining strategy and valid card choices.
		// Keep a record of the index of the declarer. By process of elimination (you already have your own playerIndex),
		// you can figure out the index of each player.
		// [Strategy] This is useful when choosing which Card to play during your turn so that you can attempt to
		// beat the declarer, and so you don't accidentally trump the other defender if their card has already beaten the declarer.
		// Note: if you ARE the declarer, you will already have a record of your own index, so the second parameter in this method
		// is not important to you. Furthermore, the difference between the two defenders is irrelevant to the declarer, so it is not
		// necessary that the declarer knows the individual indexes of the two defenders.
	}

	@Override
	public void endGameInfo(boolean decWon, int[] scores) {
		// TODO
		// If desired, keep a record of your (and the other players') current game score.
		// Note: This method isn't particularly important, it simply provides the player with information
		// a real human being would naturally acquire while playing a game of skat.
	}

	@Override
	public void giveDeclarersHand(Pile declHand) {
		// TODO
		// Keep a record of the declarer's hand.
		// [Strategy] Use your knowledge of the declarer's cards in order to choose which cards you play.
	}

	
}
