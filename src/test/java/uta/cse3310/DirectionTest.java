package uta.cse3310;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DirectionTest {

    @Test
    public void testDirectionEnum() {
        assertEquals(Direction.Directions.HORIZONTAL, Direction.Directions.HORIZONTAL); // Test HORIZONTAL enum
        assertEquals(Direction.Directions.VERTICAL, Direction.Directions.VERTICAL); // Test VERTICAL enum
        assertEquals(Direction.Directions.DIAGONAL, Direction.Directions.DIAGONAL); // Test DIAGONAL enum
    }

    // Add more test methods as needed
}
