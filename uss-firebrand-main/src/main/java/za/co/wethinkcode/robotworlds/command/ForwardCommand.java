package za.co.wethinkcode.robotworlds.command;

import com.google.gson.JsonObject;
import za.co.wethinkcode.robotworlds.IWorldNavigator;
import za.co.wethinkcode.robotworlds.Response;
import za.co.wethinkcode.robotworlds.Robot;

public class ForwardCommand extends Command {
    @Override
    public boolean execute(Robot target) {
        JsonObject data = new JsonObject();
        Response response = new Response();
        int nrSteps = Integer.parseInt(getArgument());
        if (target.updatePosition(nrSteps) == IWorldNavigator.UpdateResponse.SUCCESS){
            target.setStatus("Moved forward by "+nrSteps+" steps.");
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

    public ForwardCommand(String argument) {
        super("forward", argument);
    }
}
