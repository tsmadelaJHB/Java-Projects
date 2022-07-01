package za.co.wethinkcode.robotWorld.commands;

import za.co.wethinkcode.robotWorld.Gun;
import za.co.wethinkcode.robotWorld.Position;
import za.co.wethinkcode.robotWorld.Robot;
import za.co.wethinkcode.robotWorld.World;

import java.util.HashMap;
import java.util.Map;

public class LaunchCommand extends Command{

    @Override
    public boolean execute(World target) {
        Robot robot;
        Position position = target.getRandomPosition();
        String[] args = getArgument().split(" ", 3);
        try {
            int shield = Integer.parseInt(args[1]);
            int shot = Integer.parseInt(args[2]);
            robot = new Robot(getRobotName(), position, shield, new Gun(shot));
        }catch (IndexOutOfBoundsException | NumberFormatException e){
            robot = new Robot(getRobotName(), position, 3, new Gun(3));
        }
        target.addRobot(robot);
        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("message", "Hello " + getRobotName());
        robot.setResponse(data);
        return true;
    }

    public LaunchCommand(String robotName, String argument) {
        super(robotName,"launch", argument);
    }
}
