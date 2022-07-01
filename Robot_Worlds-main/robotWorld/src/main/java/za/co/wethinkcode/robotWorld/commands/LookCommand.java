package za.co.wethinkcode.robotWorld.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import za.co.wethinkcode.robotWorld.Position;
import za.co.wethinkcode.robotWorld.Robot;
import za.co.wethinkcode.robotWorld.World;
import za.co.wethinkcode.robotWorld.IWorld.Direction;

public class LookCommand extends Command{
    @Override
    public boolean execute(World target) {
        Robot robot = target.getRobot(getRobotName());
        Direction direction = robot.getDirection();
        int xCoor = robot.getPosition().getX();
        int yCoor = robot.getPosition().getY();
        List<Map<String,Object>> objectList = new ArrayList<Map<String, Object>>();

        for(int i = 1; i < target.getVisibilityRange(); ++i) {
            if(objectInPosition(new Position(xCoor, yCoor + i), target) != null) {
                objectList.add(getObject(i, /// distance from robot
                    objectInPosition(new Position(xCoor, yCoor + i), target), /// getting object name using method objectInPosition
                    direction.toString(), "NORTH")); /// robot direction, true direction of object
            }
            if(objectInPosition(new Position(xCoor + i, yCoor), target) != null) {
                objectList.add(getObject(i, 
                    objectInPosition(new Position(xCoor + i, yCoor),target), 
                    direction.toString(), "EAST"));
            }
            if(objectInPosition(new Position(xCoor, yCoor - i), target) != null) {
                objectList.add(getObject(i, 
                    objectInPosition(new Position(xCoor, yCoor - i),target), 
                    direction.toString(), "SOUTH"));
            }
            if(objectInPosition(new Position(xCoor - i, yCoor), target) != null) {
                objectList.add(getObject(i, 
                    objectInPosition(new Position(xCoor - i, yCoor),target), 
                    direction.toString(), "WEST"));
            }
        }
        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("objects", objectList);
        robot.setResponse(data);
        return true;
    }

    private Map<String, Object> getObject(int distance, String object, String robotDirection, String direction) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("direction", getDirection(robotDirection, direction));
        map.put("type", object);
        map.put("distance", distance);
        return map;
    }

    private String getDirection(String robotDirection, String direction) {
        List<String> coOrdinates = new ArrayList<>();
        String[] compass = {"NORTH","EAST","SOUTH","WEST"};
        for(String coOrdinate:compass) {
            coOrdinates.add(coOrdinate);
        }
        
        if (coOrdinates.indexOf(robotDirection) == 1){
            try {
                return coOrdinates.get(coOrdinates.indexOf(direction) - coOrdinates.indexOf(robotDirection));
            } catch (Exception e) {
                return coOrdinates.get(coOrdinates.size() - coOrdinates.indexOf(robotDirection));
            }
        }
        if (coOrdinates.indexOf(robotDirection) == 2) {
            try {
                return coOrdinates.get(coOrdinates.indexOf(robotDirection) + coOrdinates.indexOf(direction));
            } catch (Exception e) {
                return coOrdinates.get(coOrdinates.indexOf(direction) - coOrdinates.indexOf(robotDirection));
            }
        }
        if (coOrdinates.indexOf(robotDirection) == 3) {
            try {
                return coOrdinates.get(coOrdinates.indexOf(direction) + 1);
            } catch (Exception e) {
                return coOrdinates.get(coOrdinates.indexOf(direction) - coOrdinates.indexOf(robotDirection));
            }
        }
        return direction;
    }

    private String objectInPosition(Position position, World world) {
        int xMin = world.getTopLeft().getX();
        int xMax = world.getBottomRight().getX();
        int yMin = world.getBottomRight().getY();
        int yMax = world.getTopLeft().getY();
        if(world.positonObstructed(position, position)) {
            return "OBSTACLE";
        } else if(world.pitAhead(position, position)) {
            return "PIT";
        } else if(world.robotInPosition(position, position)) {
            return "ROBOT";
        } else if(world.mineInPosition(position, position)) {
            return "MINE";
        } else if(position.getX() == xMin || position.getX() == xMax || 
        position.getY() == yMin || position.getY() == yMax){
            return "Edge";
        }
        return null;
    }

    public LookCommand(String robotName) {
        super(robotName, "look");
    }
}