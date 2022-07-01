package za.co.wethinkcode.Server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import za.co.wethinkcode.Server.config.ConfigurationExceptions;
import za.co.wethinkcode.Server.util.Json;
import za.co.wethinkcode.robotWorld.RobotType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Connects to the server
 * Writes a Json String
 * Sends message to the server
 * Process response from the server
 * exits the connection once done
 **/

public class SimpleClient {

    public static String hostname = "localhost";
    public static int PORT = 5000;
    public static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        boolean create = true;
        String name = "";
        int attempts =  10;

        while(attempts > 0) {
            try (
                    Socket socket = new Socket(hostname, PORT);
                    PrintStream out = new PrintStream(socket.getOutputStream());
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            )

            {
                if(create) {
                    name = getInput("What do you want to name your robot?");
                    out.println(setRequest(name));
                    out.flush();
                    String messageFromServer = in.readLine();
                    readResponse(processResponse(messageFromServer));
                    create = false;
                } else {

                    String command = getInput("What must I do next?");
                    out.println(setRequest(name, command));
                    out.flush();
                    String messageFromServer = in.readLine();
                    if(!readResponse(processResponse(messageFromServer)))
                        break;
                }
            } catch (IOException e) {
                System.out.println("I/O Error: " + e.getMessage());
                attempts -= 1;
            }
        }
        System.out.println("Shutting down...");
    }

    private static String setRequest(String name) {
        String type = getInput("Pick a robot type: Gunner, Sniper, Ranger or Infant");

        MyRequestObject request = new MyRequestObject();
        request.setRobot(name);
        request.setCommand("launch");
        request.setArguments(SetRobotType(type));
        return createJsonString(request);
    }

    private static String[] SetRobotType(String type){
        RobotType robotType = new RobotType(type);
        return new String[]{robotType.getName(), robotType.getShields(), robotType.getShots()};
    }

    private static String setRequest(String name, String command) {

        MyRequestObject request = new MyRequestObject();
        String[] newArgs;
        request.setRobot(name);
        request.setCommand(command.split(" ")[0]);
        String[] args = command.split(" ");
        if (args.length > 1)
            newArgs = new String[args.length - 1];
        else
            newArgs = new String[] {};

        for (int i = 1; i < args.length; i++) {
            newArgs[i - 1] = args[i];
        }
        request.setArguments(newArgs);
        return createJsonString(request);
    }

    private static String createJsonString(MyRequestObject request) {
        JsonNode node = Json.toJson(request);
        String json = "";
        try {
            json = Json.Stringify(node);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    private static String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();

        while(input.isBlank()) {
            System.out.println(prompt + "\n");
            input = scanner.nextLine();
        }
        return input;
    }
    private static MyResponseObject processResponse(String response) {
        JsonNode node = null; // create a jason node
        MyResponseObject myResponseObject;
        try {
            node = Json.parse(response);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ConfigurationExceptions("Error parsing response");
        }
        try {
            myResponseObject = Json.fromJson(node, MyResponseObject.class);

        } catch (JsonProcessingException e) {
            throw new ConfigurationExceptions("Error creating response object");
        }
        return myResponseObject;
    }

    private static boolean readResponse(MyResponseObject response) {
        if(response.getResult() != null && response.getResult().equals("OK")) {
            if (response.getData() != null) {
                Map<Object, Object> data = response.getData();
                if (data.get("message") != null) {
                    data.forEach((key, value) -> {
                        System.out.println(key + ": "+ value);
                    });
                } else if (data.get("objects") != null) {
                    List<Map<String,Object>> objects = (List<Map<String, Object>>) data.get("objects");
                    for(Map<String,Object> object:objects){
                        object.forEach((key, value) -> {
                            System.out.println(key + ": "+ value);
                        });
                    }
                }
            }
        } else if (response.getResult() != null && response.getResult().equals("ERROR")) {
            if (response.getData().get("message").equals("OFF"))
                return false;
            else {
                System.out.println("ERROR: " + response.getData().get("message"));
            }
        }

        if (response.getState() != null) {
            Map<String, Object> state = response.getState();
            state.forEach((key, value) -> {
                System.out.println(key + ": "+ value);
            });
        }
        return true;
    }
}
