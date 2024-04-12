package uta.cse3310;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public class Lobby {
    private final ConcurrentHashMap<String, Game> games = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> playerToGameMap = new ConcurrentHashMap<>(); // map player nicknames to the game names they are participating in
    private final ConcurrentHashMap<String, ArrayList<String>> gameToPlayersMap = new ConcurrentHashMap<>(); // map game names to list of player nicknames

    public String createOrJoinGame(String gameName, String nick, int gameMode) 
    {
        if (playerToGameMap.containsKey(nick)) 
        {
            return "Nickname is already in use.";
        }

        games.computeIfAbsent(gameName, k -> new Game());
        ArrayList<String> players = gameToPlayersMap.computeIfAbsent(gameName, k -> new ArrayList<>());

        // gameMode to limit the number of players
        if (players.size() >= gameMode) 
        { //
            return "Game is full.";
        }

        players.add(nick);
        playerToGameMap.put(nick, gameName);
        return nick + " has joined the game.";
    }

    public String leaveGame(String nick) {
        String gameName = playerToGameMap.get(nick);

        if (gameName != null && gameToPlayersMap.containsKey(gameName)) //remove the player from the game
        {
            ArrayList<String> players = gameToPlayersMap.get(gameName);
            players.remove(nick);
            playerToGameMap.remove(nick);

            if (players.isEmpty()) //remove game if no player
            {
                games.remove(gameName);
                gameToPlayersMap.remove(gameName);
            }
            return "Player with nickname '" + nick + "' has left the game.";
        }
        return "Player with nickname '" + nick + "' not found in any game.";
    }

    public String listGames() 
    {
        StringBuilder sb = new StringBuilder("Available games:\n");
        gameToPlayersMap.forEach((name, players) -> sb.append(name)
                .append(" - Slots filled: ")
                .append(players.size())
                .append("\n"));
        return sb.toString();
    }
}
