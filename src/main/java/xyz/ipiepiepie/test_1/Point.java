package xyz.ipiepiepie.test_1;

/**
 * Represents 2D Point.
 */
public class Point {
    private final int x;
    private final int y;
    
    /**
     * Constructor for new instances
     *
     * @param x X axis coordinate
     * @param y Y axis coordinate
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /// DISTANCE ///
    
    /**
     * Calculate distance to another point.
     *
     * @param point point to calculate distance
     * @return distance between points
     */
    public double distance(Point point) {
        return Math.sqrt(Math.pow(x - point.getX(), 2) + Math.pow(y - point.getY(), 2));
    }
    
    /// GETTERS ///
    
    /**
     * Get X axis coordinate
     * @return coordinate
     */
    public int getX() {
        return x;
    }
    
    /**
     * Get Y axis coordinate
     * @return coordinate
     */
    public int getY() {
        return y;
    }
    
    /// OVERRIDE ///
    
    @Override
    public String toString() {
        return String.format("(%d, %d)", getX(), getY());
    }
    
}
