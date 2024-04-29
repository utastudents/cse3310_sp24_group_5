package uta.cse3310;

import java.util.List;
import java.util.Arrays;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for WordList class.
 */
public class WordListTest extends TestCase {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public WordListTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(WordListTest.class);
    }

    /**
     * Test the getWordList method.
     */
    public void testGetWordList() {
        WordList wordList = new WordList();
        String testFile = "src/test/java/uta/cse3310/testWords.txt"; 
        
        List<String> expectedWords = Arrays.asList("This", "is", "a", "test", "file");
        
        List<String> actualWords = wordList.getWordList(testFile);
        for(String i:expectedWords)
        {
            assertTrue(actualWords.contains(i));
        }
        
    }
}