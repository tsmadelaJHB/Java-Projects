package za.co.wethinkcode.robotWorld.commands;

import za.co.wethinkcode.robotWorld.IWorld;
import za.co.wethinkcode.robotWorld.Position;
import za.co.wethinkcode.robotWorld.Robot;
import za.co.wethinkcode.robotWorld.World;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FireCommand extends Command{
    @Override
    public boolean execute(World target) {
        Robot robot = target.getRobot(getRobotName());
        Robot hitRobot = null;
        Map<Object, Object> data = new HashMap<Object, Object>();
        Position positionToCheck = robot.getPosition();
        Boolean hit = false;

        if (robot.getGun().getShots() > 0) {
            for (int i = 1; i <= robot.getGun().determineMax(); i++) {
                robot.getGun().decreaseShots();
                positionToCheck = getNextPosition(positionToCheck, robot.getDirection(), i);
                if (target.positonObstructed(robot.getPosition(), positionToCheck)) {
                    data.put("message", "Miss");
                    hit = true;
                    break;
                } else {
                    hitRobot = checkHit(target.getRobots().values(), robot.getPosition(), positionToCheck);
                    if (hitRobot != null) {
                        hitRobot.decreaseShield();
                        data.put("message", "Hit");
                        data.put("distance", i);
                        data.put("robot", hitRobot.getName());
                        data.put("state", hitRobot.getState());
                        hit = true;
                        break;
                    }
                }
            }
            if (!hit) {
                data.put("message", "Miss");
            }
        }else{
            data.put("message", "No shots");
        }
        robot.setResponse(data);
        return true;
    }

    public FireCommand(String robotName) {
        super(robotName, "fire");
    }

    public Position getNextPosition(Position position, IWorld.Direction direction, int num){
        switch (direction) {
            case NORTH: return new Position(position.getX(), position.getY() + num);
            case EAST: return new Position(position.getX() + num, position.getY());
            case SOUTH: return new Position(position.getX(), position.getY() - num);
            case WEST: return new Position(position.getX() - num, position.getY());
            default: return position;
        }
    }

    public Robot checkHit(Collection<Robot> robots, Position currentPosition, Position newPosition) {
        for(Robot robot:robots){
            if(robot.blocksPath(currentPosition, newPosition)) {
                return robot;
            }
        }
        return null;
    }
}
