package uta.cse3310;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DirectionTest {

    @Test
    public void testDirectionEnum() {
        assertEquals(Direction.HORIZONTAL, Direction.valueOf("HORIZONTAL"));
        assertEquals(Direction.VERTICAL, Direction.valueOf("VERTICAL"));
        assertEquals(Direction.DIAGONAL, Direction.valueOf("DIAGONAL"));
    }

    // Add more test methods as needed
}
