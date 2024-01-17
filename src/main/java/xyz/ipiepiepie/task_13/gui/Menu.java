package xyz.ipiepiepie.task_13.gui;

import xyz.ipiepiepie.task_13.Game;
import xyz.ipiepiepie.task_13.Main;
import xyz.ipiepiepie.task_13.object.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Menu extends JFrame implements Renderer {
    private final JPanel gameBoard;

    public Menu() {
        setTitle("Task 13");
        // lock size of window
        setSize(400, 814);
        setResizable(false);

        // set grid layout
        setLayout(new GridLayout(1, 2));

        // create game board panel
        gameBoard = new BoardPanel();
        this.add(gameBoard);

        // register input
        registerInput();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /// INPUT ///

    private void registerInput() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                Game game = Main.getGameInstance();
                int keycode = e.getKeyCode();

                // TODO check for game over

                switch (keycode) {
                    case 'a':
                    case 'A':
                    case KeyEvent.VK_LEFT:
                        // try to move piece left
                        if (game.getCurrentPiece().move(-1, 0))
                            render(); // re-render board if moved
                        break;
                    case 'd':
                    case 'D':
                    case KeyEvent.VK_RIGHT:
                        // try to move piece right
                        if (game.getCurrentPiece().move(1, 0))
                            render(); // re-render board if moved
                        break;
                    case 'w':
                    case 'W':
                    case KeyEvent.VK_UP:
                        // try to rotate piece left
                        if (game.getCurrentPiece().rotateLeft())
                            render(); // re-render board if moved
                        break;
                    case 's':
                    case 'S':
                    case KeyEvent.VK_DOWN:
                        // try to move piece down
                        if (game.getCurrentPiece().move(0, -1))
                            render(); // re-render board if moved
                        break;
                    case KeyEvent.VK_SPACE:
                        //advanceToEnd();
                        break;
                }

            }
        });
    }

    /// RENDER ///

    /**
     * Render game panel
     */
    @Override
    public void render() {
        gameBoard.repaint();
    }



}
