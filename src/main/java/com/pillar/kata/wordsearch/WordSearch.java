package com.pillar.kata.wordsearch;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WordSearch {

    private final List<String> grid;
    private final String[] wordsToSearch;


    WordSearch(File rawWordSearch) throws InvalidFileFormatException, IOException {
        validateInput(rawWordSearch);
        var entireFile = Files.readAllLines(rawWordSearch.toPath());
        wordsToSearch = entireFile.get(0).split(",");
        entireFile.remove(0);
        grid = entireFile;
    }

    private void validateInput(File file) throws InvalidFileFormatException {
        if (!file.exists()) {
            throw new InvalidFileFormatException("Word Search File Not Found");
        }
        try {
            long fileLineCount = Files.lines(file.toPath()).count();
            if (fileLineCount < 3) {
                throw new InvalidFileFormatException("Word Search must contain at least three lines");
            } else {
                List<String> entireFile = Files.readAllLines(file.toPath());
                if (entireFile.stream()
                        .skip(1) // First row is words to search
                        .anyMatch(line -> line.split(",").length != fileLineCount - 1)) {
                    throw new InvalidFileFormatException("Word Search Grid must be a square");
                }
            }
        } catch (IOException e) {
            throw new InvalidFileFormatException(e);
        }
    }

    public List<String> getGrid() {
        return new ArrayList<>(grid);
    }

    public String[] getWordsToSearch() {
        return wordsToSearch.clone();
    }
}
