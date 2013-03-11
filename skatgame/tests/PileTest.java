package skatgame.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import skatgame.*;

/**
 * Test cases used for Pile to ensure it works properly.
 */
public class PileTest {

	/**
	 * Tests that the Pile can successfully retrieve the correct cards at a given index.
	 */
	@Test
	public void testGetCard(){
		Pile pile = new Pile();
		Card card = new Card(Card.CARD_SUIT.DIAMONDS, Card.FACE_VALUE.ACE);
		Card card2 = new Card(Card.CARD_SUIT.CLUBS, Card.FACE_VALUE.NINE);
		pile.addCard(card);
		pile.addCard(card2);
		assertEquals("Asserting the right card was retrieved", card2, pile.getCard(1));
		
		Pile pile2 = new Pile();
		
		boolean threwCorrectError = false;
		try {
			pile2.getCard(0);
		} catch(IndexOutOfBoundsException e){
			// Correct exception was caught.
			threwCorrectError = true;
		} catch (Exception e) {
			assertTrue("Unexpected exception.", false);
		} finally {
			assertTrue("Didn't throw any exception when should've.", threwCorrectError);
		}
		
	}
	
	/**
	 * Tests that the Pile can successfully add a card to itself and get it.
	 */
	@Test
	public void testAddCard(){
		Pile pile = new Pile();
		Card card = new Card(Card.CARD_SUIT.HEARTS, Card.FACE_VALUE.KING);
		pile.addCard(card);
		assertEquals("Asserting the card was added to the pile", card, pile.getCard(0));
	}
	
	/**
	 * Tests that the Pile can successfully remove a given card when it should, or won't
	 * when it shouldn't.
	 */
	@Test
	public void testRemoveCard(){
		Pile pile = new Pile();
		Card card = new Card(Card.CARD_SUIT.SPADES, Card.FACE_VALUE.KING);
		pile.addCard(card);
		assertEquals("Asserting the card was removed", card, pile.removeCard(0));
		
		Pile pile2 = new Pile();
		Card card2 = new Card(Card.CARD_SUIT.DIAMONDS, Card.FACE_VALUE.EIGHT);
		Card card3 = new Card(Card.CARD_SUIT.CLUBS, Card.FACE_VALUE.JACK);
		pile2.addCard(card2);
		assertEquals("Asserting the card was not removed.", false, pile2.removeCard(card3));
		
		Pile pile3 = new Pile();
		Card card4 = new Card(Card.CARD_SUIT.HEARTS, Card.FACE_VALUE.SEVEN);

		assertEquals("Asserting there was no card to remove.", false , pile3.removeCard(card4));
		
		boolean threwCorrectException = false;
		boolean threwIncorrectException = false;
		try {
			pile3.removeCard(0);
		} catch(IndexOutOfBoundsException e){
			threwCorrectException = true;
			assertTrue("Correct exception caught.", threwCorrectException);
		} catch (Exception e) {
			threwIncorrectException = true;
			assertFalse("Unexpected exception.", threwIncorrectException);
		} finally {
			assertTrue("Didn't throw any exception.", threwCorrectException && !threwIncorrectException);
		}
	}
	
	/**
	 * Tests that the Pile can successfully retrieve the correct number of cards it contains.
	 */
	@Test
	public void testGetNumCards(){
		Pile pile = new Pile();
		Card card1 = new Card(Card.CARD_SUIT.HEARTS, Card.FACE_VALUE.KING);
		Card card2 = new Card(Card.CARD_SUIT.CLUBS, Card.FACE_VALUE.SEVEN);
		Card card3 = new Card(Card.CARD_SUIT.DIAMONDS, Card.FACE_VALUE.ACE);
		
		assertEquals("Asserting the pile has no cards in it.", 0, pile.getNumCards());
		
		pile.addCard(card1);
		assertEquals("Asserting the pile has one card in it.", 1, pile.getNumCards());
		
		pile.addCard(card2);
		assertEquals("Asserting the pile has two cards in it.", 2, pile.getNumCards());
		
		pile.addCard(card3);
		assertEquals("Asserting the pile has three cards in it.", 3, pile.getNumCards());
	}
	
	/**
	 * Tests that the Pile can successfully move all of its cards to another pile,
	 * using the method to move all cards.
	 */
	@Test
	public void testMoveAllCards(){
		Pile pilesrc = new Pile();
		Pile piledest = new Pile();
		Card card1 = new Card(Card.CARD_SUIT.HEARTS, Card.FACE_VALUE.KING);
		Card card2 = new Card(Card.CARD_SUIT.CLUBS, Card.FACE_VALUE.SEVEN);
		Card card3 = new Card(Card.CARD_SUIT.DIAMONDS, Card.FACE_VALUE.ACE);
		Card card4 = new Card(Card.CARD_SUIT.SPADES, Card.FACE_VALUE.QUEEN);
		pilesrc.addCard(card1);
		pilesrc.addCard(card2);
		pilesrc.addCard(card3);
		pilesrc.addCard(card4);
		pilesrc.moveAllCards(piledest);
		assertEquals("Moved all (multiple) cards from source pile.", 0, pilesrc.getNumCards());
		assertEquals("Moved all (multiple) cards to destination pile.", 4, piledest.getNumCards());
		
		piledest.removeCard(0);
		piledest.removeCard(0);
		piledest.removeCard(0);
		piledest.removeCard(0);
		pilesrc.addCard(card1);
		pilesrc.moveAllCards(piledest);
		assertEquals("Moved all cards (only one card) from source pile.", 0, pilesrc.getNumCards());
		assertEquals("Moved all cards (only one card) to destination pile.", 1, piledest.getNumCards());
	}
	
	/**
	 * Tests that the Pile can successfully copy itself and contain the correct cards.
	 */
	@Test
	public void testCopyPile(){
		Pile pile1 = new Pile();
		Card card1 = new Card(Card.CARD_SUIT.HEARTS, Card.FACE_VALUE.KING);
		Card card2 = new Card(Card.CARD_SUIT.CLUBS, Card.FACE_VALUE.SEVEN);
		Card card3 = new Card(Card.CARD_SUIT.DIAMONDS, Card.FACE_VALUE.ACE);
		
		Pile pile2 = pile1.copy();
		assertEquals("Copy of an empty pile is also an empty pile", 0, pile2.getNumCards());
		
		pile1.addCard(card1);
		Pile pile3 = pile1.copy();
		assertEquals("Copy of a pile with one card has correct copy of card.", true, card1.equals(pile3.getCard(0)));
		assertEquals("Copy of a pile with one card has only one card.", 1, pile3.getNumCards());
		
		pile1.addCard(card2);
		pile1.addCard(card3);
		Pile pile4 = pile1.copy();
		assertEquals("Copy of a pile with 3 cards has correct copy of first card.", true, card1.equals(pile4.getCard(0)));
		assertEquals("Copy of a pile with 3 cards has correct copy of second card.", true, card2.equals(pile4.getCard(1)));
		assertEquals("Copy of a pile with 3 cards has correct copy of third card.", true, card3.equals(pile4.getCard(2)));
		assertEquals("Copy of a pile with 3 cards has only 3 cards.", 3, pile4.getNumCards());

	}

}
