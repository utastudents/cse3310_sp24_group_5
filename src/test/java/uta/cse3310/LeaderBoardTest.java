package uta.cse3310;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class LeaderBoardTest {
    private Session session1;
    private Session session2;

    @Before
    public void setUp() {
        // Mock sessions
        session1 = mock(Session.class);
        session2 = mock(Session.class);

        // Add sessions to leaderboard
        LeaderBoard.addSession(session1);
        LeaderBoard.addSession(session2);
    }

    @Test
    public void testAddScore() {
        // Create players
        Player player1 = new Player("Apple");
        Player player2 = new Player("Bannanana@#$%#");

        // Add scores
        LeaderBoard.addScore(player1, 100);
        LeaderBoard.addScore(player2, 200);

        // Verify scores
        assertEquals(Integer.valueOf(100), LeaderBoard.getScores().get(player1));
        assertEquals(Integer.valueOf(200), LeaderBoard.getScores().get(player2));
    }

    @Test
    public void testBroadcast() throws IOException {
        // Mock RemoteEndpoint for sessions
        RemoteEndpoint.Basic remote1 = mock(RemoteEndpoint.Basic.class);
        RemoteEndpoint.Basic remote2 = mock(RemoteEndpoint.Basic.class);
        when(session1.getBasicRemote()).thenReturn(remote1);
        when(session2.getBasicRemote()).thenReturn(remote2);

        // Broadcast
        LeaderBoard.broadcast();

        // Verify that sendText method is called for all sessions
        verify(remote1).sendText(anyString());
        verify(remote2).sendText(anyString());
    }

    @Test
    public void testGetTopPlayers() {
        // Create players
        Player player1 = new Player("Apple");
        Player player2 = new Player("Bannanana");
        Player player3 = new Player("Cherry");

        // Add scores
        LeaderBoard.addScore(player1, 100);
        LeaderBoard.addScore(player2, 200);
        LeaderBoard.addScore(player3, 150);

        // Get top players
        List<Player> topPlayers = LeaderBoard.getTopPlayers(2);

        // Verify top players
        assertEquals(Arrays.asList(player2, player3), topPlayers);
    }

    @Test
    public void testGameEnd() throws IOException {
        // Simulate game conclusion by adding scores to the leaderboard
        Player player1 = new Player("Apple");
        Player player2 = new Player("Bobby");

        LeaderBoard.addScore(player1, 100);
        LeaderBoard.addScore(player2, 200);

        // Mock RemoteEndpoint for sessions
        RemoteEndpoint.Basic remote1 = mock(RemoteEndpoint.Basic.class);
        RemoteEndpoint.Basic remote2 = mock(RemoteEndpoint.Basic.class);
        when(session1.getBasicRemote()).thenReturn(remote1);
        when(session2.getBasicRemote()).thenReturn(remote2);

        // Call broadcast to update leaderboard and send to sessions
        LeaderBoard.broadcast();

        // Verify that sendText method is called for all sessions
        ArgumentCaptor<String> argumentCaptor1 = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> argumentCaptor2 = ArgumentCaptor.forClass(String.class);
        verify(remote1).sendText(argumentCaptor1.capture());
        verify(remote2).sendText(argumentCaptor2.capture());

        // Extract captured leaderboard strings
        String leaderboardSentToSession1 = argumentCaptor1.getValue();
        String leaderboardSentToSession2 = argumentCaptor2.getValue();

        // Manually construct the expected leaderboard string
        StringBuilder expectedLeaderboard = new StringBuilder("Leaderboard:\n");
        expectedLeaderboard.append("1. Bobby - Score: 200\n");
        expectedLeaderboard.append("2. Apple - Score: 100\n");

        // Assert that the captured strings match the expected leaderboard string
        assertEquals(expectedLeaderboard.toString(), leaderboardSentToSession1);
        assertEquals(expectedLeaderboard.toString(), leaderboardSentToSession2);
    }

}
