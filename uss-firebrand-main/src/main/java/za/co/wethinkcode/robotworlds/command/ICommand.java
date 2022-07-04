package za.co.wethinkcode.robotworlds.command;

import za.co.wethinkcode.robotworlds.world.Mine;
import za.co.wethinkcode.robotworlds.world.Obstacle;
import za.co.wethinkcode.robotworlds.world.Pits;

import java.util.List;

public interface ICommand {
    List<Obstacle> getObstacles();

    List<Pits> getPits();

    List<Mine> getMines();
}
