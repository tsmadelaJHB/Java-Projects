package za.co.wethinkcode.robotWorld.commands;

import za.co.wethinkcode.robotWorld.Position;
import za.co.wethinkcode.robotWorld.Robot;
import za.co.wethinkcode.robotWorld.World;

import java.util.HashMap;
import java.util.Map;

public class SetMineCommand extends Command{

    @Override
    public boolean execute(World target) {
        Robot robot = target.getRobot(getRobotName());
        Position position = robot.getPosition();
        Map<Object, Object> data = new HashMap<>();
        int shield = robot.getShield();

        while(robot.getShield() != 1) {
            robot.decreaseShield(); //decreasing shield to make robot vulnerable.
        }

        try {
            Thread.sleep(1000L * target.mineDelay());
        } catch (InterruptedException e) {
            return true;
        }

        target.updatePosition(1, robot);
        target.addMine(position);

        while (robot.getShield() != shield) {
            robot.increaseShied();  //increasing shield back to first state.
        }
        if(position.equals(robot.getPosition())) {
            target.stepOnMine(robot, position);
        }
        robot.setStatus("SETMINE");
        data.put("message", "Done");
        robot.setResponse(data);

        return true;
    }

    public SetMineCommand(String robotName) {
        super(robotName, "set");
    }
}
