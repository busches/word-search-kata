package com.pillar.kata.wordsearch;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.function.Predicate.not;

public class Solver {
    public List<String> solve(WordSearch wordSearch) {

        // This should likely return a Map<String, List<Coordinate>> but keeping it simple for now
        return searchWords(wordSearch, wordSearch.getGrid());
    }

    private List<String> searchWords(WordSearch wordSearch, List<String> grid) {
        return Arrays.stream(wordSearch.getWordsToSearch())
                .map(wordToFind -> searchByStrategy(wordToFind, grid))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<String> searchByStrategy(String wordToFind, List<String> grid) {
        return Arrays.stream(SearchStrategy.values())
                .map(searchStrategy -> searchGrid(grid, wordToFind, searchStrategy))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }


    private Optional<String> searchGrid(List<String> grid, String wordToFind, SearchStrategy searchStrategy) {
        return IntStream.range(0, grid.size())
                .filter(x -> x + wordToFind.length() * searchStrategy.xIncrement() <= grid.size())
                .filter(x -> x + wordToFind.length() * searchStrategy.xIncrement() + 1 >= 0)
                .mapToObj(x -> loopX(grid, wordToFind, searchStrategy, x))
                .filter(not(String::isBlank))
                .findFirst();
    }

    private String loopX(List<String> grid, String wordToFind, SearchStrategy searchStrategy, int x) {
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
                return String.format("%s: %s", wordToFind, coordinates);
            }
        }
        return "";
    }
}
