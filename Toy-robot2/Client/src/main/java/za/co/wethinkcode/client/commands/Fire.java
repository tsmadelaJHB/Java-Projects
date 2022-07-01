package za.co.wethinkcode.client.commands;

import org.json.simple.JSONObject;

import za.co.wethinkcode.client.Robot;

public class Fire {
    /**
     * Generate a JSONObject for the fire command
     * @return JSONObject: the object with the command
     */
    @SuppressWarnings("unchecked")
    public static JSONObject fireJSON(Robot rbt){
        JSONObject result = new JSONObject();

        result.put("robot", rbt.getName());
        result.put("command", "fire");
        result.put("arguments", new String[]{});
        return result;
    }
}
