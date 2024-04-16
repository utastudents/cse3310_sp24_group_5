package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.ArrayList;
import java.lang.Boolean;
import java.util.Iterator;
public class gameTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public gameTest(String test)
    {
        super(test);
    }
    /**
     * @return the suite of tests being tested
     */
    public static Test suite() 
    {
        return new TestSuite(gameTest.class);
    }

    //The following are the unit tests for the game class
    //-----------------------------------------------------------
    public void testGame()
    {
        Game game = new Game();
        //game.startGame();
        ArrayList<String> wordList = new ArrayList<String>(){
            {
                add("hello");
                add("goodbye");
                add("testing");
                add("computer");
                add("code");
            }
            
        };
        GridField gridField=new GridField(wordList);
        game.setGridField(gridField);
        Player player1=new Player("tom");
        Player player2=new Player("steve");
        game.addPlayers(player1);
        game.addPlayers(player2);
        game.startGame();
        try
        {   int score=player1.getScore();
            Iterator i=wordList.iterator();
            while(i.hasNext())
            {
                String param=String.valueOf(i.next());
                game.wordChosen(param,player1);
                assertTrue(player1.getScore()==100*param.length()+score);
                score+=param.length();
            }
            assertTrue(gridField.getRemainingWords()==0);
        }
        catch(Exception e)
        {

        }
        
    }
    public void testExit()
    {
        Game game= new Game();
        //create players
        Player player1= new Player("tom");
        Player player2= new Player("steve");
        Player player3= new Player("sam");
        //add players to the game
        game.addPlayers(player1);
        game.addPlayers(player2);
        game.addPlayers(player3);
        
        ArrayList<String> wordList = new ArrayList<String>(){
            {
                add("hello");
                add("goodbye");
                add("testing");
                add("computer");
                add("code");
            }
        };
        GridField gridField=new GridField(wordList);
        game.setGridField(gridField);
        game.startGame();
        game.setGridField(gridField);
        game.wordChosen("testing",player1);
        game.wordChosen("computer",player1);
        game.wordChosen("computer",player1);
        game.wordChosen("code",player2);
        game.wordChosen("hello",player3);
        game.exitGame(game.getPlayersList());
        assertTrue(game.winner==player1);
        ArrayList<Player> playersList= game.getPlayersList();
        assertTrue(playersList.size()==0);
        assertTrue(game.getGameStatus()==false);


    }

    public void testUpdate()
    {
        //initialize game
        Game game= new Game();
        //create players
        Player player1= new Player("tom");
        
        //add players to the game
        game.addPlayers(player1);
        
        ArrayList<String> wordList = new ArrayList<String>(){
            {
                add("hello");
                add("goodbye");
                add("testing");
                add("computer");
                add("code");
            }
        };
        GridField gridField=new GridField(wordList);
        game.setGridField(gridField);
        game.startGame();
        game.setGridField(gridField);
        game.startGame();
        char[][] grid =gridField.getGrid();
        //test word
        String word="";
        for(int i=0;i<4;i++)
        {
            word=word+ String.valueOf(grid[1][i]);
        }
        gridField.getWordList().add(word);
        //user event array
        UserEvent U1= new UserEvent();
        UserEvent U2= new UserEvent();
        UserEvent U3= new UserEvent();
        UserEvent U4= new UserEvent();
        UserEvent U5= new UserEvent();
        U1.player=player1;
        U1.cell=50;
        U1.action=0;
        U2.player=player1;
        U2.cell=51;
        U2.action=1;
        U3.player=player1;
        U3.cell=52;
        U3.action=1;
        U4.player=player1;
        U4.cell=53;
        U4.action=2;
        ArrayList<UserEvent> attempt = new ArrayList<UserEvent>(){
            {
                add(U1);
                add(U2);
                add(U3);
                add(U4);
            }
        };
        
        
        game.updateGame(attempt);
        assertTrue(wordList.size()==5);

    }
}