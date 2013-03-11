package skatgame.tests;

import static org.junit.Assert.*;
import skatgame.*;
import org.junit.Test;

public class CardTest {

	@Test
	public void testCardSuits() {
		Card cardHearts = new Card(Card.CARD_SUIT.HEARTS, Card.FACE_VALUE.JACK);
		assertEquals("Asserting SUIT is hearts", Card.CARD_SUIT.HEARTS, cardHearts.getSuit());
		
		Card cardSpades = new Card(Card.CARD_SUIT.SPADES, Card.FACE_VALUE.JACK);
		assertEquals("Asserting SUIT is spades", Card.CARD_SUIT.SPADES, cardSpades.getSuit());

		Card cardDiamonds = new Card(Card.CARD_SUIT.DIAMONDS, Card.FACE_VALUE.JACK);
		assertEquals("Asserting SUIT is diamonds", Card.CARD_SUIT.DIAMONDS, cardDiamonds.getSuit());

		Card cardClubs = new Card(Card.CARD_SUIT.CLUBS, Card.FACE_VALUE.JACK);
		assertEquals("Asserting SUIT is clubs", Card.CARD_SUIT.CLUBS, cardClubs.getSuit());
	}
	
	@Test
	public void testCardFaceValue(){
		Card cardAce = new Card(Card.CARD_SUIT.HEARTS, Card.FACE_VALUE.ACE);
		assertEquals("Asserting face value is ace", Card.FACE_VALUE.ACE, cardAce.getFaceValue());
		
		Card cardKing = new Card(Card.CARD_SUIT.HEARTS, Card.FACE_VALUE.KING);
		assertEquals("Asserting face value is king", Card.FACE_VALUE.KING, cardKing.getFaceValue());
		
		Card cardSeven = new Card(Card.CARD_SUIT.HEARTS, Card.FACE_VALUE.SEVEN);
		assertEquals("Asserting face value is seven", Card.FACE_VALUE.SEVEN, cardSeven.getFaceValue());
		
		Card cardTen = new Card(Card.CARD_SUIT.HEARTS, Card.FACE_VALUE.TEN);
		assertEquals("Asserting face value is ten", Card.FACE_VALUE.TEN, cardTen.getFaceValue());
	}
	
	@Test
	public void testCardPointValues(){
		Card cardQueen = new Card(Card.CARD_SUIT.SPADES, Card.FACE_VALUE.QUEEN);
		assertEquals("Asserting pointValue is 3", 3, cardQueen.getPointValue());
		
		Card cardJack = new Card(Card.CARD_SUIT.SPADES, Card.FACE_VALUE.JACK);
		assertEquals("Asserting pointValue is 2", 2, cardJack.getPointValue());
		
		Card cardNine = new Card(Card.CARD_SUIT.SPADES, Card.FACE_VALUE.NINE);
		assertEquals("Asserting pointValue is 0", 0, cardNine.getPointValue());
		
		Card cardTen = new Card(Card.CARD_SUIT.SPADES, Card.FACE_VALUE.TEN);
		assertEquals("Asserting pointValue is 10", 10, cardTen.getPointValue());
	}

	
	@Test
	public void testCardEquals(){
		Card cardTen = new Card(Card.CARD_SUIT.CLUBS, Card.FACE_VALUE.TEN);
		Card cardTenDuplicate = new Card(Card.CARD_SUIT.CLUBS, Card.FACE_VALUE.TEN);
		assertEquals("Asserting cards are equal", true, cardTen.equals(cardTenDuplicate));
		
		Card cardKing = new Card(Card.CARD_SUIT.SPADES, Card.FACE_VALUE.KING);
		Card cardNine = new Card(Card.CARD_SUIT.HEARTS, Card.FACE_VALUE.NINE);
		assertEquals("Asserting cards are not equal", false, cardKing.equals(cardNine));
	}

}
