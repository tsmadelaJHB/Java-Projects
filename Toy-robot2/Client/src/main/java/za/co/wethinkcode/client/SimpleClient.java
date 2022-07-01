package za.co.wethinkcode.client;

import org.json.simple.JSONObject;
import za.co.wethinkcode.client.commands.CommandHandler;

import java.io.*;
import java.net.ConnectException;
import java.net.Socket;

public class SimpleClient {

    /**
     * Enum used to determine if writing to the server was successful.
     * Labels: CLOSE, FAILED, SUCCESS
     */
    enum Written {
        CLOSE, FAILED, SUCCESS
    }

    // Streams we will use to communicate with the server
    private ObjectOutputStream socketOut;
    private ObjectInputStream socketIn;
    private Socket socket;

    public SimpleClient(){}

    public SimpleClient(Socket givenSocket){
        this.socket = givenSocket;
        getStreamsFromSocket(givenSocket);
    }

    /**
     * Get data from our user and send it to the server.
     *
     * @return Written: SUCCESS if the data was sent, CLOSE if user has requested to exit. FAILED otherwise.
     */
    public Written writeToServer(String requested){
        try {
            JSONObject json = CommandHandler.handleCommand(requested);

            if (json.get("message") != null) {
                System.out.println(json.get("message"));
                return Written.FAILED;
            }
            socketOut.writeObject(json);
        }
        catch (IOException e){
            System.out.println("Error sending object to server.");
            return Written.FAILED;
        }

        if (requested.equalsIgnoreCase("close"))
            return Written.CLOSE;

        return Written.SUCCESS;
    }

    /**
     * Read a response object from the server.
     *
     * @return Object: The response from the server, null if server is off.
     */
    public Object readFromServer(){
        
        try{
            if (this.socketIn == null){
                socketIn = new ObjectInputStream(this.socket.getInputStream());
            }
   
            System.out.println("Getting response..");
            Object serverResponse = socketIn.readObject();

            if(!CommandHandler.getRobot().applyState(serverResponse))
                System.out.print("Error with the sent request..");
            return serverResponse;
        }
        catch (IOException | ClassNotFoundException e){
            System.out.println("Error reading data from server.");
        }
        return null;
    }

    /**
     * Connect to server on the requested IP address. Socket is 5000.
     *
     * @param ipAddress: The IP address to attempt a connection with. 
     * @return Socket: The socket connecting to the server. Null if the connection failed.
     */
    public Socket connectToServer(String ipAddress){
        System.out.println("Connecting..");

        try{
            Socket mySocket = new Socket(ipAddress, 5000);

            System.out.println("Connected.");
            this.socket = mySocket;
            getStreamsFromSocket(mySocket);
            return mySocket;
        }
        catch (ConnectException e){
            System.out.println("Connection error. Server unavailable.");
            return null;
        }
        catch(IOException e){
            System.out.println("Even me I don't know. Shutting down lmao...");
            return null;
        }
    }

    /**
     * Extract and keep a reference of the socket's input and output streams
     * for communication with the server.
     *
     * @param mySocket: The socket to extract the input and output streams from.
     */
    private void getStreamsFromSocket(Socket mySocket){
        try{
            if(mySocket == null)
                throw new IOException();

            socketOut = new ObjectOutputStream(mySocket.getOutputStream());
            socketOut.flush();
            
        }
        catch (IOException e){
            System.out.println("Error getting streams from socket");
            closeClient();
        }
    }

    /**
     * Kill the client with an exit status of 1.
     */
    void closeClient(){
        closeSocket();
        System.exit(1);
    }

    /**
     * Close the socket to release the thread.
     */
    private void closeSocket(){
        try {
            socket.close();
        }
        catch (IOException e){
            System.out.println("Socket closed.");
        }
    }
}
