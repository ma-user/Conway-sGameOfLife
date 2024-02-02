package org.swiggy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameSpaceTest {

    @Test
    public void testEvolveGrid() {
        int rows = 3;
        int columns = 3;
        GameSpace gameSpace = new GameSpace(rows, columns);
        gameSpace.setCellState(Cell.ALIVE_CELL, 0, 1);
        gameSpace.setCellState(Cell.ALIVE_CELL, 1, 1);
        gameSpace.setCellState(Cell.ALIVE_CELL, 2, 1);

        gameSpace.evolveGrid();

        assertTrue(gameSpace.getCells()[0][1] == Cell.DEAD_CELL);
        assertTrue(gameSpace.getCells()[1][1] == Cell.ALIVE_CELL);
        assertTrue(gameSpace.getCells()[2][1] == Cell.DEAD_CELL);
    }

    @Test
    public void testSetCellStateToALive_success() {
        int rows = 3;
        int columns = 3;
        GameSpace gameSpace = new GameSpace(rows, columns);

        gameSpace.setCellState(Cell.ALIVE_CELL, 1, 1);

        assertEquals(Cell.ALIVE_CELL, gameSpace.getCells()[1][1]);
    }

    @Test
    public void testSetCellStateOutOfBounds_throwsIllegalArgumentException() {
        int rows = 3;
        int columns = 3;
        GameSpace gameSpace = new GameSpace(rows, columns);

        assertThrows(IllegalArgumentException.class, () -> gameSpace.setCellState(Cell.ALIVE_CELL, 3, 3));
    }

    @Test
    public void testAreAllCellsDeadInitially() {
        int rows = 3;
        int columns = 3;
        GameSpace gameSpace = new GameSpace(rows, columns);

        assertTrue(gameSpace.areAllCellsDead());
    }

    @Test
    public void testAreAllCellsDeadAfterEvolution() {
        int rows = 3;
        int columns = 3;
        GameSpace gameSpace = new GameSpace(rows, columns);
        gameSpace.setCellState(Cell.ALIVE_CELL, 0, 1);
        gameSpace.setCellState(Cell.ALIVE_CELL, 1, 1);
        gameSpace.setCellState(Cell.ALIVE_CELL, 2, 1);

        gameSpace.evolveGrid();

        assertFalse(gameSpace.areAllCellsDead());
    }
}