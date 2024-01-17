package xyz.ipiepiepie.task_13;

import xyz.ipiepiepie.task_13.object.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Game {
    private ScheduledFuture<?> task;
    private Renderer renderer;
    // board size //
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 22;
    // placed blocks //
    private final List<Block> placedBlocks = new ArrayList<>();
    // current piece //
    private Piece currentPiece;
    // score //
    private int score = 0;

    /// GAME LOGIC ///

    /**
     * Start tetris game.
     */
    public void start(Renderer renderer) {
        // setup renderer
        this.renderer = renderer;
        // get random piece for current
        this.currentPiece = new Piece(Shape.random());

        // schedule game update loop
        task = Executors
                .newSingleThreadScheduledExecutor()
                .scheduleAtFixedRate(this::update, 0, 500, TimeUnit.MILLISECONDS);
    }

    /**
     * Tetris game loop.
     */
    public void update() {

        // generate new piece if current is placed
        if (!currentPiece.move(0, -1)) {
            // place all piece blocks
            placedBlocks.addAll(currentPiece.getBlocks());

            // check for fulled lines and remove them
            score += removeFullLines();

            // stop game if we reached top
            if (currentPiece.getHighestBlock().getCurrentPosition().getY() >= 0) {
                stop();
            } else {
                // generate new piece
                this.currentPiece = new Piece(Shape.random());
            }
        }

        // render updates
        renderer.render();
    }

    /**
     * Remove full {@link Block} lines.
     *
     * @return count of removed lines.
     */
    public int removeFullLines() {
        int count = 0;

        // iterate over lines, starting from down
        for (int y = 0; y >= -20; y--) {
            // get row at current level
            List<Block> line = getBlocksAtLevel(y);

            // if line is full of blocks
            if (line.size() >= BOARD_WIDTH) {
                // move upper blocks down
                for (int i = y + 1; i < 0; i++) {
                    getBlocksAtLevel(i).forEach(block -> block.move(0, -1));
                }

                // remove current line
                placedBlocks.removeAll(line);
                // increase removed rows count
                count++;
            }
        }

        return count;
    }

    public void stop() {
        task.cancel(true);
    }

    /// CURRENT PIECE ///

    /**
     * Get current {@link Piece}.
     *
     * @return current {@link Piece}
     */
    public Piece getCurrentPiece() {
        return currentPiece;
    }

    /// PLACED BLOCKS ///

    /**
     * Check if there is any {@link Block} in selected {@link Coordinate}.
     * @param coordinate {@link Coordinate} to check
     * @return {@code true} if there is any {@link Block} in selected {@link Coordinate}, otherwise {@code false}
     */
    public boolean hasPlacedBlockAt(Coordinate coordinate) {
        return placedBlocks.stream().anyMatch(block -> block.getCurrentPosition().equals(coordinate));
    }

    /**
     * Get {@link Block Blocks} at selected height.
     *
     * @param y Y-axis coordinate (height)
     * @return {@link List} of {@link Block Blocks}
     */
    public List<Block> getBlocksAtLevel(int y) {
        return placedBlocks.stream().filter(block -> block.getCurrentPosition().getY() == y).toList();
    }

    /**
     * Get placed {@link Block Blocks}.
     *
     * @return {@link List} of placed {@link Block Blocks}.
     */
    public List<Block> getPlacedBlocks() {
        return placedBlocks;
    }

    /// COORDINATE ///

    public boolean validate(Coordinate coordinate) {
        return coordinate.getY() >= 2 - BOARD_HEIGHT
                && coordinate.getX() >= 0
                && coordinate.getX() < BOARD_WIDTH
                && !hasPlacedBlockAt(coordinate);
    }

    /// SCORE ///

    /**
     * Get current game score.
     *
     * @return score points
     */
    public int getScore() {
        return score;
    }
}
