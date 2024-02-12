package org.swiggy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GridTest {

    @Test
    void testInvalidGridCreation_whenNumberOfRowsIsNegative_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Grid(-1, 0));
    }

    @Test
    void testInvalidGridCreation_whenNumberOfColumnsIsNegative_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Grid(0, -1));
    }

    @Test
    public void testGridInitializationWithDeadCells_successfullyInitialized() {
        Grid grid = new Grid(3, 3);

        Cell[][] cells = grid.getCells();

        assertEquals(3, grid.getRows());
        assertEquals(3, grid.getColumns());

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
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
    public void testCountAliveNeighborsForCell_success() {
        Cell[][] initialCells = {
                {Cell.ALIVE_CELL, Cell.ALIVE_CELL, Cell.DEAD_CELL},
                {Cell.ALIVE_CELL, Cell.DEAD_CELL, Cell.DEAD_CELL},
                {Cell.DEAD_CELL, Cell.DEAD_CELL, Cell.DEAD_CELL}
        };

        Grid grid = new Grid(initialCells);

        int liveNeighbours = grid.countAliveNeighborsForCell(0, 0);

        assertEquals(2, liveNeighbours);
    }

    @Test
    public void testSetCellToAlive_success() {
        Grid grid = new Grid(3, 3);

        Cell[][] cellsBefore = grid.getCells();

        grid.setCell(Cell.ALIVE_CELL, 1, 1);

        Cell[][] cellsAfter = grid.getCells();

        assertNotEquals(cellsBefore[1][1], cellsAfter[1][1]);
        assertEquals(Cell.ALIVE_CELL, cellsAfter[1][1]);
    }
}