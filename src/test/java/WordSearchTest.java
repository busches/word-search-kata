import com.pillar.kata.InvalidFileFormatException;
import com.pillar.kata.WordSearch;
import org.junit.Test;

import java.io.File;

public class WordSearchTest {

    @Test(expected = InvalidFileFormatException.class)
    public void testWordSearchRequiresFile() throws Exception {
        var wordSearch = new WordSearch();
        wordSearch.search(new File(""));
    }
}
