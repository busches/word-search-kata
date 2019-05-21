package com.pillar.kata;

import java.io.File;

public class WordSearch {
    public WordSearch() {

    }

    public void search(File file) throws InvalidFileFormatException {
        if (!file.exists()) {
            throw new InvalidFileFormatException();
        }
    }
}
