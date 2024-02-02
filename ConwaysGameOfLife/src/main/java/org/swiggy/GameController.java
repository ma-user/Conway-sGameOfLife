package org.swiggy;

public class GameController {
    private final GameSpace gameSpace;
    private static final int TIME_DELAY = 1000;
    static boolean isRunning;

    public GameController(GameSpace gameSpace) {
        this.gameSpace = gameSpace;
        isRunning = false;
    }

    public void startGame() {
        isRunning = true;

        new Thread(() -> {
            while (isRunning) {
                gameSpace.evolveGrid();
                System.out.println(gameSpace.toString());

                try {
                    Thread.sleep(TIME_DELAY);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void stopGame() {
        isRunning = false;
    }
}
