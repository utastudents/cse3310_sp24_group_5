package uta.cse3310;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for the App class.
 */
public class AppTest {

    private App app;

    /**
     * Method to set up the test environment before each test.
     */
    @Before
    public void setUp() {
        app = new App(8080); // Assuming port number 8080
    }

    /**
     * Test method to verify the behavior of the addPlayer() method.
     */
    @Test
    public void testAddPlayer() {
        Player player = app.addPlayer();
        assertNotNull(player); // Check if the player is not null
        // Add more assertions if necessary
    }

    /**
     * Test method to verify the behavior of the createGame() method.
     */
    @Test
    public void testCreateGame() {
        Game game = app.createGame(123, 1); // Assuming gameId = 123 and mode = 1
        assertNotNull(game); // Check if the game is not null
        // Add more assertions if necessary
    }

    /**
     * Test method to verify the behavior of the joinGame() method.
     */
    @Test
    public void testJoinGame() {
        Game game = app.createGame(123, 1); // Assuming gameId = 123 and mode = 1
        Player player = app.addPlayer();
        app.joinGame(game, player);
        assertTrue(game.getPlayersList().contains(player)); // Check if the player is added to the game
        // Add more assertions if necessary
    }

    /**
     * Test method to verify the behavior of the endGame() method.
     */
    @Test
    public void testEndGame() {
        Game game = app.createGame(123, 1); // Assuming gameId = 123 and mode = 1
        app.endGame(game);
        assertFalse(app.getActiveGames().contains(game)); // Check if the game is removed from active games
        // Add more assertions if necessary
    }

    // Add more test methods for other functionalities of the App class
}
