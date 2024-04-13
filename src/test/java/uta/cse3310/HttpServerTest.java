package uta.cse3310;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for http server.
 */
public class HttpServerTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public HttpServerTest(String testName)
    {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(HttpServerTest.class);
    }

    /**
     * Unit test for constructor
     */
    public void testConstructor()
    {
        HttpServer server = new HttpServer(8080, "test_directory");
        if (result == null)
        {
            throw new AssertionError(NULL result");
        }
    }

    /*
     * Test begin method
     */

     public void testBegin() {
        Thread serverThread = startServer();
        boolean isReachable = checkServerReachability();
        assertTrue("Server is not reachable", isReachable);
        stopServer(serverThread);
    }

}