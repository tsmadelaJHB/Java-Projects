package za.co.wethinkcode.client.commands;

import org.json.simple.JSONObject;

public class Launch extends CommandHandler{

    /**
     * Generate a robot launch request object
     * @param command: the full string from the user
     * @return JSONObject: the filled object with a robot name, command,
     * and its argument
     */
    @SuppressWarnings("unchecked")
    public static JSONObject launchJSON(String command){
        JSONObject result = new JSONObject();
        String[] splitCommand = command.split(" ");

        try {
            if(!checkCommand(splitCommand))
                throw new IndexOutOfBoundsException();

            result.put("robot", splitCommand[2]);
            result.put("command", splitCommand[0]);
            result.put("arguments", new String[]{splitCommand[1], "5", "5"});
        }catch (IndexOutOfBoundsException e){
            generateErrorMessage(result, "Please enter 'launch <robot type> <robot name>'.");
        }
        return result;
    }

    private static boolean checkCommand(String[] splitCommand){
        if (!splitCommand[0].equals("launch"))
            return false;
        /*
        here, check if the second command is a robot type we support
        if not, return false
        */
        return true;
    }
}
