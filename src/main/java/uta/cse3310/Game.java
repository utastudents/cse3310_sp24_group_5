package uta.cse3310;

import java.util.ArrayList;
import java.util.Scanner;
public class Game //implements Chat, Timer, GridField, LeaderBoard, Wordlist
{
    private int gameMode;
    private String gameID;
    private ArrayList<Player>playersList = new ArrayList<Player>();
    private boolean gameStatus;
    private String wordsDone;
    private Timer timer;
    private int gameCount;
    private GridField gridField;

     
     // Method to determine gamemode
    public int getGameMode() 
    {
        return gameMode;
    }
    //Method to set the gameMode
    public void setGameMode(int gameMode)
    {
        this.gameMode=gameMode;
    }
    // Method to get players game ID
    public String getGameID() 
    {
        return gameID;
    }
    //Method to set game ID
    public void setGameID(String gameID)
    {
        this.gameID=gameID;
    }
    // Method to get players game grid
    public GridField getGridField() 
    {
        return gridField;
    }
    
    // Method to start game
    public boolean startGame() 
    {
        displayRules();
        gridField.generateGrid();
        return true;
    }

     // Method for adding players to the game screen
    public void addPlayers(Player newPlayer) 
    {
        playersList.add(newPlayer);
    }

     // Method to update game state
    public void updateGame() 
    {
       
    }

     // Method for players to exit game
    public void exitGame(ArrayList<Player> playersList) 
    {
        //declare winner
        int highestScore=0;
        Player winner;
        while(playersList.hasNext())
        {
            Player currPlayer=playersList.next();
            if(currPlayer.score>highestScore)
            {
                highestScore=currPlayer.score;
                winner=currPlayer;
            }
        }
        System.out.pritntln(winner.nick +" is the winner")
        System.out.println(winner.nick+" scored "winner.score+" points")
        while(playersList.hasNext())
        {
            Player currPlayer=playersList.next();
            if(currPlayer!=winner)
            {
                System.out.println(currPlayer.nick+" scored "currPlayer.score+" points")
            }
            
        }
       //remove players
       playersList.clear();
    }

     // Method for chat implementation in game
    public void chat(Player sender,String message) 
    {
       
    }

    // Method to check for a valid word
    public boolean validWord(String word) 
    {
        
        return gridField.checkWord(word);
    }

    // Method for handling chosen word
    public String[] wordChosen(String file) 
    {
        return null;
    }

    // Method to update the game timer"Shot Clock"
    public void updateTimer() 
    {
        while(timer.isRunning())
        {
            System.out.println(timer.getTimeRemaining());
        }
    }

    // Method to display rules of game
    public void displayRules()
    {
        
    }
}
