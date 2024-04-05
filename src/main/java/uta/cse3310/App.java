
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

public abstract class App extends WebSocketServer 
{

  // All games currently underway on this server are stored in
  // the vector activeGames
    private Vector<Game> activeGames;
    //private HTTPServer server;

    // Constructor
    public App(int port) 
    {
      //Initialize activeGames Vector and httpserver
    }

    //Method for handling websocket on open event
    public void onOpen(WebSocket conn, ClientHandshake handshake) 
    {
        
    }

    //Method for handling websocket on close event
    public void onClose(WebSocket conn, int code, String reason, boolean remote) 
    {
        
    }

    //Method for handling websocket message event
    public void onMessage(WebSocket conn, String message) 
    {
      
    }

    //Method to update lobby
    public void updateLobby() 
    {
      
    }

    //Method for help tab
    public void help() 
    {
        
    }

    //Main method
    public static void main(String[] args) 
    {
        
    }

    //Method for adding a player
    public Player addPlayer() 
    {
        return new Player();
    }

    //Method for nameselect
    public void toNameSelect() 
    {
       
    }

    //Method for creategame
    public Game createGame(int gameId, int mode) 
    {
        return new Game();
    }

    //Method to join a game
    public void joinGame(Game game, Player player) 
    {
        
    }

    //Method to end a game
    public void endGame(Game game) 
    {
        
    }

    //Method to move to the lobby
    public void toLobby() 
    {
      
    }

    //Method for global chat
    public void globalChat() 
    {
        
    }

    //Method to get player color
    public void getPlayerColor() 
    {
       
    }

    //Method to select a game
    public void selectGame() 
    {
       
    }
}
