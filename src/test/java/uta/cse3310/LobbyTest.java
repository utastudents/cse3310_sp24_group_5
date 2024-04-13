package uta.cse3310;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


class LobbyTest {
    private Lobby lobby;

    @BeforeEach
    void setUp() {
        lobby = new Lobby();
    }

    @Test
    void testCreateOrJoinGame() 
    {
        // Test creating a new game
        assertEquals("Alice has joined the game.", lobby.createOrJoinGame("Game1", "Alice", 4));
        assertEquals("Bob has joined the game.", lobby.createOrJoinGame("Game1", "Bob", 4));

        // Test joining an existing game
        assertEquals("Charlie has joined the game.", lobby.createOrJoinGame("Game2", "Charlie", 3));
    }

    @Test
    void testLeaveGame() 
    {
        // Test leaving a game
        lobby.createOrJoinGame("Game1", "Alice", 2);
        assertEquals("Player with nickname 'Alice' has left the game.", lobby.leaveGame("Alice"));

        // Test leaving a game with multiple players
        lobby.createOrJoinGame("Game2", "Bob", 3);
        lobby.createOrJoinGame("Game2", "Charlie", 3);
        assertEquals("Player with nickname 'Bob' has left the game.", lobby.leaveGame("Bob"));
    }

    @Test
    void testListGames() 
    {
        // Test listing games
        lobby.createOrJoinGame("Game1", "Alice", 2);
        lobby.createOrJoinGame("Game2", "Bob", 3);
        lobby.createOrJoinGame("Game2", "Charlie", 3);

        assertEquals("Available games:\nGame1 - Slots filled: 1\nGame2 - Slots filled: 2\n", lobby.listGames());
    }
}
