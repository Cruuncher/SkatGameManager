
import java.util.ArrayList;

/**
 * 
 * A representation for any generic collection of cards.  Contains generic methods used by the varius collections of cards in
 * a game of Skat.<br>
 * <br>
 * <em>Currently there is no handling of bad input</em>
 * 
 */
public class Pile {
	protected ArrayList<Card> cards;
	
	/**
	 * Creates an empty Pile.
	 */
	public Pile(){
		cards = new ArrayList<Card>();
	}
	
	/**
	 * Creates an empty pile with a specific known capacity to avoid having to enlarge this Pile later.
	 * @param capacity The known max capacity for this Pile.
	 */
	public Pile(int capacity){
		cards = new ArrayList<Card>(capacity);
	}
	
	/**
	 * This is a private constructor used internally to create a copy of this pile.
	 * @see copy
	 * @param pile the Pile to be copied
	 */
	private Pile(Pile pile){
		cards = new ArrayList<Card>(pile.getNumCards());
		for (int i = 0; i < pile.getNumCards(); i++) {
			this.addCard(pile.getCard(i).copyCard());
		}
	}
	
	/**
	 * Add a given card to this Pile.
	 * @param card The card to be added.
	 */
	public void addCard(Card card){
		this.cards.add(card);
	}
	
	/**
	 * Remove a given card from this Pile
	 * @param card The card to be removed
	 * @return true if card was removed, false otherwise.
	 */
	public boolean removeCard(Card card){
		return this.cards.remove(card);
	}
	
	/**
	 * Remove the card at the given index of the pile
	 * @param index the index of the card to be removed
	 * @return the removed card
	 */
	public Card removeCard(int index) {
		return this.cards.remove(index);
	}
	
	/**
	 * Moves all Cards from this pile to the destination Pile.
	 * @param dest the destination Pile
	 */
	public void moveAllCards(Pile dest){
		int srcPileSize = this.getNumCards();
		for (int i = 0; i < srcPileSize; i++){
			dest.addCard(this.cards.remove(0));
		}
	}
	
	/**
	 * Returns the number of Cards in this Pile.
	 * @return the number of Cards in this Pile.
	 */
	public int getNumCards(){
		return this.cards.size();
	}
	
	/**
	 * Returns the Card found at a specific index in this Pile.
	 * @param index The index of the Card to get from this Pile.
	 * @return the Card at the specific index in this Pile.
	 */
	public Card getCard(int index) {
		return this.cards.get(index);
	}
	
	/**
	 * Creates a copy of this Pile.
	 * @return a copy of this Pile.
	 */
	public Pile copy(){
		return new Pile(this);
	}
	
	/**
	 * (Intended to be used by the declarer while examining the skat Pile)
	 * Replaces a Card from this pile with a new Card.
	 * @param oldCard the Card object in this pile you wish to replace.
	 * @param newCard the new Card object you wish to put into this pile.
	 */
	public void replaceCards(Card oldCard, Card newCard){
		// We need to find the index of the old card
		for(int i = 0; i < this.cards.size(); i++) {
			if(this.cards.get(i).equals(oldCard)) {
				// Set the new object
				this.cards.set(i, newCard);
			}
		}
	}
}
