package za.co.wethinkcode.server.commands;

import org.json.simple.JSONObject;

import za.co.wethinkcode.server.Robot;

public class Close extends CommandHandler{

    /**
     * Generate a JSONObject for the close command
     * @param arg the command object with all the relevant command data
     * @return JSONObject: the filled object with a result, 
     * message, data, and state
     */
    @SuppressWarnings("unchecked")
    public static JSONObject closeJSON(CommandArgument arg){
        Robot rbt = getRobot(arg.getName());
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject(); 

        result.put("result", "OK");

        data.put("message", "Goodnight.");
        result.put("data", data);
        result.put("state", generateState(true, rbt));
        
        robotsInWorld.remove(rbt);
        System.out.println(result);
        return result;
    }
}
