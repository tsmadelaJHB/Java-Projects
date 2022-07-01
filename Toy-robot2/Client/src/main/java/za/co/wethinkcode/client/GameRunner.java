package za.co.wethinkcode.client;

import org.json.simple.JSONObject;

import za.co.wethinkcode.client.SimpleClient.Written;
import za.co.wethinkcode.client.UserInterface.userInterface;

import java.io.IOException;
import java.lang.System;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameRunner {
    public static List<String> userCommands = new ArrayList<>();
    public static List<String> command0 = new ArrayList<>();

    private static SimpleClient connect = new SimpleClient();
    private static Display display = new Display();
    private static String robotName;
    private static Scanner scanner = new Scanner(System.in);

    // private static boolean updating = false;
    // private static boolean runningCommand = false;

    public static void main(String[] args) {
        if (args.length == 0) {
            getRobotName();
            List<String> availableWorlds = getWorlds();
            displayWorlds(availableWorlds);
            String chosenWorld = getUserWorld(availableWorlds);
            connectToWorld(chosenWorld);

            loop();
        } else {
            new userInterface();
        }
    }

    /**
     * Loop that runs the client interaction.
     *
     * - Get a response from the server - Display the updated robot state to the
     * user - Ask for a new command - Send a request to the server - Repeat
     */
    private static void loop(){
        // The loop works in conjunction with the Thread
        // They each take turns writing to the Server,
        // The loop first checks if the Thread is not writing to the server
        // If not it writes else it wait until it finished
        // The two variables [Updating and running commands] are used to coordinate this process

        //Start Continues Update Thread
        // ContinuesUpdate update = new ContinuesUpdate(connect);
        // Thread startUpdate = new Thread(update);
        // startUpdate.start();

        Written outcome = Written.SUCCESS;

         while(true){
            Object object = connect.readFromServer();

            if (object != null){
                displayResponse(object);
            }
            if (outcome == Written.CLOSE)
                break;

            String userCommand = getUserCommand();
            // while (ContinuesUpdate.updating){}
            // ContinuesUpdate.runningCommand = true;

            outcome = connect.writeToServer(userCommand);
            // ContinuesUpdate.runningCommand = false;

            while (outcome == Written.FAILED) {
                userCommand = getUserCommand();
                // while (ContinuesUpdate.updating){}
                // ContinuesUpdate.runningCommand = true;

                outcome = connect.writeToServer(userCommand);
                // ContinuesUpdate.runningCommand = false;
            }
        }
        System.out.println("Closing client..");
    }

    // /**
    // * Display the robot state to the user after a server response
    // * @param response: the object to read the new state from
    // */
    // private static void displayResponse(Object response){
    // JSONObject responseJSON = (JSONObject) response;
    // display.update(responseJSON);
    // display.displayGame();
    // }

    /**
     * Ask the user for a command and return it
     * 
     * @return String: the entered user string
     */
    private static String getUserCommand() {
        while (true) {
            System.out.println("What command would you like to run next?");
            String input = scanner.nextLine().trim();

            if (input.length() > 0) {
                System.out.println();
                appendCommands(input);
                return input;
            }
            System.out.println("Empty command entered.");
        }
    }

    /**
     * Prompt the user for a robot name and store it
     */
    private static void getRobotName() {
        robotName = "";
        System.out.println("What would you like to name the robot");

        while (robotName.strip().length() < 1) {
            robotName = scanner.nextLine();
        }
    }

    // /**
    // * Collect a list of available IP addresses
    // * @return List<String>: the list of IP addresses found
    // */
    // private static List<String> getWorlds(){
    // AvailableWorlds worlds = new AvailableWorlds();
    // List<String> worldsList = worlds.getWorlds();
    // return worldsList;
    // }

    // /**
    // * Print the list of available IPs to the user
    // * @param worldsList: the list of IPs found on the network
    // */
    // private static void displayWorlds(List<String> worldsList){
    // if (worldsList.size() > 0){
    // System.out.println("Select world from the following: (return number)");
    // int position = 1;
    // for (String i: worldsList)
    // {System.out.println(position+". "+i);position++;}}
    // else System.out.println("No available worlds.");
    // }

    // /**
    // * Prompt the user to select one world from the list given
    // * @param worldsList: the list of worlds found
    // * @return String: the selected world's IP address
    // */
    // private static String getUserWorld(List<String> worldsList){
    // while (true) {
    // try {
    // int number = scanner.nextInt();
    // scanner.nextLine();
    // if (number > 0 && number <= worldsList.size())
    // return worldsList.get(number - 1);
    // System.out.println("Please input valid selection");
    // } catch (Exception e) {
    // System.out.println("Please input valid selection");
    // }
    // }
    // }

    /**
     * Attempt to connect to the selected world
     * 
     * @param world: the IP address of the selected world
     * @return boolean: true if connected, false otherwise
     */
    public static String[] selectedWorld;

    private static boolean connectToWorld(String world) {
        // selectedWorld = world.split(" ");

        if (connect.connectToServer(world) != null) {
            System.out.println("Connected to server\nLaunch robot? y/n");

            if (scanner.nextLine().equalsIgnoreCase("y")) {
                connect.writeToServer(String.format("launch GunSlinger %s", robotName));

            }
        } else {
            System.out.println("Unable to connect");
            return false;
        }
        return true;
    }

    /**
     * Loop that runs the client interaction.
     *
     * - Get a response from the server - Display the updated robot state to the
     * user - Ask for a new command - Send a request to the server - Repeat
     */
    public static void loop1() {
        Object object = connect.readFromServer();
        displayResponse(object);
    }

    public static void loop2() {
        String comm = UserCommand();
        connect.writeToServer(comm);
    }

    /**
     * Display the robot state to the user after a server response
     * 
     * @param response: the object to read the new state from
     */
    public static void displayResponse(Object response) {
        JSONObject responseJSON = (JSONObject) response;
        display.update(responseJSON);
        display.displayGame();
    }

    /**
     * Ask the user for a command and return it
     * 
     * @return String: the entered user string
     */

    public static String input;

    public static String UserCommand() {
        System.out.println("What command would you like to run next?");

        input = command0.get(0);

        if (input.length() > 0) {
            System.out.println();

            appendCommands(input);
            return input;
        }
        return input;
    }

    public static void appendCommands(String command) {
        String[] c = command.split(" ");
        switch (c[0]) {
        case "forward":
        case "back":
        case "sprint":
            userCommands.add(command);
            break;
        }
        switch (command) {
        case "turn right":
        case "turn left":
            userCommands.add(command);
            break;
        }
    }

    /**
     * Prompt the user for a robot name and store it
     */
    public static void RobotName() {
        System.out.println("What would you like to name the robot");
        robotName = userInterface.robotName();
    }

    /**
     * Collect a list of available IP addresses
     * 
     * @return List<String>: the list of IP addresses found
     */
    public static List<String> getWorlds() {
        // AvailableWorlds worlds = new AvailableWorlds();
        try {
            List<String> worldsList = DynamicConnection.IPaddress();
            return worldsList;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Print the list of available IPs to the user
     * 
     * @param worldsList: the list of IPs found on the network
     */
    public static void displayWorlds(List<String> worldsList) {
        if (worldsList.size() > 0) {
            System.out.println("Select world from the following: (return number)");
            int position = 1;
            for (String i : worldsList) {
                System.out.println(position + ". " + i);
                position++;
            }
        } else
            System.out.println("No available worlds.");
    }

    /**
     * Prompt the user to select one world from the list given
     * 
     * @param worldsList: the list of worlds found
     * @return String: the selected world's IP address
     */
    public static String getUserWorld(List<String> worldsList) {
        // Robot robot;
        while (true) {
            try {
                int number = scanner.nextInt();
                scanner.nextLine();

                if (number > 0 && number <= worldsList.size()) {
                    System.out.println(number);

                    return worldsList.get(number - 1);
                }
                System.out.println("Please input valid selection");
            } catch (Exception e) {
                System.out.println("Please input valid selection");
            }
        }
    }

    /**
     * Attempt to connect to the selected world
     * 
     * @param world: the IP address of the selected world
     * @return boolean: true if connected, false otherwise
     */
    public static boolean connect2World(String world, String response) {
        selectedWorld = world.split(" ");

        if (connect.connectToServer(selectedWorld[1]) != null) {
            System.out.println("Connected to server\nLaunch robot? y/n");

            if (response.equalsIgnoreCase("y")) {
                connect.writeToServer(String.format("launch %s %s",userInterface.playerDetails.get(1), robotName));
                System.out.println(userInterface.playerDetails.get(1)+" "+robotName);
            }
        } else {
            System.out.println("Unable to connect");
            return false;
        }
        return true;
    }
}