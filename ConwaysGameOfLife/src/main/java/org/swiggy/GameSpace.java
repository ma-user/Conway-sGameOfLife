package org.swiggy;

import static org.swiggy.Cell.DEAD_CELL;
import static org.swiggy.Cell.ALIVE_CELL;

public class GameSpace {

    private Grid currentGrid;

    public GameSpace(int rows, int columns) {
        currentGrid = new Grid(rows, columns);
    }

    public void evolveGrid() {
        int rows = currentGrid.getRows();
        int columns = currentGrid.getColumns();
        Cell[][] nextGenerationGrid = new Cell[rows][columns];

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                Cell currentCell = currentGrid.getCellAt(x, y);
                int aliveNeighbourCount = currentGrid.getAliveNeighboursAt(x, y);
                Cell nextCell = evolve(currentCell, aliveNeighbourCount);
                nextGenerationGrid[x][y] = nextCell;
            }
        }

        currentGrid = new Grid(nextGenerationGrid);
    }

    private Cell evolve(Cell currentCell, int aliveNeighbourCount) {
        Cell nextCell = null;
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

    public Cell[][] getCells() {
        return currentGrid.getCells();
    }

    public void setCellState(Cell cell, int row, int column) {
        if(row < 0 || row >= currentGrid.getRows() || column < 0 || column >= currentGrid.getColumns()) {
            throw new IllegalArgumentException("Invalid cell coordinates");
        }
        currentGrid.setCell(cell, row, column);
    }

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

    @Override
    public String toString() {
        return currentGrid.toString();
    }
}


