package com.pillar.kata;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class WordSearch {
    public WordSearch() {

    }

    public void search(File file) throws InvalidFileFormatException {
        if (!file.exists()) {
            throw new InvalidFileFormatException("Word Search File Not Found");
        } else {
            try {
                if (Files.lines(file.toPath()).count() < 3) {
                    throw new InvalidFileFormatException("Word Search must contain at least three lines");
                }
            } catch (IOException e) {
                throw new InvalidFileFormatException(e);
            }
        }
    }
}
