package za.co.wethinkcode.server.commands;

import org.json.simple.JSONObject;
import za.co.wethinkcode.server.Robot;
import za.co.wethinkcode.server.SimpleServer;
import za.co.wethinkcode.server.world.World;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class CommandHandler {
    static List<String> userCommands = new ArrayList<>();
    static ArrayList<Robot> robotsInWorld = new ArrayList<>();
    static World w;
    /**
     * Call the correct function if a command is supported Return an error JSON if
     * it is not supported
     * 
     * @param command : the string for the command
     * @param args :    the list of arguments for the command
     * @param rbt :     the robot to run the command on
     * @param name :    the robot name (used by launch)
//     * @param world:    the current world the robot resides in
     * @return JSONObject: the response object for the command
     */
    public static JSONObject handleCommand(String command, String[] args, Robot rbt, String name) {
        w = SimpleServer.world;
        appendCommands(command, args);
        CommandArgument argument = new CommandArgument(name, w, args, rbt, command);
        if (!checkRobot(name) && !command.equals("launch")){
            System.out.println(name);
            return robotDoesNotExist();
        }
        if(isValid(command)) {
            return CommandTypes.valueOf(command.toUpperCase(Locale.ROOT)).runCommand(argument);
        }
        JSONObject error = new JSONObject();
        generateErrorMessage(error, "Unsupported command.");
        return error;
    }

    private static boolean isValid(String command) {
        for(CommandTypes e: CommandTypes.values()){
            if(e.name().equals(command.toUpperCase(Locale.ROOT))){
                return true;
            }
        }
        return false;
    }

    /**
     * A getter for all the robots in the world.
     *
     */
    public static ArrayList<Robot> getRobots() {
        return robotsInWorld;
    }

    /**
     * Generate an error message with the given string
     * 
     * @param result:  the JSONObject to place the error in
     * @param message: the message world.getObstacles()to place in the message
     */
    @SuppressWarnings("unchecked")
    static void generateErrorMessage(JSONObject result, String message) {
        result.clear();

        JSONObject data = new JSONObject();

        result.put("result", "ERROR");
        data.put("message", message);
        result.put("data", data);
    }

    /**
     * Create a JSONObject with the object's current state
     * 
     * @param dead:  boolean stating if the robot has been killed
     * @param robot: the robot whose data will be used to generate a state
     * @return JSONObject: the full state of the robot
     */
    @SuppressWarnings("unchecked")
    static JSONObject generateState(boolean dead, Robot robot) {
        if (dead || robot.getStatus().equalsIgnoreCase("dead")) {
            robotsInWorld.remove(robot);
        }

        JSONObject state = new JSONObject();
        List<int[]> arr = robot.getCo();
        int[] b = arr.get(0);

        int x = b[0];
        int y = b[1];


        state.put("position", List.of(x, y));
        state.put("direction", robot.getDirection());
        state.put("shields", robot.getShields());
        state.put("shots", robot.getShots());
        state.put("status", dead ? "DEAD" : robot.getStatus());

        return state;
    }

    /**
     * Append a selection of commands that the server gets from client
     * 
     * @param command the current command
     * @param args the arguments for the current command
     */
    public static void appendCommands(String command, String[] args) {
        String[] c = command.split(" ");
        switch (c[0]) {
        case "forward":
        case "back":
        case "sprint":
        case "turn":
            userCommands.add(command + " " + args[0]);
            break;
        }
    }

    /**
     * It checks the list of valid robots in the world anc checks if the name given is for a valid robot in the list
     * @param name The name of the robot you are checking
     * @return true if the robot is a valid robot false if it is not
     */
    public static Boolean checkRobot(String name){
//        if(robotsInWorld.size() > 0){
//            System.out.println(robotsInWorld.get(0).getName());
//        }
//        System.out.println(robotsInWorld);
        for (Robot temp: robotsInWorld){
            System.out.println(temp.getName());
            if (temp.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function to get the correct robot from the list of the robots in the world
     * @param name of the robot you want to get
     * @return return the robot with the given name from the list
     */
    public static Robot getRobot(String name){
        for (Robot temp: robotsInWorld){
            if (temp.getName().equals(name)) {
                return temp;
            }
        }
        return new Robot("dummy", -1,-1, w, 1);
    }

    public static JSONObject robotDoesNotExist(){
        JSONObject result = new JSONObject();
        String message = "Robot does not exist";
        generateErrorMessage(result, message);
        return result;
    }

}
