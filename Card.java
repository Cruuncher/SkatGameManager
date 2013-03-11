
/**
 * 
 * The card object represents a single card in a deck of standard playing cards.  It contains a suit and a face value and a point
 * value associated with that card within the game of Skat. <br>
 * 
 */
public class Card {
	
	public static enum CARD_SUIT {CLUBS, SPADES, HEARTS, DIAMONDS};
	public static enum FACE_VALUE {SEVEN, EIGHT, NINE, TEN, ACE, KING, QUEEN, JACK}
	 //Our point values corresponding to each FACE_VALUE.
	private static int[] POINT_VALUES = { 0, 0, 0, 10, 11, 4, 3, 2 };
	
	private CARD_SUIT suit;
	private FACE_VALUE faceValue;
	
	/**
	 * Creates a new Card.
	 * @param suit The Card's suit.
	 * @param faceValue The Card's face value.
	 * @param pointValue The Card's point value.
	 */
	public Card(CARD_SUIT suit, FACE_VALUE faceValue){
		this.suit = suit;
		this.faceValue = faceValue;
	}
	
	/**
	 * Get this Card's suit.
	 * @return the suit of this Card.
	 */
	public CARD_SUIT getSuit(){
		return this.suit;
	}
	
	/**
	 * Get this Card's face value.
	 * @return the face value of this Card.
	 */
	public FACE_VALUE getFaceValue(){
		return this.faceValue;
	}
	
	/**
	 * Get this Card's point value.
	 * @return the point value of this Card.
	 */
	public int getPointValue(){
		return Card.POINT_VALUES[faceValue.ordinal()];
	}
	
	
	/**
	 * Compares two cards and returns true if they have the same suit and faceValue.
	 * @param card to be compared with this instance.
	 * @return true if the Cards have the same suit and faceValue, and false otherwise.
	 */
	public boolean equals(Card card){
		boolean retVal = true;
		if (this.suit != card.suit){
			retVal = false;
		} else if (this.faceValue != card.faceValue){
			retVal = false;
		}
		return retVal;
	}
	
	/**
	 * Returns a string representation of our card.
	 */
	@Override
	public String toString() {
		String cardString = "(";
		
		// Get our index for our enum option
		int faceIndex = faceValue.ordinal();
		
		// If it's less than 4 (ACE), then use the index to get the name.
		if(faceIndex < 4)
			cardString += (faceIndex + 7);
		// Otherwise, use the first character of our face value.
		else
			cardString += faceValue.toString().charAt(0);
		cardString += " of " + suit.toString().toLowerCase();
		cardString += ")";
		return cardString;
	}
}
