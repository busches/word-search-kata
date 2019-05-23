package com.pillar.kata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solver {
    public List<String> solve(WordSearch wordSearch) {

        // This should likely return a List<String, List<Coordinate>> but keeping it simple for now
        List<String> foundWords = new ArrayList<>();
        var grid = wordSearch.getGrid();

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
}
