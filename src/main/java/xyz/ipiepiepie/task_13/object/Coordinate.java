package xyz.ipiepiepie.task_13.object;

/**
 * Represents 2-D Coordinate.
 */
public class Coordinate {
    private int x;
    private int y;


    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /// ADD ///

    public Coordinate add(Coordinate coordinate) {
        return new Coordinate(x + coordinate.getX(), y + coordinate.getY());
    }

    /// MULTIPLY ///

    public void multiply(int multiplier) {
        x *= multiplier;
        y *= multiplier;
    }

    /// ABSCISSA ///

    /**
     * Get X (abscissa) coordinate.
     *
     * @return X-axis coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Set X (abscissa) coordinate.
     *
     * @param x X-axis coordinate.
     */
    public void setX(int x) {
        this.x = x;
    }

    /// ORDINATE ///

    /**
     * Get Y (ordinate) coordinate.
     *
     * @return Y-axis coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Set Y (ordinate) coordinate.
     *
     * @param y Y-axis coordinate.
     */
    public void setY(int y) {
        this.y = y;
    }

    /// STRING ///

    public String toString() {
        return String.format("(%d, %d)", getX(), getY());
    }

    /// EQUALS ///

    public boolean equals(Object object) {
        if (!(object instanceof Coordinate coordinate)) return false;

        return coordinate.getX() == x && coordinate.getY() == y;
    }

}
