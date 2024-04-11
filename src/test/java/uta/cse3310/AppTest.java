package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AppTest extends TestCase {
    
    public void testBegin() {
        // Create an instance of the App class
        App app = new App(8080); // Assuming port number 8080
        
        // Call the begin() method
        app.begin();
        
        // Add assertions to verify the behavior of the begin() method
        // For example, you could check if the server has started successfully
        // assertTrue(app.isServerRunning());
    }
    
    public void testAddPlayer() {
        // Create an instance of the App class
        App app = new App(8080); // Assuming port number 8080
        
        // Call the addPlayer() method
        Player player = app.addPlayer();
        
        // Add assertions to verify the behavior of the addPlayer() method
        // For example, you could check if the player is added to the lobby
        // assertNotNull(app.getLobby().getPlayer(player.getNick()));
    }
    
    public void testCreateGame() {
        // Create an instance of the App class
        App app = new App(8080); // Assuming port number 8080
        
        // Call the createGame() method
        Game game = app.createGame(123, 1); // Assuming gameId = 123 and mode = 1
        
        // Add assertions to verify the behavior of the createGame() method
        // For example, you could check if the game is created successfully
        // assertNotNull(game);
        // assertEquals(game.getGameId(), "123");
    }
    
    public void testJoinGame() {
        // Create an instance of the App class
        App app = new App(8080); // Assuming port number 8080
        
        // Create a game
        Game game = app.createGame(123, 1); // Assuming gameId = 123 and mode = 1
        
        // Create a player
        Player player = app.addPlayer();
        
        // Call the joinGame() method
        app.joinGame(game, player);
        
        // Add assertions to verify the behavior of the joinGame() method
        // For example, you could check if the player is added to the game
        // assertTrue(game.getPlayersList().contains(player));
    }
    
    // Add more test methods for other functionalities of the App class
}

