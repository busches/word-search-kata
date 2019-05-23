package com.pillar.kata;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.FileNotFoundException;

import static junit.framework.TestCase.assertEquals;

public class SolverTest {

    private Solver solver;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void before() {
        solver = new Solver();
    }

    @Test
    public void testWordSearchRequiresFile() throws Exception {
        expectedException.expect(InvalidFileFormatException.class);
        expectedException.expectMessage("Word Search File Not Found");

        solver.solve(new File(""));
    }

    @Test
    public void testWordSearchRequiresThreeLinesMinimum() throws Exception {
        expectedException.expect(InvalidFileFormatException.class);
        expectedException.expectMessage("Word Search must contain at least three lines");

        solver.solve(loadFile("SampleSearchOnlyHeader.txt"));
    }

    @Test
    public void testWordSearchGridShouldBeASquare() throws Exception {
        expectedException.expect(InvalidFileFormatException.class);
        expectedException.expectMessage("Word Search Grid must be a square");

        solver.solve(loadFile("SampleSearchNotSquare.txt"));
    }

    @Test
    public void testWordSearchFindsWordHorizontally() throws Exception {
        var wordsFound = solver.solve(loadFile("SampleSearchOneWord.txt"));
        assertEquals(1, wordsFound.size());
        assertEquals("SCOTTY: (0,5),(1,5),(2,5),(3,5),(4,5),(5,5)", wordsFound.get(0));
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
