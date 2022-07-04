package za.co.wethinkcode.robotworlds.world;

import java.util.List;

public interface IWorld {
    List<Obstacle> getObstacles();

    List<Pits> getPits();

    List<Mine> getMines();

    void showObstacles();

    void showPits();

    void showMines();
}
