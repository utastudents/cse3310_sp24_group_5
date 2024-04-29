package uta.cse3310;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

public class Lobby {
    private Map<String, Game> games;
    public Map<String, String> playerToGameMap;
    public ArrayList<Game> openGames=new ArrayList<Game>(); // Games open to join
    public HashMap<String, ArrayList<Player>> LeaderBoard=new HashMap<String, ArrayList<Player>>(); // Concurrent Leaderboard

    public Lobby() {
        this.games = new HashMap<>();
        this.playerToGameMap = new HashMap<>();
        initializeGames(Arrays.asList("Game1", "Game2", "Game3", "Game4", "Game5"));
    }

    public Map<String, String> getPlayerToGameMap() {
        return playerToGameMap;
    }

    private void initializeGames(List<String> gameNames) {
        gameNames.forEach(gameName -> games.put(gameName, new Game()));
    }

    // checks if the nickname is not already in use
    // adds it to the playerToGameMap if it's available
    public void enterNickname(String nick) {
        if (!playerToGameMap.containsKey(nick)) {
            playerToGameMap.put(nick, null);
        }
    }

    public void joinGame(String nick, int gameIndex, int modeIndex) {
        List<String> availableGames = new ArrayList<>(games.keySet());
        List<Integer> availableModes = Arrays.asList(2, 3, 4);

        if (gameIndex >= 0 && gameIndex < availableGames.size() && modeIndex >= 0
                && modeIndex < availableModes.size()) {
            String gameName = availableGames.get(gameIndex);
            Game game = games.get(gameName);

            if (game != null) {
                int selectedMode = availableModes.get(modeIndex);

                // check if the game is empty
                if (game.getPlayersList().isEmpty()) {
                    // set the game mode when the first player joins an empty game
                    game.setGameMode(selectedMode);
                    game.addPlayers(new Player(nick));
                    playerToGameMap.put(nick, gameName);
                }
                // or if the existing mode matches the selected mode
                else if (game.getGameMode() == selectedMode) {
                    // Join the game only if the player count is less than the game mode's capacity
                    if (game.getPlayersList().size() < selectedMode) {
                        game.addPlayers(new Player(nick));
                        playerToGameMap.put(nick, gameName);
                    }
                }
            }
        }
    }

    public void leaveGame(String nick) {
        String gameName = playerToGameMap.get(nick);
        if (gameName != null) {
            Game game = games.get(gameName);
            if (game != null) {
                game.removePlayer(nick);
                if (game.getPlayersList().isEmpty()) {
                    games.remove(gameName);
                }
                playerToGameMap.remove(nick);
            }
        }
    }

    // for later if needed to have info for UI file
    public List<Map<String, Object>> listGames() {
        List<Map<String, Object>> gameList = new ArrayList<>();

        for (Map.Entry<String, Game> entry : games.entrySet()) {
            Map<String, Object> gameInfo = new HashMap<>();
            String gameName = entry.getKey();
            Game game = entry.getValue();
            List<String> playerNicks = game.getPlayersList().stream()
                    .map(Player::getNick)
                    .collect(Collectors.toList());

            gameInfo.put("gameName", gameName);
            gameInfo.put("filledSlots", game.getPlayersList().size());
            gameInfo.put("maxSlots", game.getGameMode());
            gameInfo.put("playerNicks", playerNicks);
            gameList.add(gameInfo);
        }
        return gameList;
    }

    public void updateLobby(Vector<Game> activeGames) {
        this.games.clear();
        for (Game game : activeGames) {
            if (game.gameStatus) { // if true add to openGames = yes can join
                openGames.add(game);
            } else {
                LeaderBoard.put(game.getGameID(), game.leaderboard);
            }
        }

    }

    //turning playerToGameMap into a string so it can be converted in javascript
    public String mapToString()
    {
        String pairs= new String();
        for(Map.Entry<String,String> element: playerToGameMap.entrySet())
        {
            String key= element.getKey();
            
            String value = element.getValue();
            
            pairs=pairs+key+":"+value+",";
        }
        return pairs;
    }
}
