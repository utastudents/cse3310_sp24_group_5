package uta.cse3310;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import java.util.List;
import java.util.stream.Collectors;

public class Lobby {
    private final ConcurrentHashMap<String, Game> games = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> playerToGameMap = new ConcurrentHashMap<>();

    //Fixed game name and game mode for player to pick
    private final List<String> availableGames = Arrays.asList("Game1", "Game2", "Game3", "Game4", "Game5");
    private final List<Integer> availableModes = Arrays.asList(2, 3, 4);

    public Lobby() 
    {
        availableGames.forEach(gameName -> games.put(gameName, new Game()));
    }

    //check if the nick is already used
    public String enterNickname(String nick) {
        if (playerToGameMap.containsKey(nick)) {
            return "Nickname is taken, please reenter.";
        }
        return "Nickname is available, please select a game.";
    }

    
    public String joinGame(String nick, int gameIndex, int modeIndex) 
    {   

        // The gameIndex and modeIndex will be in form of option for player to pick
        // these conditions are only to make sure about the range of gameMode and gameIndex
        if (gameIndex < 0 || gameIndex >= availableGames.size()) {
            return "Invalid game selection. Please select a valid game number.";
        }
        if (modeIndex < 0 || modeIndex >= availableModes.size()) {
            return "Invalid game mode selection. Please select a valid game mode number.";
        }

        //get info of the game
        String gameName = availableGames.get(gameIndex);
        int gameMode = availableModes.get(modeIndex);
        

        Game game = games.get(gameName);
        synchronized (game) 
        {
            if (game.playersList.isEmpty()) 
            {
                game.setGameMode(gameMode);  // allow to set game mode only if it's the first player joining
            } 
            else if (game.getGameMode() != gameMode) // if the second player enter a different game mode with the mode of the available game, error
            {
                return "Mismatched game mode. The game mode is already set to " + game.getGameMode();
            }

            if (game.playersList.size() >= game.getGameMode()) 
            {
                return "Game is full.";
            }

            game.addPlayers(new Player(nick));  // add player to the game
            playerToGameMap.put(nick, gameName);
            return nick + " has joined " + gameName + ". Current players: " + game.playersList.size() + "/" + game.getGameMode();
        }
    }

    public void leaveGame(String nick) 
    {
        String gameName = playerToGameMap.remove(nick); // Remove player from the game map
        if (gameName != null) 
        {
            Game game = games.get(gameName);
            if (game != null && game.removePlayer(nick)) 
            { // Remove player from the game
                if (game.isEmpty()) 
                { // remove the game if no players left
                games.remove(gameName);
                }
            }
        }
    }


    public List<String> getAvailableGames() {
        return availableGames;
    }

    public List<Integer> getAvailableModes() {
        return availableModes;
    }

    // return a List of Maps. Each Map represents the state of a game 
    // collect info for later display in Lobby UI
    public List<Map<String, Object>> listGames() 
    {
    return games.entrySet().stream().map(entry -> {
        Map<String, Object> gameInfo = new ConcurrentHashMap<>();
        String gameName = entry.getKey(); // // retrieve the game name -the key in the 'games' map
        Game game = entry.getValue(); // retrieves the corresponding value in the 'games' map
        int filledSlots = game.getPlayersList().size(); //// number of players = size of the players list
        int maxSlots = game.getGameMode(); //// maximum number of players = getGameMode().
        
        List<String> playerNicks = game.getPlayersList().stream()
                                        .map(Player::getNick) // This assumes a method getNick() exists in the Player class.
                                        .collect(Collectors.toList());
        
        gameInfo.put("gameName", gameName);
        gameInfo.put("filledSlots", filledSlots);
        gameInfo.put("maxSlots", maxSlots > 0 ? maxSlots : "Not set"); ////"Not set" to indicate the game mode is not defined
        gameInfo.put("playerNicks", playerNicks);
        
        return gameInfo;        
    }).collect(Collectors.toList());
    }
}






/*package uta.cse3310;

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

         games.computeIfAbsent(gameName, k -> new Game(gameMode));
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
*/
