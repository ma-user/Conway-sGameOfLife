package org.swiggy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Manages the game logic, including starting and stopping the game loop.
 * Coordinates interactions between the user and the game grid.
 */
public class Game {

    private final GameSpace gameSpace;
    private static final int TIME_DELAY = 1000;
    static boolean isRunning;

    public Game(final GameSpace gameSpace) {
        this.gameSpace = gameSpace;
        isRunning = false;
    }

    /**
     * Starts the game loop asynchronously. The game will evolve continuously until stopped or all cells are dead.
     */
    public void start() {
        isRunning = true;

        new Thread(() -> {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while (isRunning) {
                try {
                    // Check if Enter is pressed
                    if (reader.ready() && reader.readLine().isEmpty()) {
                        stop();
                        System.out.println("Game over!");
                        break;
                    }
                } catch (IOException e) {
                    handleIOException(e);
                }

                gameSpace.evolveGrid();
                System.out.println(gameSpace);

                if (gameSpace.areAllCellsDead()) {
                    stop();
                    System.out.println("All cells are dead. Game over!");
                    break;
                }

                try {
                    Thread.sleep(TIME_DELAY);
                } catch (InterruptedException e) {
                    stop();
                    Thread.currentThread().interrupt();
                    System.err.println("Current thread interrupted: " + e.getMessage());
                    throw new RuntimeException("Current thread interrupted", e);
                }
            }

            try {
                reader.close();
            } catch (IOException e) {
                handleIOException(e);
            }
        }).start();
    }

    /**
     * Stops the game loop, allowing the game to exit.
     */
    private void stop() {
        isRunning = false;
    }

    private void handleIOException(IOException e) {
        System.err.println("An unexpected I/O error occurred: " + e.getMessage());
        throw new RuntimeException("Failed to perform I/O operation", e);
    }
}
