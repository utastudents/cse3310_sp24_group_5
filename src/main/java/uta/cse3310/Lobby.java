package uta.cse3310;
import java.util.concurrent.ConcurrentHashMap;


public class Lobby {
    private final ConcurrentHashMap<String, Game> games = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> playerToGameMap = new ConcurrentHashMap<>(); //map player nicknames to the game names they are participating in

    //either create a new game or join an existing on
    //need the game's name, the player's nickname, and the intended game mode (number of players)
    // check if a game with the specified name already exists. 
    // If not, create a new game with the given name and game mode.
    public String createOrJoinGame(String gameName, String nick, int gameMode) 
    {

        //containsKey to determine whether a map contains an entry for the specified key
        if (playerToGameMap.containsKey(nick)) 
        {
            return "Nickname is already in use.";
        }

        //checks whether a game with the given gameName already exists in the games map
        //game will goes with games name, in case there 2 games into server at same time
        //if not, create a new game
        games.computeIfAbsent(gameName, k -> new Game(gameName, gameMode));
        Game game = games.get(gameName);
        
        //game mode (number of players) matches the mode specified when the game was created?
        if (game.getMaxPlayers() != gameMode) 
        {
            return "Game mode mismatch for the game '" + gameName + "'.";
        }
        
        // add the player to the game
        String response = game.addPlayer(nick);
        if (response.contains("joined")) 
        {
            playerToGameMap.put(nick, gameName);
        }
        return response;
    }

    //Looks up the game the player is currently in
    // removes the player from that game
    // updates the mapping to reflect this change. 

    public String leaveGame(String nick) {
        String gameName = playerToGameMap.get(nick);
        if (gameName != null && games.containsKey(gameName)) 
        {
            Game game = games.get(gameName);
            game.removePlayer(nick);
            playerToGameMap.remove(nick);
            return "Player with nickname '" + nick + "' has left the game.";
        }
        return "Player with nickname '" + nick + "' not found in any game.";
    }

    public String listGames() 
    {
        StringBuilder sb = new StringBuilder("Available games:\n");
        games.forEach((name, game) -> sb.append(name)
                .append(" - Slots filled: ")
                .append(game.getCurrentPlayerCount())
                .append("/")
                .append(game.getMaxPlayers())
                .append("\n"));
        return sb.toString();
    }
}
