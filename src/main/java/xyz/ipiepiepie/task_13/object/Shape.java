package xyz.ipiepiepie.task_13.object;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents {@link Piece} shape.
 */
public enum Shape {
    EMPTY(new int[][] {
            {0, 0},
            {0, 0},
            {0, 0},
            {0, 0}
    }, Color.BLACK),
    SQUARE(new int[][] {
            {0, 0},
            {1, 0},
            {0, 1},
            {1, 1}
    }, Color.ORANGE),
    T(new int[][] {
            {-1, 0},
            {0, 0},
            {1, 0},
            {0, 1}
    }, Color.MAGENTA),
    ZAP(new int[][] {
            {0, -1},
            {0, 0},
            {1, 0},
            {1, 1}
    }, Color.GREEN),
    REVERTED_ZAP(new int[][] {
            {0, -1},
            {0, 0},
            {-1, 0},
            {-1, 1}
    }, Color.RED),
    L(new int[][] {
            {-1, -1},
            {0, -1},
            {0, 0},
            {0, 1}
    }, Color.YELLOW),
    REVERTED_L(new int[][] {
            {1, -1},
            {0, -1},
            {0, 0},
            {0, 1}
    }, Color.BLUE),
    LINE(new int[][] {
            {0, -1},
            {0, 0},
            {0, 1},
            {0, 2}
    }, Color.CYAN),
    ;

    /**
     * Get random {@link Shape}.
     * <p>
     * Excludes {@link Shape#EMPTY Empty Shape}.
     *
     * @return random {@link Shape}
     */
    public static Shape random() {
        // exclude EMPTY shape (zero index) from indexes.
        int index = 1 + new Random(System.currentTimeMillis()).nextInt(Shape.values().length - 1);

        return Shape.values()[index];
    }

    /// SHAPE METHODS ///

    // color //
    private final Color color;
    // coordinates //
    private final int[][] coordinates;

    /**
     * Shape constructor
     *
     * @param coordinates coordinates, representing shape
     */
    Shape(int[][] coordinates, Color color) {
        this.coordinates = coordinates;
        this.color = color;
    }

    /**
     * Create {@link Piece} {@link Shape} with {@link Block Blocks}.
     *
     * @return created {@link Block Blocks}
     */
    public List<Block> create() {
        List<Block> blocks = new ArrayList<>();

        // create blocks for piece
        for (int[] coordinates : getCoordinates()) {
            // skip coordinate array if it has wrong size
            if (coordinates.length != 2) continue;
            // read coordinate
            Coordinate coordinate = new Coordinate(coordinates[0], coordinates[1]);

            // add generated block to blocks list
            blocks.add(new Block(color, coordinate));
        }

        return blocks;
    }

    /**
     * Get current shape coordinates
     *
     * @return coordinates of shape
     */
    public int[][] getCoordinates() {
        return this.coordinates;
    }

}
