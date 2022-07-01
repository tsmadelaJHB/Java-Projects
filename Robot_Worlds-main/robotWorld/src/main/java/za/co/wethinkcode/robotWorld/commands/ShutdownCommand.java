package za.co.wethinkcode.robotWorld.commands;

import za.co.wethinkcode.robotWorld.Robot;
import za.co.wethinkcode.robotWorld.World;

public class ShutdownCommand extends Command {
    public ShutdownCommand(String robotName) {
        super(robotName, "off");
    }

    @Override
    public boolean execute(World target) {
        Robot robot = target.getRobot(getRobotName());
        robot.setStatus("DEAD");
        target.removeRobot(robot);
        return false;
    }
}
