package za.co.wethinkcode.robotworlds.maze;

import za.co.wethinkcode.robotworlds.Position;
import za.co.wethinkcode.robotworlds.world.Mine;
import za.co.wethinkcode.robotworlds.world.Obstacle;
import za.co.wethinkcode.robotworlds.world.Pits;

import java.util.List;

public interface Maze {
    /**
     * Interface to represent a maze. A World will be loaded with a Maze, and will delegate the work to check if a
     * path is blocked by certain obstacles etc to this maze instance.
     */

    /**
     * @return the list of obstacles.
     */
    List<Obstacle> getObstacles();

    /**
     * @return the list of Pits.
     */
    List<Pits> getPits();

    public void MakeObsList(Obstacle obstacle);

    public void MakePitsList(Pits pits);

    public void MakeMinesList(Mine mine);

    void cleanPits();

    void cleanObstacles();

    boolean blocksPath(Position a, Position b);

    void MakeSpecificObstacle(Position TopLet, Position BottomRight);
}