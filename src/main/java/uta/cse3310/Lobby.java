package uta.cse3310;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors; 

public class Lobby {
    private Map<String, Game> games;
    private Map<String, String> playerToGameMap;

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


    //checks if the nickname is not already in use
    //adds it to the playerToGameMap if it's available
    public void enterNickname(String nick) {
        if (!playerToGameMap.containsKey(nick)) 
        {
            playerToGameMap.put(nick, null);
        }
    }

    public void joinGame(String nick, int gameIndex, int modeIndex) {
        List<String> availableGames = new ArrayList<>(games.keySet());
        List<Integer> availableModes = Arrays.asList(2, 3, 4); // direct representation of modes

        if (gameIndex >= 0 && gameIndex < availableGames.size() && modeIndex >= 0 && modeIndex < availableModes.size()) 
        {   
            // checks if the indices are valid
            String gameName = availableGames.get(gameIndex);

            Game game = games.get(gameName);

            if (game != null) 
            { // checks if the selected game exists in the list of game
                int selectedMode = availableModes.get(modeIndex); // directly use the mode from the list

                if (game.getPlayersList().isEmpty()) //if no player in that game before, pick game, then pick mode of the game
                {
                    game.setGameMode(selectedMode);
                } 
                else if (game.getGameMode() == selectedMode) //or join the game that need  slot filled
                {
                    if (game.getPlayersList().size() < game.getGameMode()) 
                    {
                        game.addPlayers(new Player(nick));
                        playerToGameMap.put(nick, gameName);
                    }
                }
            }
        }
    }


    public void leaveGame(String nick) {
        String gameName = playerToGameMap.get(nick);
        if (gameName != null) 
        {
            Game game = games.get(gameName);
            if (game != null) 
            {
                game.removePlayer(nick);
                if (game.getPlayersList().isEmpty()) 
                {
                    games.remove(gameName);
                }
                playerToGameMap.remove(nick);
            }
        }
    }


    //for later if needed to have info for UI file
    public List<Map<String, Object>> listGames() {
        List<Map<String, Object>> gameList = new ArrayList<>();

        for (Map.Entry<String, Game> entry : games.entrySet()) 
        {
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
}
