package za.co.wethinkcode.server.commands;

import org.json.simple.JSONObject;

import za.co.wethinkcode.server.Robot;

@SuppressWarnings("unchecked")
public class SetMine extends CommandHandler {
    public static JSONObject setMineJsonObject(CommandArgument arg) {
        Robot rbt = getRobot(arg.getName());
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        rbt.placeMine();

        result.put("result", "OK");
        data.put("message", "Done");
        result.put("state", generateState(false, rbt));
        return result;
    }
}
