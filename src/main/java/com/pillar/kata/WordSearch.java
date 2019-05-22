package com.pillar.kata;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class WordSearch {
    public List<String> search(File file) throws InvalidFileFormatException, IOException {
        validateInput(file);

        // This should likely return a List<String, List<Coordinate>> but keeping it simple for now
        List<String> foundWords = new ArrayList<>();

        List<String> entireFile = Files.readAllLines(file.toPath());
        String[] wordsToSearch = entireFile.get(0).split(",");
        entireFile.remove(0);
        for (String wordToFind : wordsToSearch) {
            for (String line : entireFile) {
                String formattedLine = line.replace(",", "");
                if (formattedLine.contains(wordToFind)) {
                    foundWords.add(wordToFind);
                    break;
                }
            }
        }

        return foundWords;
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
}
