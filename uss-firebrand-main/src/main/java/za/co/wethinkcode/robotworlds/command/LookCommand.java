package za.co.wethinkcode.robotworlds.command;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import za.co.wethinkcode.robotworlds.*;
import za.co.wethinkcode.robotworlds.world.Obstacle;
import za.co.wethinkcode.robotworlds.world.Pits;

import java.util.ArrayList;
import java.util.List;

public class LookCommand extends Command {

    private final List<Obstacle> obstacleList = getObstacles();
    private final List<Pits> pitList = getPits();
    private final List<Robot> robotsList = MultiServers.RobotList;
    private final List<Position> edgeList =new ArrayList<>();

    private final List<Position> visibleObs = new ArrayList<>();
    private final List<Position> visiblePits = new ArrayList<>();
    private final List<Position> visibleRobots = new ArrayList<>();
    private final List<Position> visibleEdge = new ArrayList<>();

    public  int distance = 0;

    WorldConfig worldConfig = new WorldConfig();

    @Override
    public boolean execute(Robot target) {
        Response response = new Response();
        JsonArray entity = new JsonArray();
        String entityDirection;
        response.setResult("OK");
        setEdgeList();

        checkEveryDirection(target);
        for (Position visibleOb : visibleObs) {
            JsonObject data = new JsonObject();
            entityDirection = directionHandler(target.getPosition(), visibleOb);
            data.addProperty("direction", entityDirection);
            data.addProperty("type", "obstacle");
            data.addProperty("distance", distance);
            entity.add(data);
        }

        for (Position visiblePit : visiblePits) {
            JsonObject data = new JsonObject();
            entityDirection = directionHandler(target.getPosition(), visiblePit);
            data.addProperty("direction", entityDirection);
            data.addProperty("type", "pit");
            data.addProperty("distance", distance);
            entity.add(data);
        }

        for (Position visibleRobot : visibleRobots){
            JsonObject data = new JsonObject();
            entityDirection = directionHandler(target.getPosition(),visibleRobot);
            data.addProperty("direction",entityDirection);
            data.addProperty("type","robot");
            data.addProperty("distance",distance);
            entity.add(data);
        }

        for(Position edge : visibleEdge){
            JsonObject data = new JsonObject();
            entityDirection = directionHandler(target.getPosition(),edge);
            data.addProperty("direction",entityDirection);
            data.addProperty("type", "Edge");
            data.addProperty("distance",distance);
            entity.add(data);
        }


        JsonObject entities = new JsonObject();
        entities.add("Objects",entity);
        response.setData("Objects",entities);

        response.setState(target);
        target.setServerResponse(response);
        return true;
    }

    public void setEdgeList(){
        if (MultiServers.IWorldSize%2 != 0){
            edgeList.add(new Position(0,(MultiServers.IWorldSize+1)/2));
            edgeList.add(new Position(0,-(MultiServers.IWorldSize+1)/2));
            edgeList.add(new Position((MultiServers.IWorldSize+1)/2,0));
            edgeList.add(new Position((MultiServers.IWorldSize+1)/2,0));
        } else {
            edgeList.add(new Position(0,MultiServers.IWorldSize/2));
            edgeList.add(new Position(0,-MultiServers.IWorldSize/2));
            edgeList.add(new Position(MultiServers.IWorldSize/2,0));
            edgeList.add(new Position(MultiServers.IWorldSize/2,0));
        }
    }

    public String directionHandler(Position robot, Position entity){
        if(robot.getX() > entity.getX()){
            this.distance = entity.getX() - robot.getX();
            if(distance < 0){
                distance = -distance;
            }
            return "WEST";
        }else if(robot.getX() < entity.getX()){
            this.distance = robot.getX() - entity.getX();
            if(distance < 0){
                distance = -distance;
            }
            return "EAST";
        }else if(robot.getY() > entity.getY()){
            this.distance = (entity.getY() -robot.getY());
            if(distance < 0){
                distance = -distance;
            }
            return "SOUTH";
        }else {
            this.distance = robot.getY() -entity.getY();
            if(distance < 0){
                distance = -distance;
            }
            return "NORTH";
        }
    }

    /** Iterates through the obstacle list and gets the coordinates that represent the obstacle (getBottomLeftX and
     * getBottomLeftY). Iterate through the size of the obstacle (positionX/Y + 4) and if this position and the current
     * position are equal, return True.
     */
    public void isBlockedByObstacle(Position position) {
        for (Obstacle obs: this.obstacleList) {
            if (obs.blockedCords().contains(position)){
                int i = obs.blockedCords().indexOf(position);
                visibleObs.add(obs.blockedCords().get(i));
            }
        }
    }

    /** Performs the same process as isBlockedByObstacle, just using the list of pits.
     */
    public void isBlockedByPit(Position position) {
        for (Pits pit: this.pitList) {
            if (pit.blockedCords().contains(position)){
                int i = pit.blockedCords().indexOf(position);
                visiblePits.add(pit.blockedCords().get(i));
            }
        }
    }

    /** Performs the same process as isBlockedByObstacle, just using the list of Robots.
     */

    public void isBlockedByRobot(Position position){
        for (Robot robot : this.robotsList){
            if (robot.getPosition().equals(position)){
                visibleRobots.add(robot.getPosition());
            }
        }
    }

    public void isBlockedByEdge(Position position){
        for(Position edge : this.edgeList){
            if(position.getX() == edge.getX() && position.getY() != 0){
                visibleEdge.add(position);
            }else if(position.getY() == edge.getY() && position.getX() != 0){
                visibleEdge.add(position);
            }
        }
    }

    /** Looks n number of steps (specified by the config file) ahead of the robot in every direction. Checks if each step
     * in every direction is blocked by an obstacle. If it is, return True. This same check repeats for pits and mines.
     */
    public void checkEveryDirection(Robot target) {
        int robotVisibility = worldConfig.getRobotVisibility();
        for (int lookStepsInFront = 1; lookStepsInFront <= robotVisibility; lookStepsInFront++) {
            Position north = new Position(target.getPosition().getX(), target.getPosition().getY() + lookStepsInFront);
            Position east = new Position(target.getPosition().getX() + lookStepsInFront, target.getPosition().getY());
            Position south = new Position(target.getPosition().getX(), target.getPosition().getY() - lookStepsInFront);
            Position west = new Position(target.getPosition().getX() - lookStepsInFront, target.getPosition().getY());

            isBlockedByObstacle(north); isBlockedByObstacle(south); isBlockedByObstacle(east); isBlockedByObstacle(west);
            isBlockedByPit(north); isBlockedByPit(south); isBlockedByPit(east); isBlockedByPit(west);
            isBlockedByRobot(north); isBlockedByRobot(south); isBlockedByRobot(east); isBlockedByRobot(west);
//            isBlockedByEdge(north); isBlockedByEdge(south); isBlockedByEdge(east); isBlockedByEdge(west);

        }
    }

    public LookCommand() {
        super("look");
    }
}