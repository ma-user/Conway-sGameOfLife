package org.swiggy;

import java.util.*;

import static org.swiggy.Cell.ALIVE_CELL;

public class GameOfLifeCLI {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the number of columns: ");
        int columns = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the seeding percentage (0-100): ");
        int seedingPercentage = scanner.nextInt();
        scanner.nextLine();

        GameSpace gameSpace = new GameSpace(rows, columns);
        seedGameSpace(gameSpace, rows, columns, seedingPercentage);

        GameController gameController = new GameController(gameSpace);

        System.out.println("Press Enter to start and stop the game.");
        scanner.nextLine();

        gameController.startGame();

        while (true) {
            if (gameSpace.areAllCellsDead()) {
                gameController.stopGame();
                System.out.println("All cells are dead. Game over!");
                break;
            }

            String input = scanner.nextLine();
            if (input.isEmpty()) {
                gameController.stopGame();
                System.out.println("Game over!");
                break;
            }
        }
    }

    private static void seedGameSpace(GameSpace gameSpace, int rows, int columns, int seedingPercentage) {
        int totalCells = rows * columns;
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < totalCells; i++) {
            indices.add(i);
        }

        Collections.shuffle(indices);

        int liveCellsToSeed = (int) (totalCells * (seedingPercentage / 100.0));

        for (int i = 0; i < liveCellsToSeed; i++) {
            int index = indices.get(i);
            int randomRow = index / columns;
            int randomColumn = index % columns;
            gameSpace.setCellState(ALIVE_CELL, randomRow, randomColumn);
        }
    }
}