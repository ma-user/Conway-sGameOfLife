package org.swiggy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameSpaceTest {

    private final GameSpace gameSpace = new GameSpace(3,3);

    @Test
    public void testEvolveGrid() {
        gameSpace.setCellState(Cell.ALIVE_CELL, 0, 1);
        gameSpace.setCellState(Cell.ALIVE_CELL, 1, 1);
        gameSpace.setCellState(Cell.ALIVE_CELL, 2, 1);

        gameSpace.evolveGrid();

        assertSame(gameSpace.getCells()[0][1], Cell.DEAD_CELL);
        assertSame(gameSpace.getCells()[1][1], Cell.ALIVE_CELL);
        assertSame(gameSpace.getCells()[2][1], Cell.DEAD_CELL);
    }

    @Test
    public void testSetCellStateToALive_success() {
        gameSpace.setCellState(Cell.ALIVE_CELL, 1, 1);

        assertEquals(Cell.ALIVE_CELL, gameSpace.getCells()[1][1]);
    }

    @Test
    public void testSetCellStateOutOfBounds_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> gameSpace.setCellState(Cell.ALIVE_CELL, 3, 3));
    }

    @Test
    public void testAreAllCellsDeadInitially() {
        assertTrue(gameSpace.areAllCellsDead());
    }

    @Test
    public void testAreAllCellsDeadAfterEvolution() {
        gameSpace.setCellState(Cell.ALIVE_CELL, 0, 1);
        gameSpace.setCellState(Cell.ALIVE_CELL, 1, 1);
        gameSpace.setCellState(Cell.ALIVE_CELL, 2, 1);

        gameSpace.evolveGrid();

        assertFalse(gameSpace.areAllCellsDead());
    }
}
