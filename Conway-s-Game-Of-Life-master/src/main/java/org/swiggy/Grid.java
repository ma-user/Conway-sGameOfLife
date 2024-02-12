package org.swiggy;

import static org.swiggy.Cell.ALIVE_CELL;
import static org.swiggy.Cell.DEAD_CELL;


/**
 * The Grid class represents a 2D grid of cells for Conway's Game of Life.
 * It provides methods to initialize the grid, access and manipulate the state of cells.
 */
public class Grid {

    private final int rows;
    private final int columns;

    /**
     * Two-dimensional array representing the cells in the grid.
     */
    private final Cell[][] cells;

    /**
     * Constructs a new grid with the specified number of rows and columns,
     * initializing all cells to be dead.
     *
     * @param rows    The number of rows in the grid.
     * @param columns The number of columns in the grid.
     */
    public Grid(final int rows, final int columns) {
        validateGrid(rows, columns);
        this.rows = rows;
        this.columns = columns;
        this.cells = initalizeGridWithDeadCells(rows, columns);
    }

    /**
     * Constructs a new grid with the specified initial state of cells.
     *
     * @param cells The initial state of cells in the grid.
     * @throws IllegalArgumentException if the initialCells array is null or empty.
     */
    public Grid(final Cell[][] cells) {
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

    private static void validateGrid(final int rows, final int columns) throws IllegalArgumentException {
        if (rows < 0 || columns < 0) {
            throw new IllegalArgumentException("Grid dimensions cannot be negative");
        }
    }

    /**
     * Initializes the grid with dead cells.
     *
     * @param rows    The number of rows in the grid.
     * @param columns The number of columns in the grid.
     * @return A 2D array representing the grid with all cells dead.
     */
    private Cell[][] initalizeGridWithDeadCells(final int rows, final int columns) {
        Cell[][] cells = new Cell[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                cells[i][j] = DEAD_CELL;
            }
        }
        return cells;
    }

    /**
     * Gets the count of alive neighbors surrounding the specified cell.
     *
     * @param targetRow The row index of the target cell.
     * @param targetColumn The column index of the target cell.
     * @return The number of live neighbors around the specified cell.
     */
    public int countAliveNeighborsForCell(final int targetRow, final int targetColumn) {
        int aliveNeighbourCount = 0;

        for (int row = targetRow - 1; row <= targetRow + 1; row++) {
            for (int column = targetColumn - 1; column <= targetColumn + 1; column++) {
                if (!isCellItself(row, column, targetRow, targetColumn)) {
                    aliveNeighbourCount += isAliveInCell(row, column);
                }
            }
        }

        return aliveNeighbourCount;
    }

    /**
     * Checks if the specified cell is the target cell itself.
     *
     * @return true if the coordinates represent the same cell, false otherwise.
     */
    private boolean isCellItself(final int row, final int column, final int targetRow, final int targetColumn) {
        return (row == targetRow) && (column == targetColumn);
    }

    /**
     * Checks if the cell at the specified index is alive.
     *
     * @param row The row index of the cell.
     * @param column The column index of the cell.
     * @return 1 if the cell is alive, 0 if the cell is dead or the coordinates are out of bounds.
     */
    private int isAliveInCell(final int row, final int column) {
        if (isValidCell(row, column)) {
            return 0;
        }
        if (cells[row][column] == ALIVE_CELL) {
            return 1;
        } else {
            return 0;
        }
    }

    private boolean isValidCell(final int row, final int column) {
        return (row < 0 || row > getRows() - 1) || (column < 0 || column > getColumns() - 1);
    }


    public int getRows() {
        return cells.length;
    }

    public int getColumns() {
        return cells[0].length;
    }

    public Cell[][] getCells() {
        Cell[][] contentCopy = new Cell[getRows()][getColumns()];
        for (int row = 0; row < getRows(); row++) {
            if (getColumns() >= 0) System.arraycopy(cells[row], 0, contentCopy[row], 0, getColumns());
        }
        return contentCopy;
    }

    public Cell getCellAt(final int row, final int column) {
        return cells[row][column];
    }

    /**
     * Sets the state of the cell at the specified index.
     *
     * @param cell The new state of the cell.
     * @param row    The row index of the cell.
     * @param column    The column index of the cell.
     */
    public void setCell(final Cell cell, final int row, final int column) {
        cells[row][column] = cell;
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
}
