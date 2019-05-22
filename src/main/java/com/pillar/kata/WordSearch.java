package com.pillar.kata;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WordSearch {
    public List<String> search(File file) throws InvalidFileFormatException, IOException {
        validateInput(file);

        // This should likely return a List<String, List<Coordinate>> but keeping it simple for now
        List<String> foundWords = new ArrayList<>();

        List<String> entireFile = Files.readAllLines(file.toPath());
        String[] wordsToSearch = entireFile.get(0).split(",");
        entireFile.remove(0);
        for (String wordToFind : wordsToSearch) {
            for (int lineNumber = 0; lineNumber < entireFile.size(); lineNumber++) {
                String line = entireFile.get(lineNumber);
                String formattedLine = line.replace(",", "");
                if (formattedLine.contains(wordToFind)) {
                    final int y = lineNumber;
                    String coordinates = IntStream.range(formattedLine.indexOf(wordToFind), wordToFind.length())
                            .mapToObj(x -> String.format("(%s,%s)", x, y))
                            .collect(Collectors.joining(","));
                    foundWords.add(String.format("%s: %s", wordToFind, coordinates));
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
