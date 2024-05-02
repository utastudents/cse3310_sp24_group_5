package uta.cse3310;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.freeutils.httpserver.HTTPServer;
import net.freeutils.httpserver.HTTPServer.ContextHandler;
import net.freeutils.httpserver.HTTPServer.FileContextHandler;
import net.freeutils.httpserver.HTTPServer.Request;
import net.freeutils.httpserver.HTTPServer.Response;
import net.freeutils.httpserver.HTTPServer.VirtualHost;

// http server include is a GPL licensed package from
//            http://www.freeutils.net/source/jlhttp/

public class HttpServer
{
    private String HTML;
    private int port = 8080;
    String dirname = HTML;

    public HttpServer(int portNum, String dirName)
    {
        port = portNum;
        dirname = dirName;
    }
    //method begin
    // Method to start the HTTP server
    public void begin() {
        try {
            // Create a File object representing the directory
            File dir = new File(dirname);

            // Check if directory is readable, throw exception if not
            if (!dir.canRead())
                throw new FileNotFoundException(dir.getAbsolutePath());

            // Create an HTTPServer instance listening on the specified port
            HTTPServer server = new HTTPServer(port);

            // Get the default virtual host from the server
            VirtualHost host = server.getVirtualHost(null);

            // Allow generated index for the virtual host
            host.setAllowGeneratedIndex(true);

            // Add context for serving files from root directory
            host.addContext("/", new FileContextHandler(dir));

            // Add context for serving current time
            host.addContext("/api/time", new ContextHandler() {
                // Serve method to handle requests for current time
                public int serve(Request req, Response resp) throws IOException {
                    // Get current time in milliseconds
                    long now = System.currentTimeMillis();

                    // Set content type header
                    resp.getHeaders().add("Content-Type", "text/plain");

                    // Send response with current time
                    resp.send(200, String.format("%tF %<tT", now));
                    return 0;
                }
            });

            // Start the HTTP server
            server.start();
        } catch (Exception e) {
            // Print error message if any exception occurs
            System.err.println("error: " + e);
        }
    }
}