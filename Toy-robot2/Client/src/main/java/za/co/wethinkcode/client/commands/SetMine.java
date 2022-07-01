package za.co.wethinkcode.client.commands;

import org.json.simple.JSONObject;

import za.co.wethinkcode.client.Robot;

public class SetMine {
    /**
     * Generate a JSONObject for the mine command
     * @return JSONObject: the object with the command
     */
    @SuppressWarnings("unchecked")
    public static JSONObject mineJSON(Robot rbt){
        JSONObject result = new JSONObject();

        result.put("robot", rbt.getName());
        result.put("command", "mine");
        result.put("arguments", new String[]{});
        return result;
    }
}
