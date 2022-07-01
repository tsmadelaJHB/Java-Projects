package za.co.wethinkcode.client.commands;

import org.json.simple.JSONObject;
import za.co.wethinkcode.client.Robot;

public class CommandHandler{

    private static Robot robot;

    /**
     * Handle any supported commands. Return an error message otherwise
     * @param command: The string from the user for the command
     * @return JSONObject: The request object to send to the server
     */
    public static JSONObject handleCommand(String command){
        String[] splitCommand = command.split(" ");

        switch (splitCommand[0]){
            case "launch":
                JSONObject result = Launch.launchJSON(command);

                if (result.get("robot") != null)
                    robot = new Robot((String) result.get("robot"));
                return result;
            case "close":
                return Close.closeJSON();
            case "forward":
            case "back":
                return Movement.movementJSON(command, robot);
            case "turn":
                return Turn.turnJSON(command, robot);
            case "help":
                return Help.helpJSON();
            case "sprint":
                return Sprint.sprintJSON(command,robot);
            case "replay":
                return Replay.replayJSON(command, robot);
            case "look":
                return Look.lookJSON(robot);
            case "state":
                return State.stateJSON(robot);
            case "fire":
                return Fire.fireJSON(robot);
            case "mine":
                return SetMine.mineJSON(robot);
            case "reload":
                return Reload.reloadJSON(robot);
            case "repair":
                return Repair.repairJSON(robot);
        }

        JSONObject error = new JSONObject();
        generateErrorMessage(error, "Unsupported command. Use <help> to see supported commands.");
        return error;
    }

    /**
     * Generate an error message in result with the given message
     * @param result: the JSONObject to occupy with the error message
     * @param message: the string to place as the error
     */
    @SuppressWarnings("unchecked")
    static void generateErrorMessage(JSONObject result, String message){
        result.clear();

        result.put("message", message);
    }

    /**
     * Return the robot object active in the world
     * @return Robot: the robot object
     */
    public static Robot getRobot(){
        return robot;
    }

    /**
     * Set the current robot to the given robot
     * 
     * @param rbt: the new robot instance
     */
    public static void setRobot(Robot rbt){
        robot = rbt;
    }
}
