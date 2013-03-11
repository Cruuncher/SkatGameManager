package skatgame.tests;

import static org.junit.Assert.*;
import org.junit.Test;
import skatgame.*;

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
