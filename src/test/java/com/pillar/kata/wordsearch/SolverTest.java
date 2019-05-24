package com.pillar.kata.wordsearch;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static junit.framework.TestCase.assertEquals;

public class SolverTest {

    private Solver solver;

    @Before
    public void before() {
        solver = new Solver();
    }

    @Test
    public void testWordSearchFindsWordHorizontally() throws Exception {
        var wordsFound = solver.solve(new WordSearch(loadFile("SampleSearchOneWord.txt")));
        assertEquals(1, wordsFound.size());
        assertEquals("SCOTTY: (0,5),(1,5),(2,5),(3,5),(4,5),(5,5)", wordsFound.get(0));
    }

    @Test
    public void testWordSearchFindsWordVertically() throws Exception {
        var wordsFound = solver.solve(new WordSearch(loadFile("SampleSearchVerticalWord.txt")));
        assertEquals(1, wordsFound.size());
        assertEquals("BONES: (0,6),(0,7),(0,8),(0,9),(0,10)", wordsFound.get(0));
    }

    @Test
    public void testWordSearchFindsWordDiagonallyDescending() throws Exception {
        var wordsFound = solver.solve(new WordSearch(loadFile("SampleSearchDiagonalDescendingWord.txt")));
        assertEquals(1, wordsFound.size());
        assertEquals("SPOCK: (2,1),(3,2),(4,3),(5,4),(6,5)", wordsFound.get(0));
    }

    @Test
    public void testWordSearchFindsWordDiagonallyAscending() throws Exception {
        var wordsFound = solver.solve(new WordSearch(loadFile("SampleSearchDiagonalAscendingWord.txt")));
        assertEquals(2, wordsFound.size());
        assertEquals("BSSH: (0,3),(1,2),(2,1),(3,0)", wordsFound.get(0));
        assertEquals("EXH: (12,14),(13,13),(14,12)", wordsFound.get(1));
    }

    @Test
    public void testWordSearchFindsWordHorizontalReverse() throws Exception {
        var wordsFound = solver.solve(new WordSearch(loadFile("SampleSearchHorizontalReverse.txt")));
        assertEquals(1, wordsFound.size());
        assertEquals("KIRK: (4,7),(3,7),(2,7),(1,7)", wordsFound.get(0));
    }

    @Test
    public void testWordSearchFindsWordVerticalReverse() throws Exception {
        var wordsFound = solver.solve(new WordSearch(loadFile("SampleSearchVerticalReverse.txt")));
        assertEquals(1, wordsFound.size());
        assertEquals("KHAN: (5,9),(5,8),(5,7),(5,6)", wordsFound.get(0));
    }

    @Test
    public void testWordSearchFindsWordDiagonallyDescendingReverse() throws Exception {
        var wordsFound = solver.solve(new WordSearch(loadFile("SampleSearchDiagonalDescendingReverse.txt")));
        assertEquals(1, wordsFound.size());
        assertEquals("UHURA: (4,0),(3,1),(2,2),(1,3),(0,4)", wordsFound.get(0));
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
