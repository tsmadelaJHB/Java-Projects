package za.co.wethinkcode.robotWorld.commands;

import za.co.wethinkcode.robotWorld.Robot;
import za.co.wethinkcode.robotWorld.World;

import java.util.HashMap;
import java.util.Map;

public class RepairCommand extends Command{

    @Override
    public boolean execute(World target) {
        Robot robot = target.getRobot(getRobotName());
        target.getRobot(getRobotName()).resetShield();
        Map<Object, Object> data = new HashMap<Object, Object>();
        try {
            Thread.sleep(1000L * target.repairDelay());
        } catch (InterruptedException e) {
            return true;
        }
        data.put("message", "Done");
        robot.setStatus("REPAIRING");
        robot.setResponse(data);
        return true;
    }

    public RepairCommand(String robotName) {
        super(robotName, "repair");
    }
}
