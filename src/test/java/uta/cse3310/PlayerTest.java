package uta.cse3310;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlayerTest {
    private Player player;

    @Before
    public void setUp() 
    {
        player = new Player("TestPlayer");
    }

    @Test
    public void testInitialScore() 
    {
        assertEquals("Initial score should be zero", 0, player.getScore());
    }

    @Test
    public void testSetAndGetNick() 
    {
        player.setNick("NewNick");
        assertEquals("Nickname should be set to 'NewNick'", "NewNick", player.getNick());
    }

    @Test
    public void testSetAndGetScore() 
    {
        player.setScore(100);
        assertEquals("Score should be set to 100", 100, player.getScore());
    }

    @Test
    public void testIncreaseScore() 
    {
        player.increaseScore(50);
        assertEquals("Score should be increased by 50", 50, player.getScore());

        player.increaseScore(20);
        assertEquals("Score should be increased by 20 to a total of 70", 70, player.getScore());
    }
}
