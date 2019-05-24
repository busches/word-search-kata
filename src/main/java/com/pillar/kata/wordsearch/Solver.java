package com.pillar.kata.wordsearch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solver {
    public List<String> solve(WordSearch wordSearch) {

        // This should likely return a List<String, List<Coordinate>> but keeping it simple for now
        List<String> foundWords = new ArrayList<>();
        var grid = wordSearch.getGrid();
        foundWords.addAll(searchWordsHorizontally(wordSearch, grid));
        foundWords.addAll(searchWordsVertically(wordSearch, grid));

        return foundWords;
    }

    private List<String> searchWordsHorizontally(WordSearch wordSearch, List<String> grid) {
        List<String> foundWords = new ArrayList<>();
        for (var wordToFind : wordSearch.getWordsToSearch()) {
            for (int lineNumber = 0; lineNumber < grid.size(); lineNumber++) {
                var line = grid.get(lineNumber);
                var formattedLine = line.replace(",", "");
                if (formattedLine.contains(wordToFind)) {
                    final var y = lineNumber;
                    var coordinates = IntStream.range(formattedLine.indexOf(wordToFind), wordToFind.length())
                            .mapToObj(x -> String.format("(%s,%s)", x, y))
                            .collect(Collectors.joining(","));
                    foundWords.add(String.format("%s: %s", wordToFind, coordinates));
                    break;
                }
            }
        }
        return foundWords;
    }

    private List<String> searchWordsVertically(WordSearch wordSearch, List<String> grid) {
        List<String> foundWords = new ArrayList<>();
        for (var wordToFind : wordSearch.getWordsToSearch()) {
            for (int y = 0; y < grid.size(); y++) {
                // Stop before we attempt to search out of bounds
                if (y + wordToFind.length() > grid.size()) {
                    break;
                }
                for (int x = 0; x < grid.size(); x++) {
                    var foundWord = true;
                    var coordinates = "";
                    for (int letterPosition = 0; letterPosition < wordToFind.length(); letterPosition++) {
                        if (grid.get(y + letterPosition).split(",")[x].equals(wordToFind.substring(letterPosition, letterPosition + 1))) {
                            coordinates += String.format("(%s,%s)", x, y + letterPosition);
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
        return foundWords;
    }
}
