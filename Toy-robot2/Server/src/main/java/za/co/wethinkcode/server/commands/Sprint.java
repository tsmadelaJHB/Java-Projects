package za.co.wethinkcode.server.commands;

import org.json.simple.JSONObject;

import za.co.wethinkcode.server.Robot;

public class Sprint extends CommandHandler{
    /**
     * Move the robot and generate a response JSONObject
     * @param sprint: boolean saying if the robot is sprinting forward
     * @param args: the distance to move
     * @return JSONObject: the filled object with a result,
     * data, and state
     */
    @SuppressWarnings("unchecked")
    public static JSONObject sprintJSON(boolean sprint, CommandArgument arg){
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        Robot rbt = getRobot(arg.getName());
        int nrSteps = Integer.parseInt(arg.getArgs()[0]);
        try {
            for (int i = 1; i <= nrSteps; i++) {
                int steps = sprint ? i : -1;
                rbt.move(steps);
            }

            data.put("message", "Done");

            result.put("result", "OK");
            result.put("data", data);
            result.put("state", generateState(false, rbt));
        }catch (IndexOutOfBoundsException | NumberFormatException e){
            generateErrorMessage(result, "Could not parse arguments.");
        }
        return result;
    }
}
