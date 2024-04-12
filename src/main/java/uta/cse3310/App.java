
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

 import java.io.BufferedReader;
 import java.io.IOException;
 import java.io.InputStreamReader;
 import java.net.InetSocketAddress;
 import java.net.UnknownHostException;
 import java.nio.ByteBuffer;
 import java.util.Collections;
 
 import org.java_websocket.WebSocket;
 import org.java_websocket.drafts.Draft;
 import org.java_websocket.drafts.Draft_6455;
 import org.java_websocket.handshake.ClientHandshake;
 import org.java_websocket.server.WebSocketServer;
 import java.util.Timer;
 import java.util.TimerTask;
 import java.util.Vector;
 import java.time.Instant;
 import java.time.Duration;
 
 import com.google.gson.Gson;
 import com.google.gson.GsonBuilder;
 
 public abstract class App extends WebSocketServer {
 
     // All games currently underway on this server are stored in
     // the vector activeGames
     private Vector<Game> activeGames;
     private int httpPort;
     private int webSocketPort;
     private int selectedTestGrid;
     private String version;
 
     // Constructor
     public App(int port) {
         super(new InetSocketAddress(port));
         this.activeGames = new Vector<>();
         this.httpPort = port;
         this.webSocketPort = port + 100;
         this.selectedTestGrid = Integer.parseInt(System.getenv("TEST_GRID"));
         this.version = System.getenv("VERSION");
     }
 
     // Method for handling websocket on open event
     @Override
     public void onOpen(WebSocket conn, ClientHandshake handshake) {
         // Logic for handling websocket on open event
     }
 
     // Method for handling websocket on close event
     @Override
     public void onClose(WebSocket conn, int code, String reason, boolean remote) {
         // Logic for handling websocket on close event
     }
 
     // Method for handling websocket message event
     @Override
     public void onMessage(WebSocket conn, String message) {
         // Logic for handling websocket message event
     }
 
     // Method to update lobby
     public void updateLobby() {
         // Logic for updating the lobby
     }
 
     // Method for help tab
     public void help() {
         // Logic for displaying help information
     }
 
     // Main method
     public static void main(String[] args) {
         // Logic for starting the application
     }
 
     // Method for adding a player
     public Player addPlayer() {
         return new Player();
     }
 
     // Method for nameselect
     public void toNameSelect() {
         // Logic for navigating to the name selection screen
     }
 
     // Method for creategame
     public Game createGame(int gameId, int mode) {
         return new Game();
     }
 
     // Method to join a game
     public void joinGame(Game game, Player player) {
         // Logic for allowing a player to join a game
     }
 
     // Method to end a game
     public void endGame(Game game) {
         // Logic for ending a game
     }
 
     // Method to move to the lobby
     public void toLobby() {
         // Logic for navigating to the lobby
     }
 
     // Method for global chat
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
 }
 