package za.co.wethinkcode.robotWorld.commands;

import java.util.HashMap;
import java.util.Map;

import za.co.wethinkcode.robotWorld.Robot;
import za.co.wethinkcode.robotWorld.World;

public class LeftCommand extends Command{
    @Override
    public boolean execute(World target) {
        Robot robot = target.getRobot(getRobotName());
        Map<Object, Object> data = new HashMap<Object, Object>();
        target.updateDirection(false, robot);
        data.put("message", "Done");
        robot.setResponse(data);
        return true;
    }

    public LeftCommand(String robotName) {
        super(robotName, "left");
    }
}
