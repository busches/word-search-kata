package com.pillar.kata;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class WordSearchTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testWordSearchRequiresFile() throws Exception {
        expectedException.expect(InvalidFileFormatException.class);
        expectedException.expectMessage("Word Search File Not Found");

        var wordSearch = new WordSearch();
        wordSearch.search(new File(""));
    }

    @Test
    public void testWordSearchRequiresThreeLinesMinimum() throws Exception {
        expectedException.expect(InvalidFileFormatException.class);
        expectedException.expectMessage("Word Search must contain at least three lines");

        var wordSearch = new WordSearch();
        wordSearch.search(new File(getClass().getClassLoader().getResource("SampleSearchOnlyHeader.txt").getFile()));
    }

    @Test
    public void testWordSearchGridShouldBeASquare() throws Exception {
        expectedException.expect(InvalidFileFormatException.class);
        expectedException.expectMessage("Word Search Grid must be a square");

        var wordSearch = new WordSearch();
        wordSearch.search(new File(getClass().getClassLoader().getResource("SampleSearchNotSquare.txt").getFile()));
    }

    @Test
    public void testWordSearchFindsWordHorizontally() throws Exception {
        var wordSearch = new WordSearch();
        List<String> wordsFound = wordSearch.search(new File(getClass().getClassLoader().getResource("SampleSearchOneWord.txt").getFile()));
        assertEquals(1, wordsFound.size());
        assertEquals("SCOTTY: (0,5),(1,5),(2,5),(3,5),(4,5),(5,5)", wordsFound.get(0));
    }

}
