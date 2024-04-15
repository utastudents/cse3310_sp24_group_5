package uta.cse3310;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GridFieldTest {

    @Before
    public void setUp() {
        // Update port assignments to 9005 for HTTP and 9105 for websockets
        System.setProperty("HTTP_PORT", "9005");
        System.setProperty("WEBSOCKET_PORT", "9105");
    }

    @Test
    public void testGenerateGrid() {
        GridField gridField = new GridField(new ArrayList<>());
        gridField.generateGrid(5);
        assertNotNull(gridField.getGrid());
        assertEquals(5, gridField.getGrid().length);
        assertEquals(5, gridField.getGrid()[0].length);
    }

    @Test
    public void testCheckWord() {
        ArrayList<String> wordList = new ArrayList<>();
        wordList.add("HELLO");
        wordList.add("WORLD");
        GridField gridField = new GridField(wordList);
        assertTrue(gridField.checkWord("HELLO"));
        assertFalse(gridField.checkWord("GOODBYE"));
    }

    @Test
    public void testGetRemainingWords() {
        ArrayList<String> wordList = new ArrayList<>();
        wordList.add("HELLO");
        wordList.add("WORLD");
        GridField gridField = new GridField(wordList);
        assertEquals(2, gridField.getRemainingWords());
    }

    @Test
    public void testRevealWord() {
        ArrayList<String> wordList = new ArrayList<>();
        wordList.add("HELLO");
        wordList.add("WORLD");
        GridField gridField = new GridField(wordList);
        gridField.revealWord("HELLO");
        assertEquals(1, gridField.getRemainingWords());
    }

    @Test
    public void testAddWord() {
        ArrayList<String> wordList = new ArrayList<>();
        GridField gridField = new GridField(wordList);
        gridField.addWord("HELLO", 0, 0, Direction.Directions.HORIZONTAL);
        assertEquals(1, gridField.getRemainingWords());
        assertTrue(gridField.checkWord("HELLO"));
    }

    // Add more test methods as needed
}
