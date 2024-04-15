package uta.cse3310;

import junit.framework.TestCase;

public class PlayerTest extends TestCase {
    private Player player;

    // Set up method named in the convention of JUnit 3
    protected void setUp() {
        player = new Player("TestPlayer");
    }

    // Each test method must start with 'test'
    public void testInitialScore() {
        assertEquals("Initial score should be zero", 0, player.getScore());
    }

    public void testSetAndGetNick() {
        player.setNick("NewNick");
        assertEquals("Nickname should be set to 'NewNick'", "NewNick", player.getNick());
    }

    public void testSetAndGetScore() {
        player.setScore(100);
        assertEquals("Score should be set to 100", 100, player.getScore());
    }

    public void testIncreaseScore() {
        player.increaseScore(50);
        assertEquals("Score should be increased by 50", 50, player.getScore());

        player.increaseScore(20);
        assertEquals("Score should be increased by 20 to a total of 70", 70, player.getScore());
    }
}
