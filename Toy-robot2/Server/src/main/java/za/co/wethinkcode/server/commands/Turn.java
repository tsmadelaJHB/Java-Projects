package za.co.wethinkcode.server.commands;
import org.json.simple.JSONObject;

import za.co.wethinkcode.server.Robot;

public class Turn extends CommandHandler {

    /**
     * Turn the robot 90 degrees in the desired direction
     * and generate a response JSONObject
     * @param args: the direction to turn 
     * @param name: the name of the robot to turn
     * @return JSONObject: the filled object with a result, 
     * data, and state
     */
    @SuppressWarnings("unchecked")
    public static JSONObject turnJSON(CommandArgument arg){
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        Robot rbt = getRobot(arg.getName());
        System.out.println(rbt.getName());
        try {
            if (!(arg.getArgs()[0].equalsIgnoreCase("left") || arg.getArgs()[0].equalsIgnoreCase("right")))
                throw new IndexOutOfBoundsException();

            rbt.turnRobot(arg.getArgs()[0].equalsIgnoreCase("right"));
            data.put("message", "Done");

            result.put("result", "OK");
            result.put("data", data);
            result.put("state", generateState(false, rbt));
        }catch (IndexOutOfBoundsException e){
            generateErrorMessage(result, "Could not parse arguments.");
        }
        return result;
    }
}
