package za.co.wethinkcode.robotworlds;

import za.co.wethinkcode.robotworlds.maze.Maze;
import za.co.wethinkcode.robotworlds.world.*;

import java.util.ArrayList;
import java.util.List;

public abstract class WorldNavigator implements IWorldNavigator {
    private Position position;
    private Direction currentDirection;
    private  Position TOP_LEFT = new Position(-100,200);
    private  Position BOTTOM_RIGHT = new Position(100,-200);
    public static List<Mine> mine = new ArrayList<>();
    Maze maze;

    public WorldNavigator() {
        this.position = CENTRE;
        this.currentDirection = Direction.NORTH;
        this.maze = AbstractWorld.maze;
    }

    public void makeMinesList(Mine mines){
        mine.add(mines);
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public Direction getCurrentDirection() {
        return this.currentDirection;
    }

    @Override
    public boolean isNewPositionAllowed(Position position) {
        return position.isIn(TOP_LEFT,BOTTOM_RIGHT);
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setTOP_LEFT(Position topLeft){
        this.TOP_LEFT = topLeft;
    }

    public Position getTOP_LEFT() {
        return TOP_LEFT;
    }

    public Position getBOTTOM_RIGHT() {
        return BOTTOM_RIGHT;
    }

    public void setBOTTOM_RIGHT(Position bottomRight){
        this.BOTTOM_RIGHT = bottomRight;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    @Override
    public boolean isAtEdge() {
        if(getPosition().getX() == TOP_LEFT.getX() || getPosition().getX() == BOTTOM_RIGHT.getX()){
            return true;
        }
        return getPosition().getY() == TOP_LEFT.getY() || getPosition().getY() == BOTTOM_RIGHT.getY();
    }

    @Override
    public List<Obstacle> getObstacles() {
        return maze.getObstacles();
    }

    @Override
    public List<Pits> getPits() {
        return maze.getPits();
    }

    @Override
    public List<Mine> getMines() {return mine;}
}
