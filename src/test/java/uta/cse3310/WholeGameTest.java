package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

//import java.lang.annotation.Native;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WholeGameTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     * @return
     */
    public WholeGameTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(WholeGameTest.class);
    }

    ////////////////////////////////////////////////////////////////////////////
    // This test is very close to a system level test.
    // Well, the system without the UI.
    // Inputs and Outputs are provided by JSON strings.
    //
    //
    // Should be able to test all of the logic in the program
    // with these tests.
    //
    // The challenge is doing it without repeating a lot of code, or making
    // it tightly coupled to the specific implementation.
    // To minimize coupling, the majority of the tests should deal with
    // json strings.
    ////////////////////////////////////////////////////////////////////////////
    // Routines to replace those in App.java
    ///////////////////////////////////////////////////////////////////////////

    private void update(Game G, String msg) {
        
    }

    ////////////////////////////////////////////////////////////////////////////
    public void testXWinGame() {
        
        
        
    }
}
