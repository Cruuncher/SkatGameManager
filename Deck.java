import java.util.Collections;


/**
 * 
 * The deck object is a representation of a partial deck of cards which contain the necessary card objects to play
 * a game of Skat.<br>
 * <br>
 * <em>Currently there is no handling of bad input</em>
 * 
 */
public class Deck extends Pile {

	/**
	 * Create a populated deck containing cards for a standard Skat game.
	 */
	public Deck(){
		super();
		
		// Populate our deck
		Card.CARD_SUIT[] arrSuits = Card.CARD_SUIT.values();
		Card.FACE_VALUE[] arrFaces = Card.FACE_VALUE.values();
		for(Card.CARD_SUIT s : arrSuits) {
			for(Card.FACE_VALUE f : arrFaces) {
				this.addCard(new Card(s, f));
			}
		}
	}
	
	/**
	 * Shuffle the Cards in this Deck.
	 */
	public void shuffle(){
		//Randomize the order of the Cards in this Deck.
		Collections.shuffle(this.cards);
	}
	
	/**
	 * Deal a given number of Cards from this Deck into the given pile.
	 * @param pile the destination pile to deal Cards into.
	 * @param numCards the number of cards to deal into the pile.
	 */
	public void dealCards(Pile pile, int numCards) {
		for (int i = 0; i < numCards; i++) {
			pile.addCard(this.removeCard(0));
		}
	}
	
}
