package uta.cse3310;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class LeaderBoardTest extends TestCase {
    public LeaderBoardTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() {
        // No need for session setup in this test class
    }

    public static TestSuite suite() {
        return new TestSuite(LeaderBoardTest.class);
    }

    public void testAddScore() {
        // Test addScore method
        LeaderBoard leaderBoard = new LeaderBoard();
        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");

        leaderBoard.addScore(player1, 100);
        leaderBoard.addScore(player2, 200);

        assertEquals(Integer.valueOf(100), leaderBoard.getScores().get(player1));
        assertEquals(Integer.valueOf(200), leaderBoard.getScores().get(player2));
    }

    public void testGetTopPlayers() {
        // Test getTopPlayers method
        LeaderBoard leaderBoard = new LeaderBoard();
        Player player1 = new Player("Alice");
        Player player2 = new Player("Bob");
        Player player3 = new Player("Charlie");

        leaderBoard.addScore(player1, 100);
        leaderBoard.addScore(player2, 200);
        leaderBoard.addScore(player3, 150);

        List<Player> topPlayers = leaderBoard.getTopPlayers(2);

        assertEquals(Arrays.asList(player2, player3), topPlayers);
    }
}
