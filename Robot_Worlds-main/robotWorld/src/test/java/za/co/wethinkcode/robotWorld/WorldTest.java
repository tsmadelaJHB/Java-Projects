package za.co.wethinkcode.robotWorld;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorldTest {

    @Test
    void initialWorld(){
        World world = new World(100, 200);
        assertTrue(world.isNewPositionAllowed(new Position(-50, 100)));
        assertEquals(5, world.getVisibilityRange());
    }

    @Test
    void worldMines() {
        World world = new World(100, 200);
        world.addMine(new Position(1, 1));
        assertTrue(world.mineInPosition(new Position(1,0), new Position(1, 1)));
        assertFalse(world.mineInPosition(new Position(1,0), new Position(0, 0)));
    }

    @Test
    void worldRobots() {
        World world = new World(100, 200);
        Position position = new Position(0, 0);
        Robot robot = new Robot("Test", position, 5, new Gun(5));
        world.addRobot(robot);
        assertEquals(world.getRobot("Test"), robot);
        assertTrue(world.robotInPosition(position, position));
        world.removeRobot(robot);
        assertFalse(world.robotInPosition(position, position));
    }
}
