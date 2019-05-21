package com.pillar.kata;

public class InvalidFileFormatException extends Exception {

    public InvalidFileFormatException(String message) {
        super(message);
    }

    public InvalidFileFormatException(Throwable e) {
        super(e);
    }
}
