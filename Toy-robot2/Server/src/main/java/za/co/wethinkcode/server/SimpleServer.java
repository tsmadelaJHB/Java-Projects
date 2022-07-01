package za.co.wethinkcode.server;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import za.co.wethinkcode.server.commands.CommandHandler;
import za.co.wethinkcode.server.serverCommands.PurgeCommand;
import za.co.wethinkcode.server.world.World;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SimpleServer implements Runnable{
    private BufferedReader socketIn;
    private PrintStream socketOut;
    private final Socket mySocket;
    public static World world = null;
    private Robot robot;
    private final Integer size;

    /**
     * A server instance that will communicate to a client
     * through a socket.
     *
     * @param newConnection : The socket to use for communication
     * @param size          : Size of the world example 2x2
     */
    public SimpleServer(Socket newConnection, World world, int size) {
        this.mySocket = newConnection;
        this.world = world;
        this.robot = new Robot("dummy", -1, -1, this.world, size);
        this.size = size;
        extractSocketStreams();
    }

    /**
     * Extract and keep a reference of the socket's input and output streams
     * for communication with the server.

     */
    private void extractSocketStreams(){
        try {
            if (this.mySocket == null){
                throw new IOException();
            }

            this.socketOut = new PrintStream(this.mySocket.getOutputStream());
            this.socketOut.flush();
        }
        catch (IOException e){
            System.out.println("Connection failed.");
            closeServer();
            return;
        }
        System.out.println("Server Connected.");
    }

    /**
     * Auto-called: Run the loop that gets data from the client and
     * sends a response.
     */
    public void run() {
        String inputFromClient;

        while (!mySocket.isClosed()) {
            inputFromClient = readFromClient();

            if (inputFromClient == null)
                break;

            if (respondToClient(inputFromClient))
                break;
        }
        new PurgeCommand("all");

    }

    /**
     * Read a JSON Object from the client.
     *
     * @return JSONObject: The object from the client, null if socket is closed.
     */
    public String readFromClient(){
        try {
            if (this.socketIn == null){
                this.socketIn = new BufferedReader(new InputStreamReader(this.mySocket.getInputStream()));
            }
            JSONParser parser = new JSONParser();
            String message = socketIn.readLine();
            System.out.println(message);
            if(!(message == null)) {
                JSONObject request = (JSONObject) parser.parse(message);
                return String.valueOf(request);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
//        catch (IOException | ClassNotFoundException | ParseException e){
//            System.out.println("Problem reading data from client. Closing server..");
//            closeServer();
//        }
        return null;
    }

    /**
     * Kill the server with an exit status of 1.
     */
    private void closeServer(){
        this.closeSocket();
        System.exit(1);
    }

    /**
     * Send a JSON Object to the client.
     *
     * @param inputFromClient: The last message we received from the client.
     */
    boolean respondToClient(String inputFromClient) {

        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject inputFromClientJSON = (JSONObject) jsonParser.parse(inputFromClient);
            JSONObject json;
            String command = (String) inputFromClientJSON.get("command");
            String name = (String) inputFromClientJSON.get("robot");
            JSONArray argArr = (JSONArray) inputFromClientJSON.get("arguments");
            List<String> argList = new ArrayList<>();
            HelpingRespond(argArr,argList);

            String[] arguments = argList.toArray(new String[0]);
            json = CommandHandler.handleCommand(command, arguments, robot, name);
            /*
            Attaching world to the response
            */
            HelpingRespond2(json);

            String message = json.toString();
            System.out.println(">>>> "+message);
            socketOut.println(message);
            socketOut.flush();
            if (command.equalsIgnoreCase("close"))
                return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Adding the arguments in the list
     * @param argArr JSONArray
     * @param argList Argument list
     */
    public void HelpingRespond(JSONArray argArr ,List<String> argList){
        for (Object o : argArr) {
            argList.add(o.toString());
        }
    }

    /**
     * placing obstacles and pits if there are available
     * @param json object
     */
    public void HelpingRespond2(JSONObject json){
        if(world != null){
            List<int[]> arr = robot.findObstacles();
            if (arr.size() != 0) {
                int[] b = arr.get(0);
                int x = b[0];
                int y = b[1];
                json.put("obstacles", List.of(x, y));
                json.put("pits", robot.findPits());
            }
        }
    }

    /**
     * Close the socket to release the thread.
     */
    private void closeSocket(){
        try {
            this.mySocket.close();
        }
        catch (IOException e){
            System.out.println("Socket closed.");
        }
    }

    public Robot getRobot(){
        return this.robot;
    }
}
