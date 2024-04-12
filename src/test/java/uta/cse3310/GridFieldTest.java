package uta.cse3310;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GridFieldTest {

    @Test
    public void testGenerateGrid() {
        GridField gridField = new GridField();
        gridField.generateGrid();
        // Implement test logic to verify the generated grid
    }

    @Test
    public void testCheckWord() {
        GridField gridField = new GridField();
        assertTrue(gridField.checkWord("word"));
        assertFalse(gridField.checkWord("invalid"));
    }

    @Test
    public void testGetRemainingWords() {
        GridField gridField = new GridField();
        assertEquals(0, gridField.getRemainingWords());
    }

    @Test
    public void testRevealWord() {
        GridField gridField = new GridField();
        gridField.revealWord("words");
        // Implement test logic to verify word revealing
    }

    @Test
    public void testAddWord() {
        GridField gridField = new GridField();
        gridField.addWord("word", Direction.HORIZONTAL);
        // Implement test logic to verify word addition
    }

    @Test
    public void testDisplayGrid() {
        GridField gridField = new GridField();
        gridField.displayGrid();
        // Implement test logic to verify grid display
    }

    // Add more test methods as needed
}
