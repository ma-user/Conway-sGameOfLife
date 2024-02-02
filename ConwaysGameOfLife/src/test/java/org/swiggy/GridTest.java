package org.swiggy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    public void testGridInitializationWithDeadCells_successfullyInitialized() {
        int rows = 3;
        int columns = 3;
        Grid grid = new Grid(rows, columns);

        Cell[][] cells = grid.getCells();

        assertEquals(rows, grid.getRows());
        assertEquals(columns, grid.getColumns());

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                assertEquals(Cell.DEAD_CELL, cells[i][j]);
            }
        }
    }

    @Test
    public void testGridInitializationWithCustomCells_successfullyInitialized() {
        Cell[][] initialCells = {
                {Cell.ALIVE_CELL, Cell.DEAD_CELL, Cell.ALIVE_CELL},
                {Cell.DEAD_CELL, Cell.DEAD_CELL, Cell.ALIVE_CELL},
                {Cell.ALIVE_CELL, Cell.DEAD_CELL, Cell.DEAD_CELL}
        };

        Grid grid = new Grid(initialCells);

        Cell[][] cells = grid.getCells();

        assertEquals(initialCells.length, grid.getRows());
        assertEquals(initialCells[0].length, grid.getColumns());

        for (int i = 0; i < initialCells.length; i++) {
            for (int j = 0; j < initialCells[0].length; j++) {
                assertEquals(initialCells[i][j], cells[i][j]);
            }
        }
    }

    @Test
    public void testGridInitialization_whenNullCells_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Grid(null));
    }

    @Test
    public void testGridInitialization_whenEmptyCells_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Grid(new Cell[0][0]));
    }

    @Test
    public void testGetLiveNeighboursAtACell_success() {
        Cell[][] initialCells = {
                {Cell.ALIVE_CELL, Cell.ALIVE_CELL, Cell.DEAD_CELL},
                {Cell.ALIVE_CELL, Cell.DEAD_CELL, Cell.DEAD_CELL},
                {Cell.DEAD_CELL, Cell.DEAD_CELL, Cell.DEAD_CELL}
        };

        Grid grid = new Grid(initialCells);

        int liveNeighbours = grid.getAliveNeighboursAt(0, 0);

        assertEquals(2, liveNeighbours);
    }

    @Test
    public void testSetCellToAlive_success() {
        int rows = 3;
        int columns = 3;
        Grid grid = new Grid(rows, columns);

        Cell[][] cellsBefore = grid.getCells();

        grid.setCell(Cell.ALIVE_CELL, 1, 1);

        Cell[][] cellsAfter = grid.getCells();

        assertNotEquals(cellsBefore[1][1], cellsAfter[1][1]);
        assertEquals(Cell.ALIVE_CELL, cellsAfter[1][1]);
    }
}