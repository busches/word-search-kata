package com.pillar.kata;

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


    private File loadFile(String fileName) throws FileNotFoundException {
        var resource = getClass().getClassLoader().getResource(fileName);
        if (resource != null) {
            return new File(resource.getFile());
        } else {
            throw new FileNotFoundException();
        }
    }
}
