package org.swiggy;

/**
 * The Cell enum represents the possible states of a cell in the Game of Life.
 * It defines two states:
 * 1. ALIVE_CELL (a living cell in the Game of Life)
 * 2. DEAD_CELL (a dead cell in the Game of Life)
 */
public enum Cell {
    ALIVE_CELL("- "),
    DEAD_CELL("* ");

    private final String state;

    private Cell(final String initialState) {
        this.state = initialState;
    }

    public String getState() {
        return state;
    }
}
