package za.co.wethinkcode.server.commands;

import org.json.simple.JSONObject;

import za.co.wethinkcode.server.Robot;

import java.util.List;

public class Movement extends CommandHandler{
    
    /**
     * Move the robot and generate a response JSONObject
     * @param forward: boolean saying if the robot is moving forward
     * @param args: the distance to move
     * @return JSONObject: the filled object with a result, 
     * data, and state
     */
    @SuppressWarnings("unchecked")
    public static JSONObject movementJSON(boolean forward, CommandArgument argument){
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        Robot rbt = getRobot(argument.getName());
        try {
            int multiplier = forward ? 1 : -1;
            int steps = multiplier * Integer.parseInt(argument.getArgs()[0]);
            rbt.move(steps);
            int[] arr = rbt.getPosition();
            int x = arr[0];
            int y = arr[1];
            data.put("message", rbt.getLastAttempt());
            data.put("position", List.of(x, y));
            System.out.println("My data"+data);

            result.put("result", "OK");
            result.put("data", data);
            result.put("state", generateState(rbt.getLastAttempt().equalsIgnoreCase("fell"), rbt));
        }catch (IndexOutOfBoundsException | NumberFormatException e){
            generateErrorMessage(result, "Could not parse arguments.");
        }
        return result;
    }
}
