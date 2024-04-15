package uta.cse3310;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
public class Game //implements Chat, Timer, GridField, LeaderBoard, Wordlist
{
    private int gameMode;
    private String gameID;
    private ArrayList<Player>playersList = new ArrayList<Player>();
    private boolean gameStatus;
    private String wordsDone;
    private MyTimer timer;
    private int gameCount;
    private GridField gridField;


    public ArrayList<Player> getPlayersList() //I need to add this since Lobby class needs to access the game's player list _Liz
    {
        return new ArrayList<>(playersList);  
    }

    public void removePlayer(String nick) //I need to add this since Lobby need to manage players within a game _Liz
    {
        playersList.removeIf(player -> player.getNick().equals(nick));
    }
     
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
    public void updateGame(ArrayList<UserEvent> attempt) 
    {
       //if the first action is a click and the last action was a release
       if((attempt.get(0)).action==0 && (attempt.get(attempt.size()-1)).action==2 )
       {
            Player player=(attempt.get(0)).player;
            String word="";
            char[][]grid=gridField.getGrid();
            Iterator i= attempt.iterator();
            //iterate through each event and get the character that was affected
            //concatenate each letter until a word is formed
            while(i.hasNext())
            {
                UserEvent U = (UserEvent)i.next();
                int index= U.cell;
                int row = index/50;
                int column= index%50;
                
                word=word+grid[index][column];
                System.out.println(word);
            }
            wordChosen(word,player);
       }
    }

     // Method for players to exit game
    public void exitGame(ArrayList<Player> playersList) 
    {
        //declare winner
        int highestScore=0;
        Player winner=null;
        Iterator i=playersList.iterator();
        while(i.hasNext())
        {
            Player currPlayer=(Player)i.next();
            if(currPlayer.getScore()>highestScore)
            {
                highestScore=currPlayer.getScore();
                winner=currPlayer;
            }
        }
        System.out.println(winner.getNick() + " is the winner");
        System.out.println(winner.getNick() +" scored "+ winner.getScore() +" points");
        i=playersList.iterator();
        while(i.hasNext())
        {
            Player currPlayer=(Player)i.next();
            if(currPlayer!=winner)
            {
                System.out.println(currPlayer.getNick()+" scored "+ currPlayer.getScore() +" points");
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
    //may be obsolete
    public boolean validWord(String word) 
    {
        
        return gridField.checkWord(word);
    }

    /* Method for handling chosen word
    the word if valid should be highlighted in the player's color
    and after being revealed, the method will check if there are any
    more remaining words*/
    public void wordChosen(String selectedWord,Player player) 
    {
        if(gridField.checkWord(selectedWord)==true)
        {
            gridField.revealWord(selectedWord);//fully highlights word
            player.increaseScore(100*selectedWord.length());//give player points
            if(gridField.getRemainingWords()==0)//exit game if no words remain
            {
                exitGame(playersList);
            }
        }
        
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
    public void displayRules() {

    }
}
