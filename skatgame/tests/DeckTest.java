package skatgame.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import skatgame.*;

/**
 * Test cases used for DeckTest to ensure it works properly.
 */
public class DeckTest {
	
	/**
	 *  Tests that the deck can deal the correct amount of cards (2) to a pile.
	 */
	@Test
	public void testDeckDeal2Cards(){
		Deck deck = new Deck();
		Pile destinationPile = new Pile();
		deck.dealCards(destinationPile, 2);
		assertEquals("Asserting 2 cards were dealt from the deck", 30, deck.getNumCards());
		assertEquals("Asserting 2 cards were dealt into the destination", 2, destinationPile.getNumCards());
	}
	
	/**
	 *  Tests that the deck can deal the correct amount of cards (3) to a pile.
	 */
	@Test
	public void testDeckDeal3Cards(){
		Deck deck = new Deck();
		Pile destinationPile = new Pile();
		deck.dealCards(destinationPile, 3);
		assertEquals("Asserting 3 cards were dealt from the deck", 29, deck.getNumCards());
		assertEquals("Asserting 3 cards were dealt into the destination", 3, destinationPile.getNumCards());
	}
	
	/**
	 *  Tests that the deck can deal the correct amount of cards (4) to a pile.
	 */
	@Test
	public void testDeckDeal4Cards(){
		Deck deck = new Deck();
		Pile destinationPile = new Pile();
		deck.dealCards(destinationPile, 4);
		assertEquals("Asserting 4 cards were dealt from the deck", 28, deck.getNumCards());
		assertEquals("Asserting 4 cards were dealt into the destination", 4, destinationPile.getNumCards());
	}

}
