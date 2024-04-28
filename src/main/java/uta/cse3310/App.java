
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
import java.util.UUID;
import java.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.List;
import java.util.ArrayList;
import java.time.Instant;
import java.time.Duration;
import java.lang.Character;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;



import java.util.ArrayList;


public class App extends WebSocketServer {

    public Vector<Game> activeGames = new Vector<Game>();
    private HashMap<String, HashMap<String, ArrayList<UserEvent>>> everyAttempt = new HashMap<String, HashMap<String, ArrayList<UserEvent>>>();
    public Map<WebSocket, Player> Connections = new HashMap<WebSocket, Player>();
    public Map<String, Player> Actives = new HashMap<String, Player>();
    public Lobby lobbies = new Lobby();

    public App(int webSocketPort) {
        super(new InetSocketAddress(webSocketPort));
        
    }

    public App(InetSocketAddress address) {
        super(address);
    }

    public App(int webSocketPort, Draft_6455 draft) {
        super(new InetSocketAddress(webSocketPort), Collections.<Draft>singletonList(draft));
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        System.out.println(conn.getRemoteSocketAddress().getAddress().getHostAddress() + " connected");
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.excludeFieldsWithoutExposeAnnotation().create();
        //Gson gson = new Gson();
        String jsonString;
        jsonString = gson.toJson("New Server Connection");
        newNetwork(conn, gson);
        broadcast(jsonString);
        // Logic for handling websocket on open event
    }

    public void newNetwork(WebSocket conn, Gson gson) {
        // Logic for handling new network connections
        String id = UUID.randomUUID().toString();
        Player newPlayer = new Player();
        Connections.put(conn, newPlayer);
        Actives.put(id, newPlayer);
        //updateLobby(gson);

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
        Gson gson = builder.excludeFieldsWithoutExposeAnnotation().create();
        //Gson gson = builder.create();
        messageHandler(gson,message,conn);
        

        

    }
    public ArrayList<UserEvent> insertEvent(UserEvent U,HashMap<String, ArrayList<UserEvent>> innerMap)
    {
        //declare list
        ArrayList<UserEvent> events = new ArrayList<UserEvent>();
        //innerMap.get(U.player);
        //if the arrayList of events is null create it, add data, put it in the innermap
        if(!(innerMap.containsKey(U.player.getNick())))
        {
            
            events.add(U);
            innerMap.put((U.player).getNick(),events);
            
        }
        else
        {
            //events.add(U);
            innerMap.get(U.player.getNick()).add(U);
            
        }
        return innerMap.get(U.player.getNick());
    }
    public void insertInnerMap(UserEvent U,HashMap<String, HashMap<String, ArrayList<UserEvent>>> everyAttempt)
    {
        //declare inner map
        HashMap<String,ArrayList<UserEvent>> innerMap = new HashMap<String, ArrayList<UserEvent>>();
        innerMap=everyAttempt.get(U.gameId);
        //if inner map is null create it and add the events,then put it in the outer map
        if(innerMap==null)
        {
            
            innerMap=new HashMap<String,ArrayList<UserEvent>>();
            ArrayList<UserEvent> events=insertEvent(U,innerMap);
            innerMap.put(U.player.getNick(),events);
            everyAttempt.put(U.gameId,innerMap);
        }
        else
        {
            innerMap.put(U.player.getNick(),insertEvent(U,innerMap));
            everyAttempt.put(U.gameId,innerMap);
        }
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        ex.printStackTrace();
        if (conn != null) {
            // some errors like port binding failed may not be assignable to a specific
            // websocket
        }
        System.out.println("[ERROR] onError()");
    }

    @Override
    public void onStart() {
        System.out.println("Server has started!");
        setConnectionLostTimeout(0);
    }


    public void updateLobby(Gson gson,JsonObject jsonObject) {
        // Logic for updating the lobby
        lobbies.updateLobby(activeGames);
        jsonObject.addProperty("type", "updateLobby");
        //jsonObject.addProperty("id", id);//not sure of the purpose -muktar
        //get String version of playerToGameMap -muktar
        String map=new String();
        map=lobbies.mapToString();
        //parse map into array of keys and values-muktar
        String[]mapParts=map.split(",");
        //Split each element of mapParts by ":" to get keys and values -muktar
        String []keys =new String[Connections.size()];
        String []values=new String[Connections.size()];
        for(int i=0;i<Connections.size();i++)
        {
            for(String part : mapParts)
            {
                if(part!="")
                {
                    String[] temp = part.split(":");
                    keys[i]=temp[0].trim();
                    values[i]=temp[1].trim();
                }
            }
        }
        //send the keys and values to every connection -muktar
        jsonObject.addProperty("keys", gson.toJson(keys));
        //jsonObject.addProperty("values",gson.toJson(values));
        System.out.println(lobbies.toString());
        broadcast(jsonObject.toString()); // Sendinfo about lobby & ID to the client
    }

    public void help() {
        // Logic for displaying help information
    }

    public Player addPlayer(String playerName) {
        Player player = new Player(playerName);
       
        return player;
    }

    public void toNameSelect() {
        // Logic for navigating to the name selection screen
    }

    public Game createGame(String gameId,int mode) {
        Game game = new Game();
        game.setGameID(gameId);
        game.setGameMode(mode);
        activeGames.add(game);
        return game;
    }

    public boolean joinGame(Game game, Player player) {
        // Logic for allowing a player to join a game
        if(game.getPlayersList().size()<game.getGameMode())
        {
            game.addPlayers(player);
            System.out.println("Player List: "+game.getPlayersList().toString());
            System.out.println("gameMode:"+game.getGameMode()+" Num players "+game.getPlayersList().size());
            if(game.getPlayersList().size()==game.getGameMode())
            {
                game.startGame();
            }
            return true;
        }
        else if(game.getPlayersList().size()>=game.getGameMode())
        {
            System.out.println("This game is full");
            return false;
        }
        return false;
    }

    public void endGame(Game game,Gson gson) 
    {
        // Logic for ending a game
        game.exitGame(game.getPlayersList());
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("type","endGame");
        jsonObject.addProperty("winner",game.winner.getNick());
        ArrayList<Player> playerList=new ArrayList<Player>();
            playerList=game.getPlayersList();
            for(Player i:playerList)
            {
                //search through the key/value pairs
                for(Entry<WebSocket,Player> entry:Connections.entrySet())
                {
                    if(entry.getValue()==i)//if the player in the map is the same as in outer loop
                    {
                        entry.getKey().send(jsonObject.toString());//send the jsonObject to their connection
                        break;
                    }
                }
            }
        activeGames.remove(game);
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

    
    // public class Game {
    // // Define game properties and methods here
    // }

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
        String HttpPort = System.getenv("HTTP_PORT");
        int port = 9005;
        if(HttpPort!=null)
        {
            port= Integer.valueOf(HttpPort);
        }
        HttpServer H = new HttpServer(port, "./html");
        H.begin();
        System.out.println("http Server started on port: " + port);

        // create and start the websocket server

        port = 9105;
        String WsocketPort= System.getenv("WEBSOCKET_PORT");
        if(WsocketPort!=null)
        {
            port=Integer.valueOf(WsocketPort);
        }
        App A = new App(port);
        A.setReuseAddr(true);
        A.start();
        System.out.println("websocket Server started on port: " + port);

    }

    public void messageHandler(Gson gson, String jsonString, WebSocket conn)
    {
        //get string and turn it into a json object
        JsonElement element = JsonParser.parseString(jsonString);
        JsonObject object = element.getAsJsonObject();
        //each message has a specific type
        String messageType = object.get("type").getAsString();
        //jsonObject will be sent back to client
        JsonObject jsonObject = new JsonObject();
        JsonArray jsonArray = new JsonArray();

        String json = new String();
        //the way to handle a message determined by type
        switch(messageType)
        {
            case ("JoinGame"):
                boolean joined=false;
                String gameId= object.get("gameIndex").getAsString();
                String gameMode = object.get("modeIndex").getAsString();
                int mode = Character.getNumericValue(gameMode.toCharArray()[0]);
                Player player= Connections.get(conn);
                player.setNick(object.get("nick").getAsString());

                //update playerToGameMap in the lobby
                lobbies.playerToGameMap.put(player.getNick(),gameId);
                int numGames= 0;
                Game G = new Game();
                //search through active games
                for(Game i : activeGames)
                {
                    //if the game exists join it
                    G = i;
                    if(G.getGameID().equals(gameId))
                    {
                        joined=joinGame(G,player);
                        break;
                    }
                    else
                    {
                        numGames++;
                    }

                }
                //if game doesn't exist, create it then join
                if(numGames>=activeGames.size())
                {
                    G = createGame(gameId,mode);
                    System.out.println("creating game "+gameId);
                    joined=joinGame(G,player);
                }
                //send back message to all connections if player successfully joints
                if(joined==true)
                {
                    jsonObject.addProperty("type","JoinGame");
                    //create data to send back
                    
                    //add player to json object to send back
                    json = gson.toJson(player);
                    jsonObject.addProperty("player",json);
                    
                    //add game to json Object to send back
                    if(G.grid!=null)
                    {
                        json = gson.toJson(G.grid);
                        jsonObject.addProperty("grid",json); 
                    }
                    
                    jsonObject.addProperty("gameId",G.getGameID());
                    //if enough players joined, automatically start game
                    if(G.getPlayersList().size()==G.getGameMode())
                    {
                        System.out.println("This game has "+ G.getPlayersList().size()+ " many people in it");
                        jsonObject.addProperty("ready","true");
                    }
                    else
                    {
                        jsonObject.addProperty("ready","false");
                    }
                    
                    //send back to all connections

                    broadcast(jsonObject.toString());
                }
                break;
            case("UpdateGame"):
                JsonObject events=object.get("events").getAsJsonObject();
                updateHandler(gson,events,conn); 
                
                
                break;

            case("RequestGameList"):
                jsonObject.addProperty("type","RequestGameList");
                //FIX LATER
                //does not end up sending the active games
                String jsonGameList= gson.toJson(activeGames);
                System.out.println(activeGames.toString());
                jsonObject.addProperty("gameList",jsonGameList);
                conn.send(jsonObject.toString());
                break;
            case("endGame"):
                //find game from active games
                for(Game i:activeGames)
                {
                    if(i.getGameID().equals(object.get("gameId").getAsString()))
                    {   
                        if(object.get("reason").equals("OutOfTime"))
                        {
                            endGame(i,gson);
                            break;
                        }
                        else if(object.get("reason").equals("Player Disconnected"))
                        {
                            activeGames.remove(i);
                            break;
                        }
                    }
                }

                break;
            case("updateLobby"):
                updateLobby(gson,jsonObject);
                /*ArrayList<String>lobbyData=new ArrayList<String>();
                ArrayList<Player> players=new ArrayList<Player>(); 
                for(Game i : activeGames)
                {
                    gameId=i.getGameID();
                    lobbyData.add(gameId);
                    players=i.getPlayersList();
                    for(Player j :players)
                    {
                        lobbyData.add(j.getNick());
                    }
                }
                jsonObject.addProperty("type","updateLobby");
                jsonObject.addProperty("lobbyData",lobbyData.toString());
                broadcast(jsonObject.toString());*/
                break;
            default:
                System.out.println("Unexpected message");
                




        }
    }


    public void updateHandler(Gson gson, JsonObject message, WebSocket conn)
    {
        String evtMessage=message.toString();
        System.out.println(evtMessage);
        UserEvent U = gson.fromJson(message,UserEvent.class);//turn message into userEvent instance
        U.action = Integer.valueOf(message.get("action").getAsString());
        U.cell = Integer.valueOf(message.get("cell").getAsString());
        U.player = Connections.get(conn);
        U.gameId = message.get("gameId").getAsString();
        
        if(U.action!=2)
        {
            insertInnerMap(U,everyAttempt);
            
        }//add U to arraylist
        
        String jsonString= new String();//will be returned
        jsonString="";//if update happens, will become jsonString of updated game 
        JsonObject jsonObject=new JsonObject();//will be sent to various connections
        Game G = new Game();
        boolean valid=false;//will determine if word is valid
        ArrayList<UserEvent> attempt= new ArrayList<UserEvent>();//series of userevents that make up an attempted word
        if(U.action==2)
        {
            
            attempt=everyAttempt.get(U.gameId).get(U.player.getNick());
            int [] cells= new int[attempt.size()];
            int k=0;
            for(UserEvent i: attempt)
            {
                cells[k]=i.cell;
                k++;
            }
            
            for(Game i : activeGames)
            {
                if(i.getGameID().equals(U.gameId))
                {
                    G = i;
                    valid=G.updateGame(attempt);
                    System.out.println("Was it a valid word? : "+valid);
                    break;
                }
            }
            
            jsonString = gson.toJson(G);
            System.out.println("GameId= "+U.gameId+" G's GameId= "+G.getGameID());
            
        
            jsonObject.addProperty("type","UpdateGame");
            jsonObject.addProperty("gameData",jsonString);
            jsonObject.addProperty("attempt",gson.toJson(cells));
            jsonObject.addProperty("valid",String.valueOf(valid));
            jsonObject.addProperty("score",String.valueOf(U.player.getScore()));
            conn.send(jsonObject.toString());
            //search through the game's player list
            ArrayList<Player> playerList=new ArrayList<Player>();
            playerList=G.getPlayersList();
            for(Player i:playerList)
            {
                //search through the key/value pairs
                for(Entry<WebSocket,Player> entry:Connections.entrySet())
                {
                    if(entry.getValue()==i)//if the player in the map is the same as in outer loop
                    {
                        entry.getKey().send(jsonObject.toString());//send the jsonObject to their connection
                        break;
                    }
                }
            }
        }
        attempt.clear();
        
    }
}