package za.co.wethinkcode.robotWorld.commands;

import za.co.wethinkcode.robotWorld.Mine;
import za.co.wethinkcode.robotWorld.Position;
import za.co.wethinkcode.robotWorld.Robot;
import za.co.wethinkcode.robotWorld.World;

import java.util.HashMap;
import java.util.Map;

public class DetectMineCommand extends Command{

    @Override
    public boolean execute(World target) {
        Map<Object, Object> data = new HashMap<Object, Object>();
        Map<Object, Object> objects = new HashMap<Object, Object>();
        Robot robot = target.getRobot(getRobotName());
        int robotX = robot.getPosition().getX();
        int robotY =  robot.getPosition().getY();
        int range = target.getVisibilityRange()/4;
        for(int i = 1; i < range; ++i) {
            if (target.mineInPosition(robot.getPosition(), new Position(robotX, robotY + i))){
                objects.put("direction", "NORTH");
                objects.put("distance", i);
            }
        }
        for(int i = 1; i < range; ++i) {
            if (target.mineInPosition(robot.getPosition(), new Position(robotX, robotY - i))){
                objects.put("direction", "SOUTH");
                objects.put("distance", i);
            }
        }
        for(int i = 1; i < range; ++i) {
            if (target.mineInPosition(robot.getPosition(), new Position(robotX + i, robotY))){
                objects.put("direction", "EAST");
                objects.put("distance", i);
            }
        }
        for(int i = 1; i < range; ++i) {
            if (target.mineInPosition(robot.getPosition(), new Position(robotX - i, robotY))){
                objects.put("direction", "WEST");
                objects.put("distance", i);
            }
        }
        data.put("Objects", objects);
        robot.setResponse(data);

        return true;
    }

    public DetectMineCommand(String robotName) {
        super(robotName, "detect");
    }
}
