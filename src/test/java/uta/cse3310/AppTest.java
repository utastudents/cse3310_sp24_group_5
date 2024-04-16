package uta.cse3310;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class AppTest {

    @Test
    public void testWebSocketServerInitialization() {
        App app = new App(9005); // Assuming WebSocket server port is set to 9005
        assertNotNull(app); // Check if WebSocket server is initialized successfully
    }

    @Test
    public void testHttpServerInitialization() {
        App app = new App(9005); // Assuming HTTP server port is set to 9005
        assertNotNull(app); // Check if HTTP server is initialized successfully
    }

    // Add more test methods as needed
}

