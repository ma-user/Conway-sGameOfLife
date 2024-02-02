package org.swiggy;

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
