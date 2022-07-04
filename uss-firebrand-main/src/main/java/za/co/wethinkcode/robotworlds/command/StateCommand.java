package za.co.wethinkcode.robotworlds.command;

import za.co.wethinkcode.robotworlds.Response;
import za.co.wethinkcode.robotworlds.Robot;


public class StateCommand extends Command{
    public StateCommand() {
        super("state");
    }

    @Override
    public boolean execute(Robot target) {
        Response response = new Response();
        response.setResult("OK");
        response.setState(target);
        target.setServerResponse(response);
        return true;
    }
}
