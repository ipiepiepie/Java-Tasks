package xyz.ipiepiepie.geometry.figure;

/**
 * Represents simple line.
 */
public class Line {
    public double x;
    public double b;
    public double k;
    
    /**
     * Constructor for new instances.
     */
    public Line(double x, double b, double k) {
        this.x = x;
        this.b = b;
        this.k = k;
    }
    
    /**
     * Check if point is above current line
     */
    public boolean isPointAboveLine(double x, double y) {
        return y > k * (x - this.x) + b;
    }
}
