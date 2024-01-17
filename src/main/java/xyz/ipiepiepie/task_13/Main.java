package xyz.ipiepiepie.task_13;

import xyz.ipiepiepie.task_13.gui.Menu;

public class Main {
    private static Game game;

    /**
     * Entry point.
     */
    public static void main(String[] args) {
        // create game instance
        game = new Game();

        // start game with GUI
        game.start(new Menu());
    }

    public static Game getGameInstance() {
        return game;
    }

}
