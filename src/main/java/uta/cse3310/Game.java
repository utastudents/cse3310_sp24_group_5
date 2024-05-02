package uta.cse3310;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.UUID;
import java.util.Set;
import java.util.HashSet;
public class Game // implements Chat, Timer, GridField, LeaderBoard, Wordlist
{
    private int gameMode;
    private String gameID;
    private Set<String> usedColors;
    private ArrayList<Player> playersList = new ArrayList<Player>();
    public Player winner = null;
    public boolean gameStatus;
    private String wordsDone;
    private MyTimer timer;
    private GridField gridField=new GridField();
    public ArrayList<String> wordList = new ArrayList<String>();
    public ArrayList<Player> leaderboard;
    public char[][] grid=null;
    public Game() {
        this.gameMode = gameMode;
        this.gameID = UUID.randomUUID().toString();
        gameStatus = false;
        usedColors = new HashSet<>();
    }

    public ArrayList<Player> getPlayersList() // I need to add this since Lobby class needs to access the game's player
                                              // list _Liz
    {
        if(playersList==null)
        {
            System.out.println("The list is null");
        }
        return playersList;
    }

    public void removePlayer(String nick) // I need to add this since Lobby need to manage players within a game _Liz
    {
        playersList.removeIf(player -> player.getNick().equals(nick));
    }

    // Method to determine gamemode
    public int getGameMode() {
        return gameMode;
    }

    // Method to set the gameMode
    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    // Method to get players game ID
    public String getGameID() {
        return gameID;
    }

    // Method to set game ID
    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    // Method to get players game grid
    public GridField getGridField() {
        return gridField;
    }

    public void setGridField(GridField gridField) {
        this.gridField = gridField;
    }

    public boolean getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(boolean gameStatus) {
        this.gameStatus = gameStatus;
    }

    // Method to start game
    public void startGame() {
        
        // gridField=new GridField(wordList);
        gridField.generateGrid(25);
        grid=gridField.getGrid();
        gameStatus = true;
    }

    // Method for adding players to the game screen
    public void addPlayers(Player newPlayer) {
        playersList.add(newPlayer);
    }

    // Method to update game state
    public boolean updateGame(ArrayList<UserEvent> attempt) {

        Player player = (attempt.get(0)).player;
        String word = "";
        char[][] grid = gridField.getGrid();
        Iterator i = attempt.iterator();
        // iterate through each event and get the character that was affected
        // concatenate each letter until a word is formed
        while (i.hasNext()) {
            UserEvent U = (UserEvent) i.next();
            int index = U.cell;
            int row = index / 25;
            int column = index % 25;

            word = word + grid[row][column];
            word=word.toLowerCase();
            System.out.println(word);
        }
        return wordChosen(word, player);
        
       
    }

    // Method for players to exit game
    public void exitGame(ArrayList<Player> playersList) {
        // declare winner
        int highestScore = 0;
        Iterator i = playersList.iterator();
        while (i.hasNext()) {
            Player currPlayer = (Player) i.next();
            if (currPlayer.getScore() > highestScore) {
                highestScore = currPlayer.getScore();
                winner = currPlayer;
            }
        }
        System.out.println(winner.getNick() + " is the winner");
        System.out.println(winner.getNick() + " scored " + winner.getScore() + " points");
        i = playersList.iterator();
        while (i.hasNext()) {
            Player currPlayer = (Player) i.next();
            if (currPlayer != winner) {
                System.out.println(currPlayer.getNick() + " scored " + currPlayer.getScore() + " points");
            }

        }
        // remove players
        //playersList.clear();
        gameStatus = false;
    }

    /*
     * Method for handling chosen word
     * the word if valid should be highlighted in the player's color
     * and after being revealed, the method will check if there are any
     * more remaining words
     */
    public boolean wordChosen(String selectedWord, Player player) {
        System.out.println(selectedWord);
        if (gridField.checkWord(selectedWord) == true) 
        {
            gridField.revealWord(selectedWord);// fully highlights word
            player.increaseScore(100 * selectedWord.length());// give player points
            if (gridField.getRemainingWords() == 0)// exit game if no words remain
            {
                exitGame(playersList);
            }
            return true;
        }
        else
        {
            return false;
        }
        
    }

    // Method to update the game timer"Shot Clock"
    public void updateTimer() {
        while (timer.isRunning()) {
            System.out.println(timer.getTimeRemaining());
        }
        if(timer.isStopped())
        {
            this.exitGame(playersList);
        }
    }

    // Method to display rules of game
    public void displayRules() {

    }

    public boolean addPlayer(Player newPlayer) {
        if (isUniquePlayerName(newPlayer.getNick())) {
            if (isUniquePlayerColor(newPlayer.getColor())) {
                playersList.add(newPlayer);
                return true; 
            } else {
                System.out.println("Color is already in use.");
            }
        } else {
            System.out.println("Player name is already in use.");
        }
        return false; 
    }
     
    private boolean isUniquePlayerName(String playerName) {
        for (Player player : playersList) {
            if (player.getNick().equals(playerName)) {
                return false;
            }
        }
        return true;
    }

    private boolean isUniquePlayerColor(String playerColor) {
        return !usedColors.contains(playerColor);
    }
}
 