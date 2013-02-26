package edu.piggottk.skat;
/**
 * 
 * The card object represents a single card in a deck of standard playing cards.  It contains a suit and a face value and a point
 * value associated with that card within the game of Skat. <br>
 * <br>
 * <em>Currently there is no handling of bad input</em>
 * 
 * @author Kelly
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
	 * Create a copy of this card.
	 * @return a copy of this card.
	 */
	public Card copyCard(){
		CARD_SUIT suit = this.suit;
		FACE_VALUE faceValue = this.faceValue;
		Card copy = new Card(suit, faceValue);
		return copy;
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
}
