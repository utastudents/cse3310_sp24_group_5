package uta.cse3310;

public class Game 
{
    private int gameMode;
    private String gameID;
    private Player[] playersList;
    private boolean gameStatus;
    private String wordsDone;
    private Timer timer;
    private int gameCount;

     // Method to determine gamemode
    public int getGameMode() 
    {
        return gameMode;
    }

     // Method to get players game ID
    public String getGameID() 
    {
        return gameID;
    }

     // Method to start game
    public void startGame() 
    {
        
    }

     // Method for adding players to the game screen
    public Player[] addPlayers() 
    {
        return null;
    }

     // Method to update game state
    public void updateGame() 
    {
       
    }

     // Method for players to exit game
    public void exitGame() 
    {
        
    }

     // Method for chat implementation in game
    public void chat(String message) 
    {
       
    }

    // Method to check for a valid word
    public boolean validWord(String word) 
    {
        return false;
    }

    // Method for handlind chosen word
    public String[] wordChosen(Data file) 
    {
        return null;
    }

    // Method to update the game timer"Shot Clock"
    public void updateTimer() 
    {
        
    }
}
