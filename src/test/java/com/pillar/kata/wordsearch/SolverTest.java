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

    @Test
    public void testWordSearchFindsWordDiagonallyAscendingReverse() throws Exception {
        var wordsFound = solver.solve(new WordSearch(loadFile("SampleSearchDiagonalAscendingReverse.txt")));
        assertEquals(1, wordsFound.size());
        assertEquals("SULU: (3,3),(2,2),(1,1),(0,0)", wordsFound.get(0));
    }

    @Test
    public void testWordSearchFindsAllTypes() throws Exception {
        var wordsFound = solver.solve(new WordSearch(loadFile("SampleSearchAllTypes.txt")));
        assertEquals(8, wordsFound.size());
        assertEquals("BONES: (0,6),(0,7),(0,8),(0,9),(0,10)", wordsFound.get(0));
        assertEquals("KHAN: (5,9),(5,8),(5,7),(5,6)", wordsFound.get(1));
        assertEquals("KIRK: (4,7),(3,7),(2,7),(1,7)", wordsFound.get(2));
        assertEquals("SCOTTY: (0,5),(1,5),(2,5),(3,5),(4,5),(5,5)", wordsFound.get(3));
        assertEquals("SPOCK: (2,1),(3,2),(4,3),(5,4),(6,5)", wordsFound.get(4));
        assertEquals("SULU: (3,3),(2,2),(1,1),(0,0)", wordsFound.get(5));
        assertEquals("UHURA: (4,0),(3,1),(2,2),(1,3),(0,4)", wordsFound.get(6));
        assertEquals("BSSH: (0,3),(1,2),(2,1),(3,0)", wordsFound.get(7));
    }

    @Test
    public void testWordSearchLargePuzzle() throws Exception {
        var wordsFound = solver.solve(new WordSearch(loadFile("LargePuzzle.txt")));
        assertEquals(19, wordsFound.size());
        assertEquals("ANTMAN: (18,0),(17,0),(16,0),(15,0),(14,0),(13,0)", wordsFound.get(0));
        assertEquals("BLACKPANTHER: (33,36),(32,35),(31,34),(30,33),(29,32),(28,31),(27,30),(26,29),(25,28),(24,27),(23,26),(22,25)", wordsFound.get(1));
        assertEquals("BLACKWIDOW: (13,30),(13,29),(13,28),(13,27),(13,26),(13,25),(13,24),(13,23),(13,22),(13,21)", wordsFound.get(2));
        assertEquals("CAPTAINAMERICA: (4,17),(4,18),(4,19),(4,20),(4,21),(4,22),(4,23),(4,24),(4,25),(4,26),(4,27),(4,28),(4,29),(4,30)", wordsFound.get(3));
        assertEquals("DOCTORSTRANGE: (17,18),(16,19),(15,20),(14,21),(13,22),(12,23),(11,24),(10,25),(9,26),(8,27),(7,28),(6,29),(5,30)", wordsFound.get(4));
        assertEquals("FALCON: (37,11),(36,10),(35,9),(34,8),(33,7),(32,6)", wordsFound.get(5));
        assertEquals("HAWKEYE: (28,20),(27,21),(26,22),(25,23),(24,24),(23,25),(22,26)", wordsFound.get(6));
        assertEquals("HULK: (23,28),(23,29),(23,30),(23,31)", wordsFound.get(7));
        assertEquals("HULK: (28,38),(29,37),(30,36),(31,35)", wordsFound.get(8)); // Technically part of HulkBuster, never said to only find first match ¯\_(ツ)_/¯
        assertEquals("HULKBUSTER: (28,38),(29,37),(30,36),(31,35),(32,34),(33,33),(34,32),(35,31),(36,30),(37,29)", wordsFound.get(9));
        assertEquals("IRONMAN: (37,4),(37,5),(37,6),(37,7),(37,8),(37,9),(37,10)", wordsFound.get(10));
        assertEquals("MARIAHILL: (12,0),(13,1),(14,2),(15,3),(16,4),(17,5),(18,6),(19,7),(20,8)", wordsFound.get(11));
        assertEquals("OKOYE: (24,25),(25,25),(26,25),(27,25),(28,25)", wordsFound.get(12));
        assertEquals("SHURI: (16,7),(17,6),(18,5),(19,4),(20,3)", wordsFound.get(13));
        assertEquals("THOR: (24,3),(25,4),(26,5),(27,6)", wordsFound.get(14));
        assertEquals("VISION: (2,29),(2,28),(2,27),(2,26),(2,25),(2,24)", wordsFound.get(15));
        assertEquals("WARMACHINE: (6,12),(7,12),(8,12),(9,12),(10,12),(11,12),(12,12),(13,12),(14,12),(15,12)", wordsFound.get(16));
        assertEquals("WASP: (8,25),(7,26),(6,27),(5,28)", wordsFound.get(17));
        assertEquals("WINTERSOLDIER: (14,39),(13,39),(12,39),(11,39),(10,39),(9,39),(8,39),(7,39),(6,39),(5,39),(4,39),(3,39),(2,39)", wordsFound.get(18));
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
