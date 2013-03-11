package skatgame.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CardTest.class, DeckTest.class, DummyPlayerTest.class, GameTest.class, GameTypeOptionsTest.class, PileTest.class, PlayerInfoTest.class })
/**
 * The main test case incorperating all test cases to run.
 */
public class AllTests {

}
