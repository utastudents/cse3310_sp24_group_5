package uta.cse3310;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    private Player player;

    @Before
    public void setUp() {
        player = new Player("TestPlayer");
        System.out.println("Setup Complete: Player initialized with nickname 'TestPlayer' and score 0.");
    }

    @Test
    public void testInitialScore() {
        assertEquals(0, player.getScore());
        System.out.println("Test Initial Score: Passed - Score is 0 as expected.");
    }

    @Test
    public void testSetAndGetNick() {
        player.setNick("NewNick");
        assertEquals("NewNick", player.getNick());
        System.out.println("Test Set and Get Nick: Passed - Nickname is correctly set to 'NewNick'.");
    }

    @Test
    public void testSetAndGetScore() {
        player.setScore(100);
        assertEquals(100, player.getScore());
        System.out.println("Test Set and Get Score: Passed - Score is correctly set to 100.");
    }

    @Test
    public void testAddScore() {
        player.addScore(50);
        assertEquals(50, player.getScore());
        System.out.println("Test Add Score: Passed - Score correctly increased by 50 to 50.");

        player.addScore(20);
        assertEquals(70, player.getScore());
        System.out.println("Test Add Score: Passed - Score correctly increased by 20 to total of 70.");
    }
}
