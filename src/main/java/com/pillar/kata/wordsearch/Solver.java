package com.pillar.kata.wordsearch;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    public List<String> solve(WordSearch wordSearch) {

        // This should likely return a Map<String, List<Coordinate>> but keeping it simple for now
        return searchWords(wordSearch, wordSearch.getGrid());
    }

    private List<String> searchWords(WordSearch wordSearch, List<String> grid) {
        List<String> foundWords = new ArrayList<>();
        for (var wordToFind : wordSearch.getWordsToSearch()) {
            for (var searchStrategy : SearchStrategy.values()) {
                for (int x = 0; x < grid.size(); x++) {
                    // Validate X out of bounds
                    if (x + wordToFind.length() * searchStrategy.xIncrement() > grid.size() || x + wordToFind.length() * searchStrategy.xIncrement() + 1 < 0) {
                        continue;
                    }
                    for (int y = 0; y < grid.size(); y++) {
                        // Validate Y out of bounds
                        if (y + wordToFind.length() * searchStrategy.yIncrement() > grid.size() || y + wordToFind.length() * searchStrategy.yIncrement() + 1 < 0) {
                            continue;
                        }
                        var foundWord = true;
                        var coordinates = "";
                        for (int letterPosition = 0; letterPosition < wordToFind.length(); letterPosition++) {
                            if (grid.get(y + letterPosition * searchStrategy.yIncrement()).split(",")[x + letterPosition * searchStrategy.xIncrement()].equals(wordToFind.substring(letterPosition, letterPosition + 1))) {
                                coordinates += String.format("(%s,%s)", x + letterPosition * searchStrategy.xIncrement(), y + letterPosition * searchStrategy.yIncrement());
                                if (letterPosition != wordToFind.length() - 1) {
                                    coordinates += ",";
                                }
                            } else {
                                foundWord = false;
                                break;
                            }
                        }
                        if (foundWord) {
                            foundWords.add(String.format("%s: %s", wordToFind, coordinates));
                        }
                    }
                }
            }
        }
        return foundWords;
    }
}
