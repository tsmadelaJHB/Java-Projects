package za.co.wethinkcode.robotWorld.commands;

import za.co.wethinkcode.robotWorld.Robot;
import za.co.wethinkcode.robotWorld.World;

import java.util.HashMap;
import java.util.Map;

public class ReloadCommand extends Command{

    @Override
    public boolean execute(World target) {
        Robot robot = target.getRobot(getRobotName());
        target.getRobot(getRobotName()).getGun().reload();
        Map<Object, Object> data = new HashMap<Object, Object>();

        try {
            Thread.sleep(1000L * target.reloadDelay());
        } catch (InterruptedException e) {
            return true;
        }

        data.put("message", "Done");
        robot.setStatus("RELOADING");
        robot.setResponse(data);
        return true;
    }

    public ReloadCommand(String robotName) {
        super(robotName, "reload");
    }

}
