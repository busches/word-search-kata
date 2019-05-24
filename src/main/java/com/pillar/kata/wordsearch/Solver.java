package com.pillar.kata.wordsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Solver {
    public List<String> solve(WordSearch wordSearch) {

        // This should likely return a Map<String, List<Coordinate>> but keeping it simple for now
        return searchWords(wordSearch, wordSearch.getGrid());
    }

    private List<String> searchWords(WordSearch wordSearch, List<String> grid) {
        List<String> foundWords = new ArrayList<>();
        for (var wordToFind : wordSearch.getWordsToSearch()) {
            for (var searchStrategy : SearchStrategy.values()) {
                searchGrid(grid, wordToFind, searchStrategy).ifPresent(foundWords::add);
            }
        }
        return foundWords;
    }

    private Optional<String> searchGrid(List<String> grid, String wordToFind, SearchStrategy searchStrategy) {
        for (var x = 0; x < grid.size(); x++) {
            // Skip this column if X will be out of bounds to find the entire word
            if (x + wordToFind.length() * searchStrategy.xIncrement() > grid.size() || x + wordToFind.length() * searchStrategy.xIncrement() + 1 < 0) {
                continue;
            }
            for (var y = 0; y < grid.size(); y++) {
                // Skip this row if Y will be out of bounds to find the entire word
                if (y + wordToFind.length() * searchStrategy.yIncrement() > grid.size() || y + wordToFind.length() * searchStrategy.yIncrement() + 1 < 0) {
                    continue;
                }
                var foundWord = true;
                var coordinates = "";
                for (var letterPosition = 0; letterPosition < wordToFind.length(); letterPosition++) {
                    var currentX = x + letterPosition * searchStrategy.xIncrement();
                    var currentY = y + letterPosition * searchStrategy.yIncrement();
                    if (grid.get(currentY).split(",")[currentX].equals(wordToFind.substring(letterPosition, letterPosition + 1))) {
                        coordinates += String.format("(%s,%s)", currentX, currentY);
                        if (letterPosition != wordToFind.length() - 1) {
                            coordinates += ",";
                        }
                    } else {
                        foundWord = false;
                        break;
                    }
                }
                if (foundWord) {
                    return Optional.of(String.format("%s: %s", wordToFind, coordinates));
                }
            }
        }
        return Optional.empty();
    }
}
