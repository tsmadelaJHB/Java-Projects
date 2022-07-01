package za.co.wethinkcode.client.commands;

import org.json.simple.JSONObject;
import za.co.wethinkcode.client.Robot;

public class Turn extends CommandHandler {

    /**
     * Generate a turn request object
     * @param command: the full string from the user
     * @param robot: the robot object
     * @return JSONObject: the request object containing the robot name,
     * the command, and its arguments
     */
    @SuppressWarnings("unchecked")
    public static JSONObject turnJSON(String command, Robot robot){
        JSONObject result = new JSONObject();
        String[] splitCommand = command.split(" ");

        try {
            if (!leftRight(splitCommand[1]))
                throw new IndexOutOfBoundsException();

            result.put("robot", robot.getName());
            result.put("command", splitCommand[0]);
            result.put("arguments", new String[]{splitCommand[1]});
        }catch (NullPointerException e) {
            generateErrorMessage(result, "Please launch a robot first.");
        }catch (IndexOutOfBoundsException e){
            generateErrorMessage(result, "Please enter 'turn <left or right>'.");
        }
        return result;
    }

    /**
     * Confirm if turn is a supported direction
     * @param turn: the string to check
     * @return boolean: true if supported (left or right), false otherwise
     */
    private static boolean leftRight(String turn){
        if (turn.equalsIgnoreCase("left"))
            return true;
        if (turn.equalsIgnoreCase("right"))
            return true;
        return false;
    }
}
