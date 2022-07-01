package za.co.wethinkcode.client.commands;

import org.json.simple.JSONObject;

import za.co.wethinkcode.client.Robot;

public class State {
    /**
     * Generate a JSONObject for the state command
     * @return JSONObject: the object with the command
     */
    @SuppressWarnings("unchecked")
    public static JSONObject stateJSON(Robot robot){
        JSONObject result = new JSONObject();

        result.put("robot", robot.getName());
        result.put("command", "state");
        result.put("arguments", new String[]{});
        return result;
    }
}
