package uta.cse3310;

import junit.framework.TestCase;

public class PlayerTest extends TestCase {
    private Player player;

    protected void setUp() {
        player = new Player("TestPlayer");
    }

    public void testInitialScore() {
        assertEquals("Initial score will be zero", 0, player.getScore());
    }

    public void testSetAndGetNick() {
        player.setNick("NewNick");
        assertEquals("Nickname willbe set to 'NewNick'", "NewNick", player.getNick());
    }

    public void testSetAndGetScore() {
        player.setScore(100);
        assertEquals("Score willbe set to 100", 100, player.getScore());
    }

    public void testIncreaseScore() {
        player.increaseScore(50);
        assertEquals("Score is increased by 50", 50, player.getScore());

        player.increaseScore(20);
        assertEquals("Score is increased by 20,  total is 70", 70, player.getScore());
    }
}
