package za.co.wethinkcode.robotWorld;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

public class MazeTest{

    @Test
     void ObstaclesInBound(){
        Maze maze = new Maze(100, -100, 200, -200);
        List<Obstacle> obstacles = maze.getObstacles();

        for(Obstacle obs:obstacles){
            Position obsPosition = new Position(obs.getBottomLeftX(),obs.getBottomLeftY());
            assertTrue(obsPosition.isIn(new Position(-100,200), new Position(100,-200)));
        }
    }

    @Test
    void PitsInBound(){
        Maze maze = new Maze(100, -100, 200, -200);
        List<Pit> pits = maze.getPits();

        for(Pit pit:pits){
            Position pitPosition = new Position(pit.getBottomLeftX(),pit.getBottomLeftY());
            assertTrue(pitPosition.isIn(new Position(-100,200), new Position(100,-200)));
        }
    }
}