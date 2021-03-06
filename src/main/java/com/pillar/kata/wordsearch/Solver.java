package com.pillar.kata.wordsearch;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Solver {
    public List<String> solve(WordSearch wordSearch) {

        // This should likely return a Map<String, List<Coordinate>> but keeping it simple for now
        return Arrays.stream(wordSearch.getWordsToSearch())
                .flatMap(wordToFind -> Arrays.stream(SearchStrategy.values())
                        .flatMap(searchStrategy -> searchGrid(wordSearch.getGrid(), wordToFind, searchStrategy))
                        .filter(Optional::isPresent)
                        .map(Optional::get))
                .collect(Collectors.toList());
    }

    private Stream<Optional<String>> searchGrid(List<String> grid, String wordToFind, SearchStrategy searchStrategy) {
        return IntStream.range(0, grid.size())
                .filter(x -> searchIsInsideGrid(grid, wordToFind, x, searchStrategy.xIncrement()))
                .mapToObj(x -> IntStream.range(0, grid.size())
                        .filter(y -> searchIsInsideGrid(grid, wordToFind, y, searchStrategy.yIncrement()))
                        .mapToObj(y -> searchAtCoordinates(grid, wordToFind, searchStrategy, x, y)))
                .flatMap(optionalStream -> optionalStream); // flatMapToObj doesn't exist, so we must make two calls
    }

    private Optional<String> searchAtCoordinates(List<String> grid, String wordToFind, SearchStrategy searchStrategy, int x, int y) {
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

    private boolean searchIsInsideGrid(List<String> grid, String wordToFind, int coordinate, int increment) {
        return coordinate + wordToFind.length() * increment <= grid.size() && coordinate + wordToFind.length() * increment >= -1;
    }
}
