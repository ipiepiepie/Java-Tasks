package xyz.ipiepiepie.geometry.figure;

public class Rectangle {
    public double x0;
    public double y0;
    public double x1;
    public double y1;
    public double x2;
    public double y2;
    public double x3;
    public double y3;
    
    public Rectangle(double x0, double y0, double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }
    
    public boolean isPointInsideOfRectangle(double x, double y) {
        return (x >= x0) && (x <= x2) && (y >= y0) && (y <= y2);
    }
    
    public boolean isPointLeftOfRectangle(double x) {
        return x < x0;
    }
    
    public boolean isPointRightOfRectangle(double x) {
        return x > x2;
    }
    
}
