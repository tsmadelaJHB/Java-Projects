package za.co.wethinkcode.client.commands;

import org.json.simple.JSONObject;
import za.co.wethinkcode.client.Robot;

public class Movement extends CommandHandler{

    /**
     * Generate the movement request object
     * @param command: the full string from the user
     * @param robot: the robot object that has the name
     * @return JSONObject: the request object with a robot name, command,
     * and its arguments
     */
    @SuppressWarnings("unchecked")
    public static JSONObject movementJSON(String command, Robot robot){
        JSONObject result = new JSONObject();
        String[] splitCommand = command.split(" ");

        try {
            result.put("robot", robot.getName());
            result.put("command", splitCommand[0]);
            result.put("arguments", new String[]{checkNumber(splitCommand[1])});
        }catch (NullPointerException e) {
            generateErrorMessage(result, "Please launch a robot first.");
        }catch (IndexOutOfBoundsException e){
            generateErrorMessage(result, "Please enter '<forward or back> <number of steps>'.");
        }
        return result;
    }

    /**
     * Confirm that the string is a valid number
     * @param number: the string to check
     * @return: the string number if it is an integer
     * @throws IndexOutOfBoundsException: if number is not an integer, force
     * an error to be generated in movementJSON
     */
    private static String checkNumber(String number) throws IndexOutOfBoundsException{
        try {
            Integer.parseInt(number);
            return number;
        }catch (NumberFormatException e){
            throw new IndexOutOfBoundsException();
        }
    }
}
