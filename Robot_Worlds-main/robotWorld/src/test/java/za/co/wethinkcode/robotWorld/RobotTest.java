package za.co.wethinkcode.robotWorld;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    @Test
    void robotInitialize() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Position start = new Position(0, 0);
        Gun gun = new Gun(3);
        Robot robot = new Robot("TestDummy", new Position(0, 0), 20, gun);
        assertEquals("TestDummy", robot.getName());
        assertEquals(start, robot.getPosition());
        assertEquals(IWorld.Direction.NORTH, robot.getDirection());
        assertEquals(20, robot.getShield());
        assertEquals(20, robot.getMaxShield());
    }

    @Test
    void movePosition() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Position start = new Position(0, 0);
        Gun gun = new Gun(3);
        Robot robot = new Robot("TestDummy", start, 20, gun);
        Position next = new Position(0, 20);
        robot.setPosition(next);
        assertEquals(next, robot.getPosition());
    }

    @Test
    void moveDirection() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        Position start = new Position(0, 0);
        Gun gun = new Gun(3);
        Robot robot = new Robot("TestDummy", start, 20, gun);
        robot.setDirection(IWorld.Direction.EAST);
        assertEquals(IWorld.Direction.EAST, robot.getDirection());
    }

}
