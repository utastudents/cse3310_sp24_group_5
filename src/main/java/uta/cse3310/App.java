
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
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.time.Instant;
import java.time.Duration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class App extends WebSocketServer {

    public int games = 0;
    int GameId = 1;
    private Vector<Game> activeGames = new Vector<Game>();
    private Map<WebSocket, Player> playerMap;
    private Map<String, WebSocket> playerNickMap;

    public App(int webSocketPort) {
        super(new InetSocketAddress(webSocketPort));
        this.activeGames = new Vector<>();
        this.playerMap = new HashMap<>();
        this.playerNickMap = new HashMap<>();
    }

    public App(InetSocketAddress address) {
        super(address);
    }

    public App(int webSocketPort, Draft_6455 draft) {
        super(new InetSocketAddress(webSocketPort), Collections.<Draft>singletonList(draft));
    }

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

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
            // some errors like port binding failed may not be assignable to a specific
            // websocket
        }
    }

    @Override
    public void onStart() {
        System.out.println("Server started!");
        setConnectionLostTimeout(0);
    }

    public void updateLobby() {
        // Logic for updating the lobby
    }

    public void help() {
        // Logic for displaying help information
    }

    public Player addPlayer(String playerName) {
        Player player = new Player(playerName);
        playerMap.put(player.getWebSocket(), player);
        playerNickMap.put(playerName, player.getWebSocket());
        return player;
    }

    public void toNameSelect() {
        // Logic for navigating to the name selection screen
    }

    public Game createGame(int gameId, int mode) {
        Game game = new Game();
        activeGames.add(game);
        return game;
    }

    public void joinGame(Game game, Player player) {
        // Logic for allowing a player to join a game
    }

    public void endGame(Game game) {
        // Logic for ending a game
    }

    public void toLobby() {
        // Logic for navigating to the lobby
    }

    public void globalChat() {
        // Logic for handling global chat
    }

    public void getPlayerColor() {
        // Logic for getting player color
    }

    public void selectGame() {
        // Logic for selecting a game
    }

    public class Player {
        private String playerName;
        private WebSocket webSocket;

        public Player(String playerName) {
            this.playerName = playerName;
        }

        public String getPlayerName() {
            return playerName;
        }

        public WebSocket getWebSocket() {
            return webSocket;
        }

        public void setWebSocket(WebSocket webSocket) {
            this.webSocket = webSocket;
        }
    }

    public class Game {
        // Define game properties and methods here
    }

    // public class ConcreteApp extends App {

    // public ConcreteApp(int webSocketPort) {
    // super(webSocketPort);
    // }

    // @Override
    // public void onStart() {
    // // Logic for handling server start
    // }

    // @Override
    // public void onError(WebSocket conn, Exception ex) {
    // // Logic for handling errors
    // }
    // }

    public static void main(String[] args) {

        // Set up the http server
        int port = 9080;
        HttpServer H = new HttpServer(port, "./html");
        H.begin();
        System.out.println("http Server started on port:" + port);

        // create and start the websocket server

        port = 9880;
        App A = new App(port);
        A.start();
        System.out.println("websocket Server started on port: " + port);

    }
}