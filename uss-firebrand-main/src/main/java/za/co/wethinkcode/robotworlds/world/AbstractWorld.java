package za.co.wethinkcode.robotworlds.world;

import za.co.wethinkcode.robotworlds.WorldNavigator;
import za.co.wethinkcode.robotworlds.maze.Maze;
import java.util.List;
import java.util.Random;

public abstract class AbstractWorld implements IWorld{
    public static Maze maze;

    public AbstractWorld(Maze maze) {
        this.maze = maze;
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
    public List<Mine> getMines() {return WorldNavigator.mine;}

    public static int RandomGenerator(int upper, int lower, int max){
        Random gen = new Random();
        return gen.nextInt(upper - (lower)) - max;
    }
}
