package com.pillar.kata;

import org.junit.Test;

import java.io.File;

public class WordSearchTest {

    @Test(expected = InvalidFileFormatException.class)
    public void testWordSearchRequiresFile() throws Exception {
        var wordSearch = new WordSearch();
        wordSearch.search(new File(""));
    }

    @Test(expected = InvalidFileFormatException.class)
    public void testWordSearchRequiresThreeLinesMinimum() throws Exception {
        var wordSearch = new WordSearch();
        wordSearch.search(new File(getClass().getClassLoader().getResource("SampleSearchOnlyHeader.txt").getFile()));
    }
}
