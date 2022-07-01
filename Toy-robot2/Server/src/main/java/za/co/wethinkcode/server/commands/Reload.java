package za.co.wethinkcode.server.commands;

import org.json.simple.JSONObject;

import za.co.wethinkcode.server.Robot;

@SuppressWarnings("unchecked")
public class Reload extends CommandHandler {
    public static JSONObject reloadJSON(CommandArgument arg) {
        Robot rbt = getRobot(arg.getName());
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        rbt.reload();

        result.put("result", "OK");
        data.put("message", "Done");
        result.put("state", generateState(false, rbt));
        return result;
    }
}