package uta.cse3310;

import java.util.*;

public class LeaderBoard {
    // Private variables
    private Map<Player, Integer> scores;
    private Set<Player> players;

    // Constructor
    public LeaderBoard() {
        this.scores = new HashMap<>();
        this.players = new HashSet<>();
    }

    // Method to add player score to leaderboard
    public synchronized void addScore(Player player, int score) {
        scores.put(player, score);
        // Implement broadcast logic if needed
    }

    // Method to remove a player from the leaderboard
    public synchronized void removePlayer(Player player) {
        scores.remove(player);
        players.remove(player);
    }

    // Method to get top players for leaderboard
    public List<Player> getTopPlayers(int count) {
        List<Player> topPlayers = new ArrayList<>();
        scores.entrySet().stream().sorted(Map.Entry.<Player, Integer>comparingByValue().reversed()).limit(count)
                .forEach(entry -> topPlayers.add(entry.getKey()));
        return topPlayers;
    }

    // Method to format leaderboard as string (not used externally)
    private String getLeaderboardAsString() {
        StringBuilder leaderboard = new StringBuilder("Leaderboard:\n");
        int rank = 1;
        for (Map.Entry<Player, Integer> entry : scores.entrySet()) {
            leaderboard.append(rank).append(". ").append(entry.getKey().getNick()).append(" - Score: ")
                    .append(entry.getValue()).append("\n");
            rank++;
        }
        return leaderboard.toString();
    }

    // Getter for scores (not used externally)
    public Map<Player, Integer> getScores() {
        return scores;
    }
}
