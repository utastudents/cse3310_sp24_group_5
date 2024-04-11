package uta.cse3310;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;

@ServerEndpoint("/leaderboard")
public class LeaderBoard {
    // Map to store player scores with thread safety
    private static Map<Player, Integer> scores = Collections.synchronizedMap(new HashMap<>());

    // Set to store sessions of connected clients
    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());

    // Method called when a new client connects
    @OnOpen
    public void onOpen(Session session) {
        sessions.add(session);
    }

    // Method called when a client disconnects
    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    // Method to add player score to leaderboard
    public static synchronized void addScore(Player player, int score) {
        scores.put(player, score);
        broadcast(); // Broadcast updated leaderboard to all connected clients
    }

    // Method to broadcast leaderboard to all connected clients
    private static void broadcast() {
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendText(getLeaderboardAsString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to get top players for leaderboard
    public static List<Player> getTopPlayers(int count) {
        List<Player> topPlayers = new ArrayList<>();
        scores.entrySet().stream().sorted(Map.Entry.<Player, Integer>comparingByValue().reversed()).limit(count)
                .forEach(entry -> topPlayers.add(entry.getKey()));
        return topPlayers;
    }

    // Method to format leaderboard as string
    private static String getLeaderboardAsString() {
        StringBuilder leaderboard = new StringBuilder("Leaderboard:\n");
        int rank = 1;
        for (Map.Entry<Player, Integer> entry : scores.entrySet()) {
            leaderboard.append(rank).append(". ").append(entry.getKey().getNick()).append(" - Score: ")
                    .append(entry.getValue()).append("\n");
            rank++;
        }
        return leaderboard.toString();
    }
}
