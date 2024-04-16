
// This is example code provided to CSE3310 Fall 2022
// You are free to use as is, or changed, any of the code provided

// Please comply with the licensing requirements for the
// open source packages being used.

// This code is based upon, and derived from the this repository
//            https:/thub.com/TooTallNate/Java-WebSocket/tree/master/src/main/example

// http server include is a GPL licensed package from
//            http://www.freeutils.net/source/jlhttp/

/*
 * Copyright (c) 2010-2020 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */

 package uta.cse3310;

import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;
import java.util.Vector;

public abstract class App extends WebSocketServer {

    private Vector<Game> activeGames;
    private int httpPort;
    private int webSocketPort;
    private int selectedTestGrid;
    private String version;

    public App(int port) {
        super(new InetSocketAddress(port));
        this.activeGames = new Vector<>();
        this.httpPort = port;
        this.webSocketPort = port + 100;
        this.selectedTestGrid = Integer.parseInt(System.getenv("TEST_GRID")); // Retrieving test grid value from environment
        this.version = System.getenv("VERSION"); // Retrieving version from environment
    }

    // WebSocket event handlers

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        // Logic for handling websocket on open event
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        // Logic for handling websocket on close event
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        // Logic for handling websocket message event
    }

    // Method to update the lobby
    public void updateLobby() {
        // Logic for updating the lobby
    }

    // Method for displaying help information
    public void help() {
        // Logic for displaying help information
    }

    // Main method to start the application
    public static void main(String[] args) {
        // Logic for starting the application
    }

    // Method to add a player
    public Player addPlayer(String nick) {
        return new Player(nick);
    }

    // Method to navigate to the name selection screen
    public void toNameSelect() {
        // Logic for navigating to the name selection screen
    }

    // Method to create a game
    public Game createGame(int gameId, int mode) {
        return new Game();
    }

    // Method to allow a player to join a game
    public void joinGame(Game game, Player player) {
        // Logic for allowing a player to join a game
    }

    // Method to end a game
    public void endGame(Game game) {
        // Logic for ending a game
    }

    // Method to navigate to the lobby
    public void toLobby() {
        // Logic for navigating to the lobby
    }

    // Method for handling global chat
    public void globalChat() {
        // Logic for handling global chat
    }

    // Method to get player color
    public void getPlayerColor() {
        // Logic for getting player color
    }

    // Method to select a game
    public void selectGame() {
        // Logic for selecting a game
    }

    // Player class
    public class Player {
        // Default constructor
        public Player() {
            // Initialize any required fields or perform any necessary setup here
        }
    }

    // Game class
    public class Game {
        // Define game properties and methods here
    }

    // ConcreteApp class
    public class ConcreteApp extends App {

        public ConcreteApp(int port) {
            super(port);
        }

        // Implement onStart method
        @Override
        public void onStart() {
            // Logic for handling server start
        }

        // Implement onError method
        @Override
        public void onError(WebSocket conn, Exception ex) {
            // Logic for handling errors
        }
    }
}
