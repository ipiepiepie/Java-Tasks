package xyz.ipiepiepie.task_13.gui;

import xyz.ipiepiepie.task_13.Game;
import xyz.ipiepiepie.task_13.Main;
import xyz.ipiepiepie.task_13.object.Block;
import xyz.ipiepiepie.task_13.object.Coordinate;
import xyz.ipiepiepie.task_13.object.Piece;

import javax.swing.*;
import java.awt.*;

public class BoardPanel extends JPanel {
    // block size //
    // FIXME hardcoded params
    private final int BLOCK_WIDTH = 400 / Game.BOARD_WIDTH; // divide panel width by board width
    private final int BLOCK_HEIGHT = 814 / Game.BOARD_HEIGHT; // divide panel height by board height

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Game game = Main.getGameInstance();

        // render score
        g.setFont(new Font("Consolas", Font.PLAIN, 28));
        g.drawString(String.format("Score: %d", game.getScore()), 15, 35);

        // render current piece
        renderPiece(g, game.getCurrentPiece());

        // render placed blocks
        for (Block block : game.getPlacedBlocks())
            renderBlock(g, block);
    }

    private void renderPiece(Graphics graphics, Piece piece) {
        // render piece blocks
        for (Block block : piece.getBlocks())
            renderBlock(graphics, block);
    }

    private void renderBlock(Graphics graphics, Block block) {
        Coordinate position = block.getCurrentPosition();

        // set block color
        graphics.setColor(block.getColor());
        // render block
        graphics.fillRect(
                position.getX() * BLOCK_WIDTH,
                -position.getY() * BLOCK_HEIGHT,
                BLOCK_WIDTH,
                BLOCK_HEIGHT
        );
        // set border color
        graphics.setColor(block.getColor().darker());
        // draw border
        graphics.drawRect(
                position.getX() * BLOCK_WIDTH,
                -position.getY() * BLOCK_HEIGHT,
                BLOCK_WIDTH,
                BLOCK_HEIGHT
        );
    }
}
