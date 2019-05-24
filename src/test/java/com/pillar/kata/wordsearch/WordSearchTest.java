package com.pillar.kata.wordsearch;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;

public class WordSearchTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testWordSearchRequiresFile() throws Exception {
        expectedException.expect(InvalidFileFormatException.class);
        expectedException.expectMessage("Word Search File Not Found");

        new WordSearch(new File(""));
    }

    @Test
    public void testWordSearchRequiresThreeLinesMinimum() throws Exception {
        expectedException.expect(InvalidFileFormatException.class);
        expectedException.expectMessage("Word Search must contain at least three lines");

        new WordSearch(loadFile("SampleSearchOnlyHeader.txt"));
    }

    @Test
    public void testWordSearchGridShouldBeASquare() throws Exception {
        expectedException.expect(InvalidFileFormatException.class);
        expectedException.expectMessage("Word Search Grid must be a square");

        new WordSearch(loadFile("SampleSearchNotSquare.txt"));
    }

    private File loadFile(String fileName) throws FileNotFoundException {
        var resource = getClass().getClassLoader().getResource(fileName);
        if (resource != null) {
            return new File(resource.getFile());
        } else {
            throw new FileNotFoundException();
        }
    }
}