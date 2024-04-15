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
        game.startGame();
        ArrayList<String> wordList = new ArrayList<String>("hello", "goodbye","testing","computer","code");
        GridField gridField=GridField(wordList);
        game.setGridField(gridField);
        Player player1=new Player("tom");
        Player player1=new Player("steve");
        game.addPlayers(player1);
        game.addPlayers(player2);
        try
        {   int score=player1.getScore();
            Iterator i=wordList.iterator();
            while(i.hasNext())
            {
                String param=String.valueOf(i.next());
                game.wordChosen(param,player1);
                assertTrue(player1.getScore()==param.length()+score);
            }
            assertTrue(gridField.getRemainingWords()==0);
        }
        catch(Exception e)
        {

        }
        
    }
    public void testUpdate()
    {
        


    }
}