package uta.cse3310;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class GridFieldTest {

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
        gridField.addWord("HELLO", 0, 0, Direction.Directions.HORIZONTAL); // Adjusted here
        assertEquals(1, gridField.getRemainingWords());
        assertTrue(gridField.checkWord("HELLO"));
    }

    @Test
    public void testPlaceRandomWords() {
        // Create a word list with some words
        ArrayList<String> wordList = new ArrayList<>(Arrays.asList("HELLO", "WORLD", "JAVA", "PROGRAMMING"));

        // Create a grid field
        GridField gridField = new GridField(wordList);

        // Place random words on the grid
        gridField.placeRandomWords();

        // Get the grid from the grid field
        char[][] grid = gridField.getGrid();

        // Check if the grid is not null
        assertNotNull(grid);

        // Check if the grid size is within a reasonable range
        assertTrue(grid.length >= 5 && grid.length <= 10);
        assertTrue(grid[0].length >= 5 && grid[0].length <= 10);
    }
}
