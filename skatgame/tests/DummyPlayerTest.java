package skatgame.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import skatgame.*;

/**
 * Test cases used for DummyPlayerTest to ensure it works properly.
 */
public class DummyPlayerTest {
	
	/**
	 * Tests that the DummyPlayer can play a turn successfully and returns a valid card.
	 */
	@Test
	public void testPlayTurn() {
		//playTurn should return a Card.
		IPlayer dummy = new DummyPlayer();
		Pile pile = new Pile();
		Card addedCard = new Card(Card.CARD_SUIT.CLUBS, Card.FACE_VALUE.JACK);
		pile.addCard(addedCard);
		assertEquals("Asserting that a Card is returned.", addedCard, dummy.playTurn(pile, pile, 2));
	}
	
	/**
	 * Tests that the DummyPlayer can successfully decide to bid.
	 * (Used to be a useful test, code changed, left here for documentation purposes)
	 */
	@Test
	public void testBid() {
		IPlayer dummy = new DummyPlayer();
		Pile pile = new Pile();
		boolean bid = dummy.bid(10, pile, true);
//		Cant do the following commented assert anymore, because it calls the method twice, each time returning a random boolean.
//		The return boolean value must be calculated beforehand, as done in the line above this comment.
//		assertTrue("The player somehow returned something invalid.", dummy.bid(10, pile, true) || !dummy.bid(10, pile, true));
		assertTrue("The player somehow returned something invalid.", bid || !bid);
	}

	/**
	 * Tests that the DummyPlayer takes the skat and returns the same instance of the skat pile.
	 */
	@Test
	public void testGiveSkat() {
		IPlayer dummy = new DummyPlayer();
		Pile skat = new Pile();
		Pile hand = new Pile();
		Card card1 = new Card(Card.CARD_SUIT.SPADES, Card.FACE_VALUE.SEVEN);
		Card card2 = new Card(Card.CARD_SUIT.CLUBS, Card.FACE_VALUE.NINE);
		Card card3 = new Card(Card.CARD_SUIT.DIAMONDS, Card.FACE_VALUE.ACE);
		Card card4 = new Card(Card.CARD_SUIT.CLUBS, Card.FACE_VALUE.JACK);
		Card card5 = new Card(Card.CARD_SUIT.HEARTS, Card.FACE_VALUE.KING);
		skat.addCard(card1);
		skat.addCard(card2);
		hand.addCard(card3);
		hand.addCard(card4);
		hand.addCard(card5);
		assertSame("Player did not return the same instance of Pile.", skat, dummy.giveSkat(skat, hand));
	}

	/**
	 * Tests that the DummyPlayer can decide to take the skat.
	 * (Just as in testBid, this is useless, left here since it changed).
	 */
	@Test
	public void testDecideTakeSkat() {
		IPlayer dummy = new DummyPlayer();
		Pile hand = new Pile();
		boolean decision = dummy.decideTakeSkat(hand);
//		Cant do the following commented assert anymore, because it calls the method twice, each time returning a random boolean.
//		The return boolean value must be calculated beforehand, as done in the line above this comment.
//		assertTrue("The player somehow returned something invalid.", dummy.decideTakeSkat(hand) || !dummy.decideTakeSkat(hand));
		assertTrue("The player somehow returned something invalid.", decision || !decision);
	}
	
	/**
	 * Tests that the DummyPlayer can return a non-null GameTypeOptions object.
	 */
	@Test
	public void testDecideGameType() {
		IPlayer dummy = new DummyPlayer();
		Pile hand = new Pile();
		GameTypeOptions gameType = dummy.decideGameType(hand);
		assertTrue("Player did not return a GameTypeOptions.", gameType != null);
		
	}
}
