package za.co.wethinkcode.client.commands;

import org.json.simple.JSONObject;

import za.co.wethinkcode.client.Robot;

public class Look {
    /**
     * Generate a JSONObject for the look command
     * @return JSONObject: the object with the command
     */
    @SuppressWarnings("unchecked")
    public static JSONObject lookJSON(Robot rbt){
        JSONObject result = new JSONObject();

        result.put("robot", rbt.getName());
        result.put("command", "look");
        result.put("arguments", new String[]{});
        return result;
    }
}
