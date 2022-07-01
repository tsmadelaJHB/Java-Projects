package za.co.wethinkcode.robotWorld.commands;

import za.co.wethinkcode.robotWorld.Robot;
import za.co.wethinkcode.robotWorld.World;

import java.util.HashMap;
import java.util.Map;

public class HelpCommand extends Command {

    public HelpCommand(String robotName) {
        super(robotName, "help");
    }

    @Override
    public boolean execute(World target) {
        Robot robot = target.getRobot(getRobotName());
        Map<Object, Object> data = new HashMap<Object, Object>();
        Map<Object, Object> objects = new HashMap<Object, Object>();
        objects.put("message", "I can understand these commands");
        objects.put("OFF", "Shut down robot");
        objects.put("HELP", "provide information about commands");
        objects.put("FORWARD", "move forward by specified number of steps, e.g. 'FORWARD 10'");
        objects.put("BACK", "move back by specified number of steps, e.g. 'BACK 10'");
        objects.put("LEFT", "turn robot left 90 degrees");
        objects.put("RIGHT", "turn robot right 90 degrees");
        objects.put("RELOAD", "Reload your robots shots and wait");
        objects.put("REPAIR", "Repair your robots shields and wait");
        objects.put("SET", "SET a mine");
        objects.put("DETECT", "Detect mine");
        data.put("options", objects);
        robot.setResponse(data);
        return true;
    }
}
