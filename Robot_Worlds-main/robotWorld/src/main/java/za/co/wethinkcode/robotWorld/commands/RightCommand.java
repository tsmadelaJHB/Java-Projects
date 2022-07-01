package za.co.wethinkcode.robotWorld.commands;

import java.util.HashMap;
import java.util.Map;

import za.co.wethinkcode.robotWorld.Robot;
import za.co.wethinkcode.robotWorld.World;

public class RightCommand extends Command{
    @Override
    public boolean execute(World target) {
        Robot robot = target.getRobot(getRobotName());
        Map<Object, Object> data = new HashMap<Object, Object>();
        target.updateDirection(true, robot);
        data.put("message", "Done");
        robot.setResponse(data);
        return true;
    }

    public RightCommand(String robotName) {
        super(robotName, "right");
    }
}
