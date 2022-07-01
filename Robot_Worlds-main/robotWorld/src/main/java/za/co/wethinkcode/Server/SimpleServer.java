package za.co.wethinkcode.Server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import za.co.wethinkcode.Server.config.ConfigurationExceptions;
import za.co.wethinkcode.Server.util.Json;
import za.co.wethinkcode.robotWorld.CreateWorld;
import za.co.wethinkcode.robotWorld.Gun;
import za.co.wethinkcode.robotWorld.Robot;
import za.co.wethinkcode.robotWorld.World;
import za.co.wethinkcode.robotWorld.commands.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Driver Class for the Server
 * Accepts a connection from the client
 * Processes the request
 * Sets response into json
 * Closes the socket once done
 **/
public class SimpleServer implements Runnable {

    private static BufferedReader in;
    private static PrintStream out;
    private static World world;
    private static String clientMachine;

    public SimpleServer (Socket socket){

        try {

            world = CreateWorld.getInstance();
            System.out.println("Waiting for client...");

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream());
            clientMachine = socket.getInetAddress().getHostName();
            System.out.println("Connection from " + clientMachine);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void create(String name, int shield, int shots) {
        world.addRobot(new Robot(name, world.getRandomPosition(), shield, new Gun(shots)));
    }

    private MyRequestObject processRequest(String request) {
        JsonNode node = null; // create a jason node
        MyRequestObject myRequestObject;
        try {
            node = Json.parse(request);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConfigurationExceptions("Error parsing request");
        }
        try {
            myRequestObject = Json.fromJson(node, MyRequestObject.class);

        } catch (JsonProcessingException e) {
            throw new ConfigurationExceptions("Error creating request object");
        }
        return myRequestObject;
    }

    private static String setResponse(Robot robot) {

        MyResponseObject response = new MyResponseObject();
        response.setResults(robot.getResponse().get("result").toString());
        response.setData((Map<Object, Object>) robot.getResponseData());
        response.setState((Map<String, Object>) robot.getState());
        JsonNode node = Json.toJson(response);
        String json = "";
        try {
            json = Json.Stringify(node);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    private String setResponse() {
        MyResponseObject response = new MyResponseObject();
        Map<Object, Object> data = new HashMap<>();
        data.put("message", "OFF");
        response.setResults("ERROR");
        response.setData(data);
        JsonNode node = Json.toJson(response);
        String json = "";
        try {
            json = Json.Stringify(node);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public void run() {
        try {
            String messageFromClient;
            Command command = null;
            while((messageFromClient = in.readLine()) != null) {
                System.out.println(messageFromClient);
                MyRequestObject myRequestObject = processRequest(messageFromClient);

                String[] args = myRequestObject.getArguments();
                String stringArgs = "";
                boolean shouldContinue;
                for (int i = 0; i < args.length; i++) {
                    stringArgs += i == 0 ? args[i]: " " + args[i];
                }
                command = Command.create(myRequestObject.getRobot() + " " + myRequestObject.getCommand() + " " + stringArgs);
                shouldContinue = command.execute(world);
                try {
                    Robot robot = world.getRobot(myRequestObject.getRobot().toLowerCase());
                    out.println(setResponse(robot));
                }catch (NullPointerException e){
                    if (!shouldContinue)
                        out.println(setResponse());
                }
            }
        } catch(IOException ex) {
            System.out.println("Shutting down single client server");
            closeQuietly();
        }
    }

    private void closeQuietly() {
        try { in.close(); out.close();
        } catch(IOException ex) {}
    }
}
