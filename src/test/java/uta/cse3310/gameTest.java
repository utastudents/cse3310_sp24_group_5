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
        GridField gridField=game.getGridField();
        ArrayList<String> wordList = new ArrayList<String>("hello", "goodbye","testing","computer","code");
        ArrayList<String> words= new ArrayList<String>();
        try
        {
            words.addAll(wordList);
            //test that all words remaining 
            //in the word bank will register as a valid word
            Iterator i=words.iterator();
            while(i.hasNext())
            {
                String param=String.valueOf(i.next());
                assertTrue(game.validWord()==true);
            }
        }
        catch(Exception e)
        {

        }
        
    }
    public void testWordChosen()
    {
        


    }
}