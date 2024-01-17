package xyz.ipiepiepie.task_13.object;

import xyz.ipiepiepie.task_13.Game;

import java.awt.*;

public class Block {
    // color //
    private final Color color;
    // position //
    private Coordinate absolutePosition;
    private final Coordinate relativePosition;

    /**
     * Constructor for new instances.
     *
     * @param relativePosition relative position inside piece.
     */
    public Block(Color color, Coordinate relativePosition) {
        this(color, relativePosition, new Coordinate((Game.BOARD_WIDTH / 2) - 1, 0));
    }

    /**
     * Constructor for inherated instances.
     *
     * @param relativePosition relative position inside piece.
     * @param absolutePosition absolute position on board.
     */
    public Block(Color color, Coordinate relativePosition, Coordinate absolutePosition) {
        this.color = color;
        this.relativePosition = relativePosition;
        this.absolutePosition = absolutePosition;
    }

    /// COLOR ///

    public Color getColor() {
        return color;
    }

    /// MOVE ///

    /**
     * Move current Block on Board.
     * <p>
     * <b>NOTE!</b> Block shouldn't be part of {@link Piece}.
     *
     * @param x X-axis shift
     * @param y Y-axis shift
     */
    public boolean move(int x, int y) {
        // calculate block shift
        Coordinate shift = new Coordinate(x, y);

        // set new absolute position
        this.absolutePosition = absolutePosition.add(shift);

        return true;
    }

    /// CURRENT ///

    public Coordinate getCurrentPosition() {
        return new Coordinate(
                (absolutePosition.getX() + relativePosition.getX()),
                (absolutePosition.getY() + relativePosition.getY())
        );
    }

    /// RELATIVE ///

    public Coordinate getRelativePosition() {
        return relativePosition;
    }

    /// ABSOLUTE ///

    public Coordinate getAbsolutePosition() {
        return absolutePosition;
    }

}
