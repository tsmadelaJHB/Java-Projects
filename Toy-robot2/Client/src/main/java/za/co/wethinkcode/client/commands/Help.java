package za.co.wethinkcode.client.commands;

import org.json.simple.JSONObject;

public class Help extends CommandHandler{

    /**
     * Return the help JSON as an error to get it printed immediately
     * @return JSONObject: the JSON with a help string
     */
    public static JSONObject helpJSON(){
        JSONObject helpString = new JSONObject();

        generateErrorMessage(helpString, helpText());
        return helpString;
    }

    /**
     * Create the help string
     * @return String: the full help string
     */
    private static String helpText(){
        String text = "These are the supported commands:\n" +
                "Launch: Use <launch> <robot type> <robot name> to create a new robot\n" +
                "Forward: Use <forward> <number of steps> to move the robot forward\n" +
                "Back: Use <back> <number of steps> to move the robot back\n" +
                "Help: Use <help> to display the list of supported commands.\n" +
                "Close: Use <close> to shutdown the client.\n";
        return text;
    }
}
