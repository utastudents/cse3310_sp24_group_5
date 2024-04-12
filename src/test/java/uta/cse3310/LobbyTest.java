package uta.cse3310;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LobbyTest {
    private Lobby lobby;

    @Mock
    private Game gameMock;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        lobby = new Lobby();
        when(gameMock.getMaxPlayers()).thenReturn(4);
        when(gameMock.addPlayer(anyString())).thenReturn("Player joined");
        when(gameMock.getCurrentPlayerCount()).thenReturn(1);
    }

    @Test
    public void testCreateOrJoinGame_NewGame() {
        when(gameMock.getMaxPlayers()).thenReturn(4);
        assertEquals("Player joined", lobby.createOrJoinGame("C", "A", 4));
        verify(gameMock, times(1)).addPlayer("A");
    }

    @Test
    public void testCreateOrJoinGame_JoinExistingGame() {
        lobby.createOrJoinGame("C", "A", 4);
        assertEquals("Player joined", lobby.createOrJoinGame("C", "B", 4));
        verify(gameMock, times(1)).addPlayer("B");
    }

    @Test
    public void testCreateOrJoinGame_NicknameInUse() {
        lobby.createOrJoinGame("C", "A", 4);
        assertEquals("Nickname is already in use.", lobby.createOrJoinGame("C", "A", 4));
    }

    @Test
    public void testCreateOrJoinGame_GameModeMismatch() {
        lobby.createOrJoinGame("C", "A", 4);
        assertEquals("Game mode mismatch for the game 'C'.", lobby.createOrJoinGame("C", "B", 2));
    }

    @Test
    public void testLeaveGame_Success() {
        lobby.createOrJoinGame("C", "A", 4);
        assertEquals("Player with nickname 'A' has left the game.", lobby.leaveGame("A"));
    }

    @Test
    public void testLeaveGame_PlayerNotFound() {
        assertEquals("Player with nickname 'A' not found in any game.", lobby.leaveGame("A"));
    }

    @Test
    public void testListGames_Empty() {
        assertEquals("Available games:\n", lobby.listGames());
    }

    @Test
    public void testListGames_WithGames() {
        lobby.createOrJoinGame("C", "A", 4);
        assertTrue(lobby.listGames().contains("C - Slots filled: 1/4\n"));
    }
}
