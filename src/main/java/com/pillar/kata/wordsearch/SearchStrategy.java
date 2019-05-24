package com.pillar.kata.wordsearch;

public enum SearchStrategy {
    HORIZONTAL(1, 0),
    HORIZONTAL_REVERSE(-1, 0),
    VERTICAL(0, 1),
    VERTICAL_REVERSE(0, -1),
    DIAGONAL_DESCENDING(1, 1),
    DIAGONAL_DESCENDING_REVERSE(-1, 1),
    DIAGONAL_ASCENDING(1, -1),
    ;

    private int xIncrement;
    private int yIncrement;

    SearchStrategy(int xIncrement, int yIncrement) {
        this.xIncrement = xIncrement;
        this.yIncrement = yIncrement;
    }

    public int xIncrement() {
        return xIncrement;
    }

    public int yIncrement() {
        return yIncrement;
    }
}
