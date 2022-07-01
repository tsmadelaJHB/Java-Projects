package za.co.wethinkcode.robotWorld.commands;

import za.co.wethinkcode.robotWorld.Robot;
import za.co.wethinkcode.robotWorld.World;

import java.util.HashMap;
import java.util.Map;

public class IllegalCommand extends Command{

    @Override
    public boolean execute(World target) {
        Robot robot = target.getRobot(getRobotName());
        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("Message", "Unsupported command");
        robot.setError(data);
        return true;
    }

    public IllegalCommand(String robotName) {
        super(robotName, "illegal");
    }
}
