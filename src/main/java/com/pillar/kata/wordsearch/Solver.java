package com.pillar.kata.wordsearch;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
                .filter(x -> x + wordToFind.length() * searchStrategy.xIncrement() >= -1)
                .mapToObj(x -> loopX(grid, wordToFind, searchStrategy, x))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    private Optional<String> loopX(List<String> grid, String wordToFind, SearchStrategy searchStrategy, int x) {
        return IntStream.range(0, grid.size())
                .filter(y -> y + wordToFind.length() * searchStrategy.yIncrement() <= grid.size())
                .filter(y -> y + wordToFind.length() * searchStrategy.yIncrement() >= -1)
                .mapToObj(y -> loopY(grid, wordToFind, searchStrategy, x, y))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    private Optional<String> loopY(List<String> grid, String wordToFind, SearchStrategy searchStrategy, int x, int y) {
        var wordFound = IntStream.range(0, wordToFind.length())
                .allMatch(letterPosition ->
                        grid.get(y + letterPosition * searchStrategy.yIncrement()).split(",")[x + letterPosition * searchStrategy.xIncrement()]
                                .equals(wordToFind.substring(letterPosition, letterPosition + 1)));

        return wordFound ? Optional.of(wordToFind + ": " + IntStream.range(0, wordToFind.length())
                .mapToObj(letterPosition ->
                        String.format("(%s,%s)", x + letterPosition * searchStrategy.xIncrement(), y + letterPosition * searchStrategy.yIncrement()))
                .collect(Collectors.joining(",")))
                : Optional.empty();
    }
}
