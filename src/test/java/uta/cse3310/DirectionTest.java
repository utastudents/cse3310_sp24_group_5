package uta.cse3310;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {

    private Direction direction;

    @Before
    public void setUp() {
        this.direction = new Direction();
    }

    @Test
    public void testDirectionEnum() {
        assertEquals(Direction.Directions.HORIZONTAL, Direction.Directions.valueOf("HORIZONTAL"));
        assertEquals(Direction.Directions.VERTICAL, Direction.Directions.valueOf("VERTICAL"));
        assertEquals(Direction.Directions.DIAGONAL, Direction.Directions.valueOf("DIAGONAL"));
    }

    // Add more test methods as needed
}
