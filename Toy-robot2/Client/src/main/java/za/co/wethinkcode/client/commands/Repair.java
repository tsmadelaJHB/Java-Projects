package za.co.wethinkcode.client.commands;

import org.json.simple.JSONObject;

import za.co.wethinkcode.client.Robot;

public class Repair {
    /**
     * Generate a JSONObject for the repair command
     * @return JSONObject: the object with the command
     */
    @SuppressWarnings("unchecked")
    public static JSONObject repairJSON(Robot rbt){
        JSONObject result = new JSONObject();

        result.put("robot", rbt.getName());
        result.put("command", "repair");
        result.put("arguments", new String[]{});
        return result;
    }
}
