package xyz.ipiepiepie.task_3;

import xyz.ipiepiepie.task_3.figure.HorizontalParabola;
import xyz.ipiepiepie.task_3.figure.Rectangle;
import xyz.ipiepiepie.task_3.figure.VerticalParabola;
import xyz.ipiepiepie.task_3.util.SimpleColor;

/**
 * Gets color of selected dot on graphic.
 * <p>
 * Task №6 from <a href="https://edu.vsu.ru/pluginfile.php/6688971/mod_folder/content/0/Task_03.doc?forcedownload=1">this list</a>
 */
public class Main {
    public static final VerticalParabola P1 = new VerticalParabola(3, 1, 1); // todo
    public static final HorizontalParabola P2 = new HorizontalParabola(-3, -4, 1); // todo
    public static final Rectangle R1 = new Rectangle(-3, 8,4, 8, 4, 9, -3, 9); // orange top
    public static final Rectangle R2 = new Rectangle(-3, 0,4, 0, 4, 8, -3, 8); // green-blue left
    public static final Rectangle R3 = new Rectangle(-3, -1,4, -1, 4, 0, -3, 0); // green-yellow bottom
    public static final Rectangle R4 = new Rectangle(4, -1,5, -1, 5, 8, 4, 8); // yellow right
    public static final Rectangle R5 = new Rectangle(1, -3,3, -3, 3, 2, 1, 2); // central
    
    
    public static void main(String[] args) {
        System.out.println(getColor(2, 10));
    }
    
    /**
     * Get {@link SimpleColor} for selected dot on graphic.
     *
     * @param x x-coordinate of dot
     * @param y y-coordinate of dot
     *
     * @return {@link SimpleColor} in dot position
     */
    public static SimpleColor getColor(int x, int y) {
        // dumb task needs dumb realisation =)
        if (R5.isPointInsideOfRectangle(x, y) && P1.isPointInsideOfParabola(x, y)) // orange inside parabola
            return SimpleColor.ORANGE;
        if (R2.isPointInsideOfRectangle(x, y) && P1.isPointInsideOfParabola(x, y)) // blue inside parabola
            return SimpleColor.BLUE;
        if (R2.isPointInsideOfRectangle(x, y) && R5.isPointRightOfRectangle(x)) // small orange lower of parabola
            return SimpleColor.ORANGE;
        if (R2.isPointInsideOfRectangle(x, y) && R5.isPointInsideOfRectangle(x, y)) // gray inside two rectangles
            return SimpleColor.GRAY;
        if (R2.isPointInsideOfRectangle(x, y)) // anything else inside R2
            return SimpleColor.GREEN;
        if (R1.isPointInsideOfRectangle(x, y)) // top orange
            return SimpleColor.ORANGE;
        if (R4.isPointInsideOfRectangle(x, y)) // right yellow
            return SimpleColor.YELLOW;
        if (P1.isPointInsideOfParabola(x, y)) // gray inside top parabola
            return SimpleColor.GRAY;
        if (P2.isPointRightOfParabola(x, y) && R5.isPointInsideOfRectangle(x, y))
            return SimpleColor.ORANGE;
        if (R5.isPointInsideOfRectangle(x, y))
            return SimpleColor.GREEN;
        if (R3.isPointInsideOfRectangle(x, y) && R5.isPointLeftOfRectangle(x))
            return SimpleColor.GREEN;
        if (R3.isPointInsideOfRectangle(x, y) && R5.isPointRightOfRectangle(x))
            return SimpleColor.YELLOW;
        if (P2.isPointRightOfParabola(x, y))
            return SimpleColor.YELLOW;
        if (!P2.isPointRightOfParabola(x, y) || R5.isPointLeftOfRectangle(x) || R2.isPointLeftOfRectangle(x) || R3.isPointLeftOfRectangle(x) || R1.isPointLeftOfRectangle(x) || x < 0)
            return SimpleColor.ORANGE;
        
        return SimpleColor.WHITE;
    }
}
