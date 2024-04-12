package uta.cse3310;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Test class for the Direction enum.
 */
public class DirectionTest {

    /**
     * Test method to verify the correctness of enum values.
     */
    @Test
    public void testValues() {
        Direction[] directions = Direction.values();
        assertEquals(3, directions.length); // Ensure there are 3 enum values
        assertEquals(Direction.horizontal, directions[0]); // Check the first enum value
        assertEquals(Direction.vertical, directions[1]);   // Check the second enum value
        assertEquals(Direction.diagonal, directions[2]);   // Check the third enum value
    }
}

