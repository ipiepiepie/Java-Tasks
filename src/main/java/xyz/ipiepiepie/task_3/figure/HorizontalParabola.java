package xyz.ipiepiepie.task_3.figure;

/**
 * Represents horizontal parabola.
 */
public class HorizontalParabola {
    public double x0;
    public double y0;
    public double a;
    
    public HorizontalParabola(double x0, double y0, double a) {
        this.x0 = x0;
        this.y0 = y0;
        this.a = a;
    }
    
    /**
     * Check if point right from current parabola
     *
     * @param x x-coordinate of point
     * @param y y-coordinate of point
     *
     * @return {@code true} if point right of current parabola, otherwise {@code false}
     */
    public boolean isPointRightOfParabola(double x, double y) {
        return x > a * Math.pow(y - y0, 2) + x0;
    }
    
}
