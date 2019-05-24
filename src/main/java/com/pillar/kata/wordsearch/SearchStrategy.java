package com.pillar.kata.wordsearch;

public enum SearchStrategy {
    HORIZONTAL(1, 0),
    VERTICAL(0, 1);

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
