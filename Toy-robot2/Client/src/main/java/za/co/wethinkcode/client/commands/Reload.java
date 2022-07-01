package za.co.wethinkcode.client.commands;

import org.json.simple.JSONObject;

import za.co.wethinkcode.client.Robot;

public class Reload {
    /**
     * Generate a JSONObject for the reload command
     * @return JSONObject: the object with the command
     */
    @SuppressWarnings("unchecked")
    public static JSONObject reloadJSON(Robot rbt){
        JSONObject result = new JSONObject();

        result.put("robot", rbt.getName());
        result.put("command", "reload");
        result.put("arguments", new String[]{});
        return result;
    }
}