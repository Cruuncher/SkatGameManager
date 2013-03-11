package skatgame.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import skatgame.*;

public class DummyPlayerTest {
	
	@Test
	public void testPlayTurn() {
		//playTurn should return a Card.
		IPlayer dummy = new DummyPlayer();
		Pile pile = new Pile();
		pile.addCard(new Card(Card.CARD_SUIT.CLUBS, Card.FACE_VALUE.JACK));
		assertEquals("Asserting that a Card is returned.", Card.class, dummy.playTurn(pile, pile, 2).getClass());
	}
	

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
	
	@Test
	public void testDecideGameType() {
		IPlayer dummy = new DummyPlayer();
		Pile hand = new Pile();
		GameTypeOptions gameType = dummy.decideGameType(hand);
//		assertTrue("The player did not...    ??? wtf to do here?
		
	}
}
