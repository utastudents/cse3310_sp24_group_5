package uta.cse3310;
import junit.framework.TestCase;

public class LobbyTest extends TestCase {
    private Lobby lobby;

    protected void setUp() {
        lobby = new Lobby();
    }

    public void testEnterNickname() {
        //check if a nick name is successfully added to the map.
        lobby.enterNickname("Player1");
        assertTrue("Nickname should be put in the map", lobby.getPlayerToGameMap().containsKey("Player1"));
        assertNull("New player should not be in any game yet", lobby.getPlayerToGameMap().get("Player1"));
    }

    public void testJoinGame() {
        //a valid nickname and valid game and mode 
        lobby.enterNickname("Player2");
        lobby.joinGame("Player2", 0, 0);

        assertNotNull("Player should be put to a game after joining", lobby.getPlayerToGameMap().get("Player2"));
        String gameName = lobby.getPlayerToGameMap().get("Player2");
        assertTrue("Game name should not be null", gameName != null && !gameName.isEmpty());
    }

    public void testLeaveGame() {
        Lobby lobby = new Lobby();
        lobby.enterNickname("Player3");
        lobby.joinGame("Player3", 0, 0); // adds Player3 to Game4 

        assertEquals("Player swas in a game before leaving", "Game4", lobby.getPlayerToGameMap().get("Player3"));

        // Now test leaving the game.
        lobby.leaveGame("Player3");
        assertNull("Player is no longer bewith any game after leaving", lobby.getPlayerToGameMap().get("Player3"));
    }


    public void testJoinGameWithInvalidIndices() {
        //invalid game or mode indices
        lobby.enterNickname("Player4");
        lobby.joinGame("Player4", 100, 100); 

        assertNull("Player will not be with any game ", lobby.getPlayerToGameMap().get("Player4"));
    }

    public void testLeaveGameWithoutJoining() {
        lobby.enterNickname("Player5");
        
        lobby.leaveGame("Player5");
        assertNull("Player will not be with any game", lobby.getPlayerToGameMap().get("Player5"));
    }
}



/*
public class LobbyTest extends TestCase {

    public void testEnterNickname() {
        Lobby lobby = new Lobby();
        
        lobby.enterNickname("Player1");
        assertTrue(lobby.getPlayerToGameMap().containsKey("Player1"));
    }

    public void testJoinGame() {
        Lobby lobby = new Lobby();
        lobby.enterNickname("Player2");

        lobby.joinGame("Player2", 0, 0);
    }

    public void testLeaveGame() {
        Lobby lobby = new Lobby();
        lobby.enterNickname("Player3");
        lobby.joinGame("Player3", 0, 0);

        lobby.leaveGame("Player3");
        assertNull(lobby.getPlayerToGameMap().get("Player3"));
    }
}
*/