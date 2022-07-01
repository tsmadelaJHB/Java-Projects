package za.co.wethinkcode.robotWorld.commands;

import java.util.HashMap;
import java.util.Map;

import za.co.wethinkcode.robotWorld.Robot;
import za.co.wethinkcode.robotWorld.World;

public class BackCommand extends Command{
    @Override
    public boolean execute(World target) {
        Robot robot = target.getRobot(getRobotName());
        Map<Object, Object> data = new HashMap<Object, Object>();
        Boolean dead = false;

        int nrSteps = 0;
        try {
            nrSteps = Integer.parseInt(getArgument());
        }catch (NumberFormatException e){
            data.put("Message", "Could not parse arguments");
            robot.setError(data);
            return true;
        }

        switch (target.updatePosition(nrSteps * -1, robot)){
            case SUCCESS:
                data.put("message", "Done");
                break;
            case FAILED_OUTSIDE_WORLD:
                data.put("message", "Edge");
                break;
            case FAILED_OBSTRUCTED:
                data.put("message", "Obstructed");
                break;
            case FAILED_FELL_IN_PIT:
                data.put("message", "Fell");
                dead = true;
                break;
            case SUCCESS_STEPPED_ON_MINE:
                data.put("message", "Mine");
                break;
            case FAILED_STEPPED_ON_MINE:
                data.put("message", "Mine");
                dead = true;
                break;
        }
        if(dead){
            robot.setStatus("DEAD");
            target.removeRobot(robot);
            return false;
        }
        robot.setResponse(data);
        return true;
    }

    public BackCommand(String robotName, String argument) {
        super(robotName, "back", argument);
    }
}
