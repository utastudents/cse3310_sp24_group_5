package uta.cse3310;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    private App app;

    @Before
    public void setUp() {
        int port = Integer.parseInt(System.getenv("HTTP_PORT"));
        this.app = new App(port) {
            @Override
            public void onStart() {
                // Override onStart method for testing
            }

            @Override
            public void onOpen(WebSocket conn, ClientHandshake handshake) {
                // Override onOpen method for testing
            }

            @Override
            public void onClose(WebSocket conn, int code, String reason, boolean remote) {
                // Override onClose method for testing
            }

            @Override
            public void onMessage(WebSocket conn, String message) {
                // Override onMessage method for testing
            }
        };
    }

    @Test
    public void testApp() {
        assertNotNull(app);
    }

    // Add more test methods for other functionalities of the App class
}

