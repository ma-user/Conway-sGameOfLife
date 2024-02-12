package org.swiggy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import static org.swiggy.Cell.ALIVE_CELL;


public class GameSetup {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = UserInput.readInteger("Enter the number of rows: ");
        int columns = UserInput.readInteger("Enter the number of columns: ");
        int seedingPercentage = UserInput.readInteger("Enter the seeding percentage (0-100): ");

        GameSpace gameSpace = new GameSpace(rows, columns);
        seedGameSpace(gameSpace, rows, columns, seedingPercentage);

        Game game = new Game(gameSpace);

        System.out.println("Press Enter to start and stop the game.");
        scanner.nextLine();

        try {
            game.start();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
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