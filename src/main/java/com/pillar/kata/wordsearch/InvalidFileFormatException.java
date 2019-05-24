package com.pillar.kata.wordsearch;

public class InvalidFileFormatException extends Exception {

    public InvalidFileFormatException(String message) {
        super(message);
    }

    public InvalidFileFormatException(Throwable e) {
        super(e);
    }
}
