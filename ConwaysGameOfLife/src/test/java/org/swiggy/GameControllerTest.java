package org.swiggy;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameControllerTest {

    @Test
    void testStartGame() {
        GameSpace mockedGameSpace = mock(GameSpace.class);
        GameController gameController = new GameController(mockedGameSpace);

        assertFalse(GameController.isRunning);

        gameController.startGame();

        assertTrue(GameController.isRunning);
        verify(mockedGameSpace, atLeastOnce()).evolveGrid();
    }

    @Test
    void testStopGame() {
        GameSpace mockedGameSpace = mock(GameSpace.class);
        GameController gameController = new GameController(mockedGameSpace);

        assertFalse(GameController.isRunning);

        gameController.startGame();

        assertTrue(GameController.isRunning);

        gameController.stopGame();

        assertFalse(GameController.isRunning);
    }
}