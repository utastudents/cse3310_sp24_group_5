import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Unit tests for the Direction enum.
 */
public class DirectionTest {
    
    /**
     * Test case to verify the behavior of the horizontal direction.
     */
    @Test
    public void testHorizontalDirection() {
        // Arrange
        Direction direction = Direction.horizontal;
        
        // Act
        // No action needed
        
        // Assert
        assertEquals("Horizontal direction should be 'horizontal'", "horizontal", direction.toString());
    }
    
    /**
     * Test case to verify the behavior of the vertical direction.
     */
    @Test
    public void testVerticalDirection() {
        // Arrange
        Direction direction = Direction.vertical;
        
        // Act
        // No action needed
        
        // Assert
        assertEquals("Vertical direction should be 'vertical'", "vertical", direction.toString());
    }
    
    /**
     * Test case to verify the behavior of the diagonal direction.
     */
    @Test
    public void testDiagonalDirection() {
        // Arrange
        Direction direction = Direction.diagonal;
        
        // Act
        // No action needed
        
        // Assert
        assertEquals("Diagonal direction should be 'diagonal'", "diagonal", direction.toString());
    }
}

