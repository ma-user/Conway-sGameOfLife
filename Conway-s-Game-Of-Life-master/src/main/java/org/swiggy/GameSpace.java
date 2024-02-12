package org.swiggy;

import static org.swiggy.Cell.ALIVE_CELL;
import static org.swiggy.Cell.DEAD_CELL;

/**
 * The GameSpace class represents the game space in the Game of Life.
 * It contains a grid of cells and provides methods for evolving the grid and checking the state of cells.
 */
public class GameSpace {

    /**
     * The current grid representing the state of the game space.
     */
    private Grid currentGrid;

    public GameSpace(final int rows, final int columns) {
        currentGrid = new Grid(rows, columns);
    }

    /**
     * Evolves the current grid to the next generation based on the rules of the Game of Life.
     * Updates the internal state of the game space.
     */
    public void evolveGrid() {
        int rows = currentGrid.getRows();
        int columns = currentGrid.getColumns();
        Cell[][] nextGenerationGrid = new Cell[rows][columns];

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                Cell currentCell = currentGrid.getCellAt(row, column);
                int aliveNeighbourCount = currentGrid.countAliveNeighborsForCell(row, column);
                Cell nextCell = evolve(currentCell, aliveNeighbourCount);
                nextGenerationGrid[row][column] = nextCell;
            }
        }

        currentGrid = new Grid(nextGenerationGrid);
    }

    private Cell evolve(final Cell currentCell, final int aliveNeighbourCount) {
        Cell nextCell;
        if (currentCell == ALIVE_CELL) {
            if ((aliveNeighbourCount == 2) || (aliveNeighbourCount == 3)) {
                nextCell = ALIVE_CELL;
            } else {
                nextCell = DEAD_CELL;
            }
        } else {
            if (aliveNeighbourCount == 3) {
                nextCell = ALIVE_CELL;
            } else {
                nextCell = DEAD_CELL;
            }
        }
        return nextCell;
    }

    /**
     * Determines whether all cells in the game space are dead.
     *
     * @return true if all cells are dead, false otherwise.
     */
    public boolean areAllCellsDead() {
        Cell[][] cells = currentGrid.getCells();

        for (int row = 0; row < currentGrid.getRows(); row++) {
            for (int column = 0; column < currentGrid.getColumns(); column++) {
                if (cells[row][column] == Cell.ALIVE_CELL) {
                    return false;
                }
            }
        }

        return true;
    }

    public Cell[][] getCells() {
        return currentGrid.getCells();
    }

    public void setCellState(final Cell cell, final int row, final int column) {
        if(row < 0 || row >= currentGrid.getRows() || column < 0 || column >= currentGrid.getColumns()) {
            throw new IllegalArgumentException("Invalid cell coordinates");
        }
        currentGrid.setCell(cell, row, column);
    }

    @Override
    public String toString() {
        return currentGrid.toString();
    }
}
