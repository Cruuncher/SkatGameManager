package skatgame.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import skatgame.*;

/**
 * Test cases used for PlayerInfoTest to ensure it works properly.
 */
public class PlayerInfoTest {

	/**
	 * Tests that the PlayerInfo class successfully constructs itself with the given player, 
	 * it's piles are reset as new, and game score is set to 0.
	 */
	@Test
	public void testConstructorValid(){
		// Create a PlayerInfo, and test that our construction works accordingly.
		IPlayer player = new DummyPlayer();
		PlayerInfo playerInfo = new PlayerInfo(player);
		assertEquals("Player instance did not set properly in PlayInfo.", player, playerInfo.getPlayer());
		
		// Check hand pile
		assertTrue("Player should have a valid hand pile.", playerInfo.getHandPile() != null);
		if(playerInfo.getHandPile() != null)
			assertTrue("Player's hand pile shouldn't have cards in it upon PlayerInfo construction.", playerInfo.getHandPile().getNumCards() == 0);
		
		// Check tricks won pile
		assertTrue("Player should have a valid tricks won pile.", playerInfo.getTricksWonPile() != null);
		if(playerInfo.getTricksWonPile() != null)
			assertTrue("Player's tricks won pile shouldn't have cards in it upon PlayerInfo construction.", playerInfo.getTricksWonPile().getNumCards() == 0);
		
		assertTrue("Player's game score should be 0 upon PlayerInfo construction.", playerInfo.getGameScore() == 0);
	}
	
	/**
	 * Tests that the PlayerInfo class successfully can set and get the game score.
	 */
	@Test
	public void testSetGameScore(){
		// Create a player, try to set score, and get it.
		IPlayer player = new DummyPlayer();
		PlayerInfo playerInfo = new PlayerInfo(player);
		playerInfo.setGameScore(1000);
		assertEquals("Player's game score should be 1000 after setting. Got " + playerInfo.getGameScore(), 1000, playerInfo.getGameScore());
	}
	
	/**
	 * Tests that the PlayerInfo class successfully resets the hand pile and tricks won pile.
	 */
	@Test
	public void testResetPiles(){
		// Create a player, try to set score, and get it.
		IPlayer player = new DummyPlayer();
		PlayerInfo playerInfo = new PlayerInfo(player);
		
		// Grab our Piles.
		Pile handPile1 = playerInfo.getHandPile();
		Pile wonPile1 = playerInfo.getTricksWonPile();
		
		// Reset our piles
		playerInfo.resetPiles();
		
		// Grab our piles again to compare.
		Pile handPile2 = playerInfo.getHandPile();
		Pile wonPile2 = playerInfo.getTricksWonPile();
		
		// Check our hand pile.
		assertTrue("Hand pile should not be null after resetting.", handPile2 != null);
		if(handPile2 != null)
			assertTrue("Hand pile should not be the same after resetting.", handPile1 != handPile2);
		
		// Check our won pile.
		assertTrue("Tricks won pile should not be null after resetting.", wonPile2 != null);
		if(wonPile2 != null)
			assertTrue("Tricks won pile should not be the same after resetting.", wonPile1 != wonPile2);
	}
}
