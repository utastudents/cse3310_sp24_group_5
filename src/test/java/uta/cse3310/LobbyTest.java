package uta.cse3310;

import junit.framework.TestCase;

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
