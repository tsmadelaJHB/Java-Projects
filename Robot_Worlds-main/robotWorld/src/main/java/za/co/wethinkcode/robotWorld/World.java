package za.co.wethinkcode.robotWorld;

import za.co.wethinkcode.robotWorld.commands.Command;

import java.util.*;

public class World implements IWorld{

    public static final Position CENTRE = new Position(0,0);

    private final Position TOP_LEFT;
    private final Position BOTTOM_RIGHT;
    private final int visibleRange;

    private HashMap<String, Robot> robots;

    private List<Obstacle> obstacleList;
    private List<Pit> pitList;
    private List<Mine> mines;

    public World(int width, int height){
        this.TOP_LEFT = new Position(-width/2, height/2);
        this.BOTTOM_RIGHT = new Position(width/2, -height/2);
        this.visibleRange = 5;
        this.robots = new HashMap<String, Robot>();
        Maze maze = new Maze(width/2, -width/2, height/2, -height/2);
        this.obstacleList = maze.getObstacles();
        this.pitList = maze.getPits();
        this.mines = new ArrayList<>();
    }

    public void updateDirection(boolean turnRight, Robot robot) {
        if(turnRight){
            switch (robot.getDirection()) {
                case NORTH:
                    robot.setDirection(Direction.EAST);
                    break;
                case EAST:
                    robot.setDirection(Direction.SOUTH);
                    break;
                case SOUTH:
                    robot.setDirection(Direction.WEST);
                    break;
                case WEST:
                    robot.setDirection(Direction.NORTH);
                    break;
            }
        } else {
            switch (robot.getDirection()) {
                case NORTH:
                    robot.setDirection(Direction.WEST);
                    break;
                case WEST:
                    robot.setDirection(Direction.SOUTH);
                    break;
                case SOUTH:
                    robot.setDirection(Direction.EAST);
                    break;
                case EAST:
                    robot.setDirection(Direction.NORTH);
                    break;
            }
        }
    }

    public Response updatePosition(int nrSteps, Robot robot) {
        int newX = robot.getPosition().getX();
        int newY = robot.getPosition().getY();

        if (Direction.NORTH.equals(robot.getDirection())) {
            newY = newY + nrSteps;
        }

        if (Direction.SOUTH.equals(robot.getDirection())) {
            newY = newY - nrSteps;
        }

        if (Direction.WEST.equals(robot.getDirection())) {
            newX = newX - nrSteps;
        }

        if (Direction.EAST.equals(robot.getDirection())) {
            newX = newX + nrSteps;
        }

        Position newPosition = new Position(newX, newY);

        if (positonObstructed(robot.getPosition() ,newPosition)) {
            return Response.FAILED_OBSTRUCTED;
        }

        /*if  (robotInPosition(robot.getPosition(), newPosition)) {
            return Response.FAILED_OBSTRUCTED;
        }*/

        if (pitAhead(robot.getPosition() ,newPosition)) {
            return Response.FAILED_FELL_IN_PIT;
        }

        if (mineInPosition(robot.getPosition(), newPosition)) {
            stepOnMine(robot, newPosition);
            if(robot.getShield() > 0) {
                return Response.SUCCESS_STEPPED_ON_MINE;
            } else {
                return Response.FAILED_STEPPED_ON_MINE;
            }
        }

        if (isNewPositionAllowed(newPosition)){
            robot.setPosition(newPosition);
            return Response.SUCCESS;
        }
        return Response.FAILED_OUTSIDE_WORLD;
    }

    public boolean pitAhead(Position currentPosition, Position newPosition) {
        for(Pit pit:pitList) {
            if(pit.blocksPosition(newPosition) ||
                    pit.blocksPath(currentPosition, newPosition)) {
                return true;
            }
        }
        return false;
    }

    public boolean positonObstructed(Position currentPosition, Position newPosition) {
        for(Obstacle obstacle:obstacleList) {
            if(obstacle.blocksPosition(newPosition) ||
                    obstacle.blocksPath(currentPosition, newPosition)) {
                return true;
            }
        }
        return false;
    }

    public boolean handleCommand(Command command) { return command.execute(this); }

    // @Override
    // public String toString() {
    //     return "[" + this.robot.getPosition().getX() + "," + this.robot.getPosition().getY() + "] "
    //             + this.robot.getName() + "> " + this.response;
    // }

    public boolean isNewPositionAllowed(Position position) {
        return position.isIn(TOP_LEFT,BOTTOM_RIGHT);
    }

    public Robot getRobot(String robotName) {
        return robots.get(robotName);
    }

    public void addRobot(Robot robot) {
        robots.put(robot.getName(), robot);
    }

    public void removeRobot(Robot robot) {
        robots.remove(robot.getName());
    }

    public boolean robotInPosition(Position currentPosition, Position newPosition) {
        for(Robot robot:robots.values()){
            if(robot.blocksPath(currentPosition, newPosition)) {
                return true;
            }
        }
        return false;
    }

    public void addMine(Position position) {
        mines.add(new Mine(position));
    }

    public void removeMine(Position currentPosition, Position newPosition) {
        for(int i = 0; i < mines.size(); ++i) {
            if(mines.get(i).blocksPosition(newPosition) ||
                    mines.get(i).blocksPath(currentPosition, newPosition)) {
                mines.remove(i);
            }
        }
    }

    public boolean mineInPosition(Position currentPosition, Position newPosition) {
        for(Mine mine:mines) {
            if(mine.blocksPosition(newPosition) || mine.blocksPath(currentPosition, newPosition)){
                return true;
            }
        }
        return false;
    }

    public int getVisibilityRange() {
        return visibleRange;
    }

    public Position getRandomPosition() {
        Random rand = new Random();
        int xMin = TOP_LEFT.getX();
        int xMax = BOTTOM_RIGHT.getX();
        int yMin = BOTTOM_RIGHT.getY();
        int yMax = TOP_LEFT.getY();
        while(true){
            int xCoor = rand.nextInt((xMax - xMin) + 1) + xMin;
            int yCoor = rand.nextInt((yMax - yMin) + 1) + yMin;
            Position position = new Position(xCoor, yCoor);
            Position checkPosition = new Position(xCoor + 5, yCoor);

            if(mineInPosition(checkPosition, position) || positonObstructed(checkPosition, position)
            || pitAhead(checkPosition, position) || robotInPosition(position, position)){
                continue;
            } else {
                return position;
            }
        }
    }

    public HashMap<String, Robot> getRobots(){
        return this.robots;
    }

    public int mineDelay() {
        return 5;
    }

    public int repairDelay(){return 3;}

    public int reloadDelay(){return 5;}

    public void stepOnMine(Robot robot, Position position) {
        for(int i =0; i < 3; ++i) {
            robot.decreaseShield();
        }
        removeMine(robot.getPosition(), position);
    }

    public Position getTopLeft() {
        return TOP_LEFT;
    }

    public Position getBottomRight() {
        return BOTTOM_RIGHT;
    }

}