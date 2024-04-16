package uta.cse3310;

//import java.net.InetAddress;
//import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class Chat 
{
    private String message;
    private BufferedReader input;
    private PrintWriter output;

    // contructor to establish connection with sockets and setup I&O Streams
    public Chat() throws IOException
    {
        Socket socket= new Socket();
        input= new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output= new PrintWriter(socket.getOutputStream(), true);
    }



    // method to send input message
    public void sendMessage(String message) 
    {
        output.println(message);
    }

    // method to receive input message
    public void receiveMessage(String message) 
    {
        try {
            while ((message = input.readLine()) != null)
            {
                System.out.println("New message from player: " + message);
            }
        } catch (IOException e)
        {
            System.err.println("Error reading message. Try Again: " + e.getMessage());
        }
    }
}
