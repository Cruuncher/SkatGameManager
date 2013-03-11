package skatgame.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import skatgame.*;

/**
 * Test cases used for Game to ensure it works properly.
 */
public class GameTest {

	@Test
	public void testConstructor() {
		Game game = null;
		try {
			game = new Game();
		} catch (Exception e) {
			fail("Exception thrown by constructor");
		}
		assertNotNull(game);
	}

}
