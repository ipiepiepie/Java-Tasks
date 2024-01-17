package xyz.ipiepiepie.task_13.object;

import xyz.ipiepiepie.task_13.Main;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Piece {
    private final Shape shape;
    private List<Block> blocks;
    // state //
    private boolean falling = true;

    /**
     * Constructor for new Piece.
     *
     * @param shape {@link Shape}
     */
    public Piece(Shape shape) {
        this.shape = shape;
        this.blocks = shape.create();
    }

    /// MOVE ///

    /**
     * Move current Piece on Board.
     *
     * @param x X-axis shift
     * @param y Y-axis shift
     */
    public boolean move(int x, int y) {
        // calculate block shift
        Coordinate shift = new Coordinate(x, y);

        // moved blocks map
        List<Block> blocks = new ArrayList<>();

        // shift all blocks
        for (Block block : this.blocks) {
            Coordinate absolute = block.getAbsolutePosition().add(shift);
            Block moved = new Block(block.getColor(), block.getRelativePosition(), absolute);

            // stop moving if block can't be moved
            if (!Main.getGameInstance().validate(moved.getCurrentPosition())) return false;

            // cache moved block
            blocks.add(moved);
        }

        // apply block changes
        this.blocks = blocks;

        return true;
    }

    /// ROTATION ///

    /**
     * Rotate current Piece to the left.
     */
    public boolean rotateLeft() {
        // skip square shape rotation
        if (shape == Shape.SQUARE) return true;

        // rotated blocks map
        List<Block> blocks = new ArrayList<>();

        // rotate blocks inside piece
        for (Block block : this.blocks) {
            // rotate relative
            Coordinate relative = new Coordinate(
                    block.getRelativePosition().getY(),
                    -block.getRelativePosition().getX()
            );
            Block rotated = new Block(block.getColor(), relative, block.getAbsolutePosition());

            // stop rotation if block can't be moved
            if (!Main.getGameInstance().validate(rotated.getCurrentPosition())) return false;

            // cache rotated block
            blocks.add(rotated);
        }

        // apply block changes
        this.blocks = blocks;

        return true;
    }

    /// BLOCKS ///

    /**
     * Find the highest relative {@link Block}.
     *
     * @return {@link Block} with highest Y-axis relative coordinate.
     */
    public Block getHighestBlock() {
        return blocks.stream()
                .max(Comparator.comparing(block -> block.getRelativePosition().getY()))
                .orElse(null);
    }

    /**
     * Find the lowest relative {@link Block}.
     *
     * @return {@link Block} with lowest Y-axis relative coordinate.
     */
    public Block getLowestBlock() {
        return blocks.stream()
                .min(Comparator.comparing(block -> block.getRelativePosition().getY()))
                .orElse(null);
    }

    /**
     * Get {@link Block Blocks} of current Piece.
     *
     * @return {@link Block Blocks} current Piece made of
     */
    public List<Block> getBlocks() {
        return blocks;
    }

    /// FALLING ///

    /**
     * Check if block is falling.
     *
     * @return {@code true} if it's falling, otherwise {@code false}
     */
    public boolean isFalling() {
        return falling;
    }

    /**
     * Set if block is falling.
     *
     * @param falling falling state
     */
    public void setFalling(boolean falling) {
        this.falling = falling;
    }

}
