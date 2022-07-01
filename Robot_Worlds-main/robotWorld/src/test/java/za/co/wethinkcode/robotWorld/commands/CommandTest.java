package za.co.wethinkcode.robotWorld.commands;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.robotWorld.*;
import za.co.wethinkcode.robotWorld.commands.ShutdownCommand;
import za.co.wethinkcode.robotWorld.commands.Command;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    @Test
    void getShutdownName() {
        Command test = new ShutdownCommand("test");
        assertEquals("off", test.getName());
        assertEquals("test", test.getRobotName());
    }

    @Test
    void getHelpName() {
        Command test = new HelpCommand("test");
        assertEquals("help", test.getName());
        assertEquals("test", test.getRobotName());
    }

    @Test
    void getLeftName() {
        Command test = new LeftCommand("test");
        assertEquals("left", test.getName());
        assertEquals("test", test.getRobotName());
    }

    @Test
    void getRightName() {
        Command test = new RightCommand("test");
        assertEquals("right", test.getName());
        assertEquals("test", test.getRobotName());
    }

    @Test
    void getForwardName() {
        Command test = new ForwardCommand("test", "2");
        assertEquals("forward", test.getName());
        assertEquals("test", test.getRobotName());
    }

    @Test
    void getBackName() {
        Command test = new BackCommand("test","2");
        assertEquals("back", test.getName());
        assertEquals("test", test.getRobotName());
    }

    @Test
    void getFireName() {
        Command test = new FireCommand("test");
        assertEquals("fire", test.getName());
        assertEquals("test", test.getRobotName());
    }

    @Test
    void getLookName() {
        Command test = new LookCommand("test");
        assertEquals("look", test.getName());
        assertEquals("test", test.getRobotName());

    }

    @Test
    void getLaunchName() {
        Command test = new LaunchCommand("test", "sniper");
        assertEquals("launch", test.getName());
        assertEquals("test", test.getRobotName());
    }

    @Test
    void getReloadName() {
        Command test = new ReloadCommand("test");
        assertEquals("reload", test.getName());
        assertEquals("test", test.getRobotName());
    }

    @Test
    void getRepairName() {
        Command test = new RepairCommand("test");
        assertEquals("repair", test.getName());
        assertEquals("test", test.getRobotName());
    }

    @Test
    void getSetMineName() {
        Command test = new SetMineCommand("test");
        assertEquals("set", test.getName());
        assertEquals("test", test.getRobotName());
    }

    @Test
    void getDetectMineName() {
        Command test = new DetectMineCommand("test");
        assertEquals("detect", test.getName());
        assertEquals("test", test.getRobotName());
    }

    @Test
    void executeDetectMine() {
        ByteArrayOutputStream outputStreamCaptor1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor1));
        World world = new World(200, 400);
        Position position = world.getRandomPosition();
        Gun gun = new Gun(3);
        Robot robot = new Robot("test", position, 5, gun);
        world.addRobot(robot);
        world.addMine(new Position(position.getX(),position.getY() + 1));
        robot.decreaseShield();

        Command test = Command.create("test detect");
        assertTrue(test.execute(world));
    }

    @Test
    void executeSetMine() {
        ByteArrayOutputStream outputStreamCaptor1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor1));
        World world = new World(200, 400);
        Position position = world.getRandomPosition();
        Gun gun = new Gun(3);
        Robot robot = new Robot("test", position, 5, gun);
        world.addRobot(robot);
        Position newPosition = new Position(position.getX(),position.getY() + 1);

        Command test = Command.create("test set");
        assertTrue(test.execute(world));
        assertTrue(world.mineInPosition(position,position));
        assertEquals(newPosition, robot.getPosition());
    }

    @Test
    void executeRepair() {
        ByteArrayOutputStream outputStreamCaptor1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor1));
        World world = new World(200, 400);
        Position position = world.getRandomPosition();
        Gun gun = new Gun(3);
        Robot robot = new Robot("test", position, 5, gun);
        world.addRobot(robot);
        world.addMine(new Position(position.getX(),position.getY() + 1));
        robot.decreaseShield();

        Command test = Command.create("test repair");
        assertTrue(test.execute(world));
        assertEquals(5, robot.getShield());
    }

    @Test
    void executeReload() {
        ByteArrayOutputStream outputStreamCaptor1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor1));
        World world = new World(200, 400);
        Position position = world.getRandomPosition();
        Gun gun = new Gun(3);
        Robot robot = new Robot("test", position, 5, gun);
        world.addRobot(robot);
        world.addMine(new Position(position.getX(),position.getY() + 1));
        robot.getGun().decreaseShots();

        Command test = Command.create("test reload");
        assertTrue(test.execute(world));
        assertEquals(3, robot.getGun().getShots());
    }

    @Test
    void executeLaunch() {
        ByteArrayOutputStream outputStreamCaptor1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor1));
        World world = new World(200, 400);
        Position position = world.getRandomPosition();
        world.addMine(new Position(position.getX(),position.getY() + 1));

        Command test = Command.create("test launch gunner 6 5");
        assertTrue(test.execute(world));
        assertTrue(world.getRobots().containsKey("test"));
        assertEquals(5, world.getRobots().get("test").getGun().getShots());
        assertEquals(6, world.getRobots().get("test").getShield());
    }

    @Test
    void executeShutdown() {
        ByteArrayOutputStream outputStreamCaptor1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor1));
        World world = new World(200, 400);
        Position position = world.getRandomPosition();
        Gun gun = new Gun(5);
        Robot robot = new Robot("test", position, 5, gun);
        world.addRobot(robot);
        world.addMine(new Position(position.getX(),position.getY() + 1));

        Command test = Command.create("test off");
        assertFalse(test.execute(world));
        assertEquals("DEAD", robot.getStatus());
    }

    @Test
    void executeForward10() {
        ByteArrayOutputStream outputStreamCaptor1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor1));
        World world = new World(200, 400);
        Position position = world.getRandomPosition();
        Gun gun = new Gun(5);
        Robot robot = new Robot("test", position, 5, gun);
        world.addRobot(robot);
        world.addMine(new Position(position.getX(),position.getY() + 1));

        Command test = Command.create("test forward 10");
        assertTrue(test.execute(world));
        assertTrue(robot.getResponseData() instanceof Map);
    }

    @Test
    void executeBack10() {
        ByteArrayOutputStream outputStreamCaptor1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor1));
        World world = new World(200, 400);
        Position position = world.getRandomPosition();
        Gun gun = new Gun(5);
        Robot robot = new Robot("test", position, 5, gun);
        world.addRobot(robot);
        world.addMine(new Position(position.getX(),position.getY() + 1));

        Command test = Command.create("test back 10");
        assertTrue(test.execute(world));
        assertTrue(robot.getResponseData() instanceof Map);
    }

    @Test
    void executeLeft() {
        ByteArrayOutputStream outputStreamCaptor1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor1));
        World world = new World(200, 400);
        Position position = world.getRandomPosition();
        Gun gun = new Gun(5);
        Robot robot = new Robot("test", position, 5, gun);
        world.addRobot(robot);
        world.addMine(new Position(position.getX(),position.getY() + 1));

        Command test = Command.create("test left");
        assertTrue(test.execute(world));
        assertTrue(robot.getResponseData() instanceof Map);
        assertEquals(IWorld.Direction.WEST, robot.getDirection());
    }

    @Test
    void executeRight() {
        ByteArrayOutputStream outputStreamCaptor1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor1));
        World world = new World(200, 400);
        Position position = world.getRandomPosition();
        Gun gun = new Gun(5);
        Robot robot = new Robot("test", position, 5, gun);
        world.addRobot(robot);
        world.addMine(new Position(position.getX(),position.getY() + 1));

        Command test = Command.create("test right");
        assertTrue(test.execute(world));
        assertTrue(robot.getResponseData() instanceof Map);
        assertEquals(IWorld.Direction.EAST, robot.getDirection());
    }

    @Test
    void executeFireHit() {
        ByteArrayOutputStream outputStreamCaptor1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor1));
        World world = new World(200, 400);
        Position position = world.getRandomPosition();
        Gun gun = new Gun(5);
        Robot robot = new Robot("test", position, 5, gun);
        Robot dummy = new Robot("dummy", new Position(position.getX(), position.getY() + 1), 5, gun);
        world.addRobot(robot);
        world.addRobot(dummy);
        world.addMine(new Position(position.getX(),position.getY() - 1));

        Command test = Command.create("test fire");
        assertTrue(test.execute(world));
        assertTrue(robot.getResponseData() instanceof Map);
        assertEquals(4,dummy.getShield());
    }

    @Test
    void executeFireMissRange() {
        ByteArrayOutputStream outputStreamCaptor1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor1));
        World world = new World(200, 400);
        Position position = world.getRandomPosition();
        Gun gun = new Gun(5);
        Robot robot = new Robot("test", position, 5, gun);
        Robot dummy = new Robot("dummy", new Position(position.getX(), position.getY() + 7), 5, gun);
        world.addRobot(robot);
        world.addRobot(dummy);
        world.addMine(new Position(position.getX(),position.getY() - 1));

        Command test = Command.create("test fire");
        assertTrue(test.execute(world));
        assertTrue(robot.getResponseData() instanceof Map);
        assertEquals(5,dummy.getShield());
    }

    @Test
    void executeFireMissDirection() {
        ByteArrayOutputStream outputStreamCaptor1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor1));
        World world = new World(200, 400);
        Position position = world.getRandomPosition();
        Gun gun = new Gun(5);
        Robot robot = new Robot("test", position, 5, gun);
        Robot dummy = new Robot("dummy", new Position(position.getX() + 1, position.getY()), 5, gun);
        world.addRobot(robot);
        world.addRobot(dummy);
        world.addMine(new Position(position.getX(),position.getY() - 1));

        Command test = Command.create("test fire");
        assertTrue(test.execute(world));
        assertTrue(robot.getResponseData() instanceof Map);
        assertEquals(5,dummy.getShield());
    }

    @Test
    void executeFireMissObstacle() {
        ByteArrayOutputStream outputStreamCaptor1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor1));
        World world = new World(200, 400);
        Position position = world.getRandomPosition();
        Gun gun = new Gun(5);
        Robot robot = new Robot("test", position, 5, gun);
        Robot dummy = new Robot("dummy", new Position(position.getX(), position.getY() + 2), 5, gun);
        world.addRobot(robot);
        world.addRobot(dummy);
        world.addMine(new Position(position.getX(),position.getY() + 1));

        Command test = Command.create("test fire");
        assertTrue(test.execute(world));
        assertTrue(robot.getResponseData() instanceof Map);
        assertEquals(5,dummy.getShield());
    }

    @Test
    void executeHitDead() {
        ByteArrayOutputStream outputStreamCaptor1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor1));
        World world = new World(200, 400);
        Position position = world.getRandomPosition();
        Gun gun = new Gun(5);
        Robot robot = new Robot("test", position, 5, gun);
        Robot dummy = new Robot("dummy", new Position(position.getX(), position.getY() + 1), 1, gun);
        world.addRobot(robot);
        world.addRobot(dummy);
        world.addMine(new Position(position.getX(),position.getY() - 1));

        Command test = Command.create("test fire");
        assertTrue(test.execute(world));
        assertTrue(robot.getResponseData() instanceof Map);
        assertEquals(0,dummy.getShield());
        assertEquals("DEAD",  dummy.getStatus());
    }

    @Test
    void executeLook() {
        World world = new World(200, 400);
        Position position = world.getRandomPosition();
        Gun gun = new Gun(5);
        Robot robot = new Robot("test", position, 5, gun);
        world.addRobot(robot);
        world.addMine(new Position(position.getX(),position.getY() + 1));

        Command test = Command.create("test look");
        assertTrue(test.execute(world));
        assertTrue(robot.getResponseData() instanceof Map);
    }

    @Test
    void legalCommands() {
        Command test = Command.create("test forward 10");
        assertTrue(test instanceof ForwardCommand);
        test = Command.create("test back 10");
        assertTrue(test instanceof BackCommand);
        test = Command.create("test left");
        assertTrue(test instanceof LeftCommand);
        test = Command.create("test right");
        assertTrue(test instanceof RightCommand);
        test = Command.create("test help");
        assertTrue(test instanceof HelpCommand);
        test = Command.create("test off");
        assertTrue(test instanceof ShutdownCommand);
        test = Command.create("test look");
        assertTrue(test instanceof LookCommand);
        test = Command.create("test launch sniper");
        assertTrue(test instanceof LaunchCommand);
        test = Command.create("test reload");
        assertTrue(test instanceof ReloadCommand);
        test = Command.create("test fire");
        assertTrue(test instanceof FireCommand);
        test = Command.create("test repair");
        assertTrue(test instanceof RepairCommand);
    }

    @Test
    void illegalCommands() {
        Command test = Command.create("say hello");
        assertTrue(test instanceof IllegalCommand);
    }
}