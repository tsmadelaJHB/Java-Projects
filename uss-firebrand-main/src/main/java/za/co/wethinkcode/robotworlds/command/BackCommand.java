package za.co.wethinkcode.robotworlds.command;

import com.google.gson.JsonObject;
import za.co.wethinkcode.robotworlds.IWorldNavigator;
import za.co.wethinkcode.robotworlds.Response;
import za.co.wethinkcode.robotworlds.Robot;

public class BackCommand extends Command {
    /** This method moves the Robot back negatively on the Y axis
     * @param target: Input from Robot as a command to move Backwards
     * @return true: Returns true when the Backwards movement has been executed
     */
    @Override
    public boolean execute(Robot target) {
            JsonObject data = new JsonObject();
            Response response = new Response();
            int step = Integer.parseInt(getArgument());
            int nrSteps = -Integer.parseInt(getArgument());
            if (target.updatePosition(nrSteps) == IWorldNavigator.UpdateResponse.SUCCESS){
                target.setStatus("Moved back by "+step+" steps.");
                response.setResult("OK");
                data.addProperty("message","done");
                response.setData("message",data);
                response.setState(target);
                target.setServerResponse(response);
            } else if (target.updatePosition(nrSteps) == IWorldNavigator.UpdateResponse.FAILED_OBSTRUCTED){
                target.setStatus("Sorry, there is an obstacle in the way.");
                response.setResult("OK");
                data.addProperty("message","obstructed");
                response.setData("message",data);
                response.setState(target);
                target.setServerResponse(response);
            }else if (target.updatePosition(nrSteps) == IWorldNavigator.UpdateResponse.FAILED_FELL_INTO_PIT){
                target.setStatus("fell into pit");
                response.setResult("OK");
                data.addProperty("message","fell");
                response.setData("message",data);
                response.setState(target);
                target.setServerResponse(response);
            }
            else {
                target.setStatus("Sorry, I cannot go outside my safe zone.");
                response.setResult("OK");
                data.addProperty("message","edge of the world");
                response.setData("message",data);
                response.setState(target);
                target.setServerResponse(response);
            }
            return true;
        }

    public BackCommand(String argument) {
        super("back", argument);
    }
}
