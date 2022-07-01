package za.co.wethinkcode.client.commands;

import org.json.simple.JSONObject;

public class Close {

    /**
     * Generate a JSONObject for the close command
     * @return JSONObject: the object with the command
     */
    @SuppressWarnings("unchecked")
    public static JSONObject closeJSON(){
        JSONObject result = new JSONObject();

        result.put("command", "close");
        return result;
    }
}
