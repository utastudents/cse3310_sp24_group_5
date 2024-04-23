
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
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class App extends WebSocketServer {

    // Fields
    private Vector<Game> activeGames;
    private HashMap<String, HashMap<String, ArrayList<UserEvent>>> everyAttempt = new HashMap<String, HashMap<String, ArrayList<UserEvent>>>();
    public App(int port) {
        super(new InetSocketAddress(port));
        this.activeGames = new Vector<>();
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
        System.out.println(conn +": " +message);

        //Bring in data from the webpage
        //Take in userEvents and make into an arrayList
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        UserEvent U = gson.fromJson(message,UserEvent.class);
        insertInnerMap(U,everyAttempt);

        

    }
    public ArrayList<UserEvent> insertEvent(UserEvent U,HashMap<String, ArrayList<UserEvent>> innerMap)
    {
        //declare list
        ArrayList<UserEvent> events = innerMap.get(U.player);
        //if the arrayList of events is null create it, add data, put it in the innermap
        if(events==null)
        {
            events = new ArrayList<UserEvent>();
            events.add(U);
            innerMap.put((U.player).getNick(),events);
        }
        else
        {
            events.add(U);
        }
        return events;
    }
    public void insertInnerMap(UserEvent U,HashMap<String, HashMap<String, ArrayList<UserEvent>>> everyAttempt)
    {
        //declare inner map
        HashMap<String,ArrayList<UserEvent>> innerMap =everyAttempt.get(U.gameId);
        //if inner map is null create it and add the events,then put it in the outer map
        if(innerMap==null)
        {
            innerMap=new HashMap<String,ArrayList<UserEvent>>();
            innerMap.put(U.player.getNick(),insertEvent(U,innerMap));
            everyAttempt.put(U.gameId,innerMap);
        }
        else
        {
            innerMap.put(U.player.getNick(),insertEvent(U,innerMap));
            everyAttempt.put(U.gameId,innerMap);
        }
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
    public Player addPlayer(String playerName) {
        return new Player(playerName);
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
        private String playerName;

        // Constructor
        public Player(String playerName) {
            this.playerName = playerName;
        }

        // Getter
        public String getPlayerName() {
            return playerName;
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
