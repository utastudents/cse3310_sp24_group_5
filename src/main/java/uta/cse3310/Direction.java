package uta.cse3310;

/**
 * Enumeration representing different directions.
 */
public class Direction {

    /**
     * Enum representing the directions: HORIZONTAL, VERTICAL, and DIAGONAL.
     */
    enum Directions {
        HORIZONTAL, // Represents horizontal direction
        VERTICAL,   // Represents vertical direction
        DIAGONAL,
        RVERTICAL,
        RHORIZONTAL,
        RDIAGONAL;   // Represents diagonal direction

        /**
         * Method to get the row increment based on the direction.
         * @return The row increment.
         */
        public int getRowIncrement() {
            switch (this) {
                case HORIZONTAL:
                    return 0;
                case VERTICAL:
                    return 1;
                case DIAGONAL:
                    return 1;
                case RVERTICAL:
                    return -1;
                case RHORIZONTAL:
                    return 0;
                case RDIAGONAL:
                    return -1;

                default:
                    throw new IllegalStateException("Unexpected value: " + this);
            }
        }

        /**
         * Method to get the column increment based on the direction.
         * @return The column increment.
         */
        public int getColumnIncrement() {
            switch (this) {
                case HORIZONTAL:
                    return 1;
                case VERTICAL:
                    return 0;
                case DIAGONAL:
                    return 1;
                case RHORIZONTAL:
                    return 0;
                case RVERTICAL:
                    return -1;
                case RDIAGONAL:
                    return -1;
                default:
                    throw new IllegalStateException("Unexpected value: " + this);
            }
        }
    }
}
