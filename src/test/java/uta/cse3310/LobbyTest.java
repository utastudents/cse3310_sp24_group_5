import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class LobbyTest {
    private Lobby lobby;

    @Before
    public void setUp() {
        lobby = new Lobby();
    }

    @Test
    public void testEnterNicknameAlreadyTaken() {
        // Assuming player "player1" already exists in the lobby
        lobby.enterNickname("player1"); // First entry of the nickname
        String result = lobby.enterNickname("player1"); // Trying to enter the same nickname again
        assertEquals("Nickname is taken, please reenter.", result);
    }

    @Test
    public void testEnterNicknameAvailable() {
        String result = lobby.enterNickname("player2");
        assertEquals("Nickname is available, please select a game.", result);
    }

    @Test
    public void testJoinGameWithValidParameters() {
        lobby.enterNickname("player3");
        String result = lobby.joinGame("player3", 0, 0); // Assuming 0 is a valid game and mode index
        assertTrue(result.contains("has joined Game1"));
    }

    @Test
    public void testJoinGameWithInvalidGameIndex() {
        lobby.enterNickname("player4");
        String result = lobby.joinGame("player4", 5, 0); // Invalid game index
        assertEquals("Invalid game selection. Please select a valid game number.", result);
    }

    @Test
    public void testJoinGameWithInvalidModeIndex() {
        lobby.enterNickname("player5");
        String result = lobby.joinGame("player5", 0, 3); // Invalid mode index
        assertEquals("Invalid game mode selection. Please select a valid game mode number.", result);
    }

    @Test
    public void testLeaveGame() {
        lobby.enterNickname("player6");
        lobby.joinGame("player6", 0, 0); // Player joins a game
        lobby.leaveGame("player6"); // Player leaves the game

        // Check if the game was properly updated after leaving
        assertTrue(lobby.listGames().stream()
                .noneMatch(game -> game.get("playerNicks").toString().contains("player6"))); // Ensuring the player is no longer listed in any game
    }
}
