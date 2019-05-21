package com.pillar.kata;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class WordSearch {
    public void search(File file) throws InvalidFileFormatException {
        if (!file.exists()) {
            throw new InvalidFileFormatException("Word Search File Not Found");
        } else {
            try {
                long fileLineCount = Files.lines(file.toPath()).count();
                if (fileLineCount < 3) {
                    throw new InvalidFileFormatException("Word Search must contain at least three lines");
                } else {
                    List<String> entireFile = Files.readAllLines(file.toPath());
                    if (entireFile.stream()
                            .skip(1) // First row is lines to search
                            .anyMatch(line -> line.split(",").length != fileLineCount)) {
                        throw new InvalidFileFormatException("Word Search Grid must be a square");
                    }
                }
            } catch (IOException e) {
                throw new InvalidFileFormatException(e);
            }
        }
    }
}
