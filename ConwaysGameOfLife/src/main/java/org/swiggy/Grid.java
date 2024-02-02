package org.swiggy;

import static org.swiggy.Cell.DEAD_CELL;
import static org.swiggy.Cell.ALIVE_CELL;

public class Grid {

    private final int rows;

    private final int columns;

    private final Cell[][] cells;

    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.cells = initalizeGridWithDeadCells(rows, columns);
    }

    public Grid(Cell[][] cells) {
        if (cells == null || cells.length == 0 || cells[0].length == 0) {
            throw new IllegalArgumentException("Invalid initialCells");
        }
        this.rows = cells.length;
        this.columns = cells[0].length;
        this.cells = new Cell[rows][columns];
        for (int row = 0; row < rows; row++) {
            System.arraycopy(cells[row], 0, this.cells[row], 0, columns);
        }
    }

    private Cell[][] initalizeGridWithDeadCells(int rows, int columns) {
        Cell[][] cells = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = DEAD_CELL;
            }
        }
        return cells;
    }

    public int getAliveNeighboursAt(int x, int y) {
        int aliveNeighbourCount = 0;
        for (int dx = x - 1; dx <= x + 1; dx++) {
            for (int dy = y - 1; dy <= y + 1; dy++) {
                if (!isCellItself(dx, dy, x, y)) {
                    aliveNeighbourCount += isAliveInCell(dx, dy);
                }
            }
        }
        return aliveNeighbourCount;
    }

    private boolean isCellItself(int dx, int dy, int x, int y) {
        return (dx == x) && (dy == y);
    }

    private int isAliveInCell(int x, int y) {
        if (isValidCell(x, y)) {
            return 0;
        }
        if (cells[x][y] == ALIVE_CELL) {
            return 1;
        } else {
            return 0;
        }
    }

    private boolean isValidCell(int x, int y) {
        return (x < 0 || x > getRows() - 1) || (y < 0 || y > getColumns() - 1);
    }

    public int getRows() {
        return cells.length;
    }

    public int getColumns() {
        return cells[0].length;
    }

    public Cell getCellAt(int x, int y) {
        return cells[x][y];
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int row = 0; row < getRows(); row++) {
            for (int column = 0; column < getColumns(); column++) {
                result.append(cells[row][column].getState());
            }
            result.append(System.lineSeparator());
        }

        return result.toString();
    }

    public Cell[][] getCells() {
        Cell[][] contentCopy = new Cell[getRows()][getColumns()];
        for (int row = 0; row < getRows(); row++) {
            if (getColumns() >= 0) System.arraycopy(cells[row], 0, contentCopy[row], 0, getColumns());
        }
        return contentCopy;
    }

    public void setCell(Cell cell, int x, int y) {
        cells[x][y] = cell;
    }
}