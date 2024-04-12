import junit.framework.TestCase;

public class DirectionTest extends TestCase {

    public void testToString() {
        assertEquals("horizontal", Direction.HORIZONTAL.toString());
        assertEquals("vertical", Direction.VERTICAL.toString());
        assertEquals("diagonal", Direction.DIAGONAL.toString());
    }

    public void testOrdinal() {
        assertEquals(0, Direction.HORIZONTAL.ordinal());
        assertEquals(1, Direction.VERTICAL.ordinal());
        assertEquals(2, Direction.DIAGONAL.ordinal());
    }

    public void testValues() {
        Direction[] values = Direction.values();
        assertEquals(3, values.length);
        assertEquals(Direction.HORIZONTAL, values[0]);
        assertEquals(Direction.VERTICAL, values[1]);
        assertEquals(Direction.DIAGONAL, values[2]);
    }

    public void testEquals() {
        assertTrue(Direction.HORIZONTAL.equals(Direction.HORIZONTAL));
        assertFalse(Direction.HORIZONTAL.equals(Direction.VERTICAL));
    }

    public void testValueOf() {
        assertEquals(Direction.HORIZONTAL, Direction.valueOf("HORIZONTAL"));
        assertEquals(Direction.VERTICAL, Direction.valueOf("VERTICAL"));
        assertEquals(Direction.DIAGONAL, Direction.valueOf("DIAGONAL"));
    }
}
