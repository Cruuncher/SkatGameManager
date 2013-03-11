package skatgame.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import skatgame.*;

/**
 * Test case used for GameTypeOptions to ensure it works properly.
 */
public class GameTypeOptionsTest {

	/**
	 * Tests that the GameTypeOptions can construct itself with the correct values accordingly and its
	 * getter functions work.
	 */
	@Test
	public void testOptionsValid(){
		// TEST 1
		GameTypeOptions.TrumpSuit trump = GameTypeOptions.TrumpSuit.Clubs;
		GameTypeOptions.SkatHandType skathandType = GameTypeOptions.SkatHandType.Skat;
		GameTypeOptions.GameType actualGameType = GameTypeOptions.GameType.Suit;
		boolean ouvert = true;
		boolean schwarz = false;
		boolean schneider = true;	
		// Create our GameType.
		GameTypeOptions gameType = new GameTypeOptions(skathandType, actualGameType, trump, ouvert, schneider, schwarz);
		
		// Assert our options.
		assertEquals("Invalid Trump Suit set in GameTypeOptions, should be " + trump + " got " + gameType.getTrumpSuit(), trump, gameType.getTrumpSuit());
		assertEquals("Invalid Skat Hand Type set in GameTypeOptions, should be " + skathandType + " got " + gameType.getHandType(), skathandType, gameType.getHandType());
		assertEquals("Invalid GameType set in GameTypeOptions, should be " + actualGameType + " got " + gameType.getGameType(), actualGameType, gameType.getGameType());
		assertEquals("Invalid ouvert status in GameTypeOptions, should be " + ouvert + " got " + gameType.getOuvert(), ouvert, gameType.getOuvert());
		assertEquals("Invalid schwarz status in GameTypeOptions, should be " + schwarz + " got " + gameType.getSchwarz(), schwarz, gameType.getSchwarz());
		assertEquals("Invalid schneider status in GameTypeOptions, should be " + schneider + " got " + gameType.getSchneider(),  schneider, gameType.getSchneider());
		
		// TEST 2
		trump = GameTypeOptions.TrumpSuit.None;
		skathandType = GameTypeOptions.SkatHandType.Hand;
		actualGameType = GameTypeOptions.GameType.Null;
		ouvert = true;
		schwarz = false;
		schneider = false;	
		// Create our GameType.
		gameType = new GameTypeOptions(skathandType, actualGameType, trump, ouvert, schneider, schwarz);
		
		// Assert our options.
		assertEquals("Invalid Trump Suit set in GameTypeOptions, should be " + trump + " got " + gameType.getTrumpSuit(), trump, gameType.getTrumpSuit());
		assertEquals("Invalid Skat Hand Type set in GameTypeOptions, should be " + skathandType + " got " + gameType.getHandType(), skathandType, gameType.getHandType());
		assertEquals("Invalid GameType set in GameTypeOptions, should be " + actualGameType + " got " + gameType.getGameType(), actualGameType, gameType.getGameType());
		assertEquals("Invalid ouvert status in GameTypeOptions, should be " + ouvert + " got " + gameType.getOuvert(), ouvert, gameType.getOuvert());
		assertEquals("Invalid schwarz status in GameTypeOptions, should be " + schwarz + " got " + gameType.getSchwarz(), schwarz, gameType.getSchwarz());
		assertEquals("Invalid schneider status in GameTypeOptions, should be " + schneider + " got " + gameType.getSchneider(),  schneider, gameType.getSchneider());
		
		// TEST 3
		trump = GameTypeOptions.TrumpSuit.None;
		skathandType = GameTypeOptions.SkatHandType.Hand;
		actualGameType = GameTypeOptions.GameType.Grand;
		ouvert = false;
		schwarz = false;
		schneider = false;	
		// Create our GameType.
		gameType = new GameTypeOptions(skathandType, actualGameType, trump, ouvert, schneider, schwarz);
		
		// Assert our options.
		assertEquals("Invalid Trump Suit set in GameTypeOptions, should be " + trump + " got " + gameType.getTrumpSuit(), trump, gameType.getTrumpSuit());
		assertEquals("Invalid Skat Hand Type set in GameTypeOptions, should be " + skathandType + " got " + gameType.getHandType(), skathandType, gameType.getHandType());
		assertEquals("Invalid GameType set in GameTypeOptions, should be " + actualGameType + " got " + gameType.getGameType(), actualGameType, gameType.getGameType());
		assertEquals("Invalid ouvert status in GameTypeOptions, should be " + ouvert + " got " + gameType.getOuvert(), ouvert, gameType.getOuvert());
		assertEquals("Invalid schwarz status in GameTypeOptions, should be " + schwarz + " got " + gameType.getSchwarz(), schwarz, gameType.getSchwarz());
		assertEquals("Invalid schneider status in GameTypeOptions, should be " + schneider + " got " + gameType.getSchneider(),  schneider, gameType.getSchneider());
		
	}
}
