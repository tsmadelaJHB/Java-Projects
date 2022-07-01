package za.co.wethinkcode.robotWorld;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ObstacleTest {
    
    @Test
    void ObstacleBlocksPosition(){
        Obstacle obstacle = new Obstacle(0, 0);
        Position position = new Position(4, 4);
        assertTrue(obstacle.blocksPosition(position));
    }

    @Test
    void ObstacleBlocksPath(){
        Obstacle obstacle = new Obstacle(1, 1);
        Position a = new Position(2, 0);
        Position b = new Position(2, 10);
        assertTrue(obstacle.blocksPath(a, b));
    }

    @Test
    void ObstacleSizeTest(){
        Obstacle obstacle = new Obstacle(1, 1);
        assertEquals(5, obstacle.getSize());
    }
}
