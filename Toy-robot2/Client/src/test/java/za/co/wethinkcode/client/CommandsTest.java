package za.co.wethinkcode.client;

import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import za.co.wethinkcode.client.commands.*;

public class CommandsTest {
    
    @Test
    public void testHandleCommandForward(){
        // JSONObject testFwd = CommandHandler.handleCommand("forward 12");
        // assertEquals(testFwd.get("message"), "Please launch a robot first.");
        
        CommandHandler.setRobot(new Robot("iTF"));
        JSONObject testFwd = CommandHandler.handleCommand("forward 12");
        
        assertEquals("iTF", testFwd.get("robot"));
        assertEquals("forward", testFwd.get("command"));
        assertEquals("12", ((String[]) testFwd.get("arguments"))[0]);
        CommandHandler.setRobot(null);
    }

    @Test
    public void testHandleCommandClose(){
        JSONObject testFwd = CommandHandler.handleCommand("close");
        assertEquals("close", testFwd.get("command"));
    }

    @Test
    public void testHandCommandTurn(){        
        CommandHandler.setRobot(new Robot("wNW"));
        JSONObject testFwd = CommandHandler.handleCommand("turn right");
        
        assertEquals("wNW", testFwd.get("robot"));
        assertEquals("turn", testFwd.get("command"));
        assertEquals("right", ((String[]) testFwd.get("arguments"))[0]);
        CommandHandler.setRobot(null);
    }

    @Test
    public void testHandleCommandWrong(){
        JSONObject testFwd = CommandHandler.handleCommand("tatakai");        
        assertEquals("Unsupported command. Use <help> to see supported commands.", testFwd.get("message"));
    }

    @Test
    public void testMovementCorrect(){
        Robot rbt = new Robot("GmL");
        JSONObject test = Movement.movementJSON("forward 12", rbt);

        assertNotNull(test);
        assertNotNull(test.get("robot"));
        assertNotNull(test.get("command"));
        assertNotNull(test.get("arguments"));

        assertEquals("GmL", test.get("robot"));
        assertEquals("forward", test.get("command"));

        String[] args = (String[]) test.get("arguments");
        assertEquals("12", args[0]);

        test = Movement.movementJSON("back 3", rbt);
        args = (String[]) test.get("arguments");
        assertEquals("back", test.get("command"));
        assertEquals("3", args[0]);

        test = Movement.movementJSON("back -23", rbt);
        args = (String[]) test.get("arguments");
        assertEquals("back", test.get("command"));
        assertEquals("-23", args[0]);

        test = Movement.movementJSON("forward -3245", rbt);
        args = (String[]) test.get("arguments");
        assertEquals("forward", test.get("command"));
        assertEquals("-3245", args[0]);
    }

    @Test
    public void testMovementWrongCommand(){
        Robot rbt = new Robot("DRk");
        JSONObject test = Movement.movementJSON("forward lol", rbt);

        assertNotNull(test);
        assertNull(test.get("robot"));
        assertNull(test.get("command"));
        assertNotNull(test.get("message"));

        assertEquals("Please enter '<forward or back> <number of steps>'.", test.get("message"));    
    }

    @Test
    public void testTurnCorrect(){
        Robot rbt = new Robot("SVE");
        JSONObject test = Turn.turnJSON("turn left", rbt);

        assertNotNull(test);
        assertNotNull(test.get("robot"));
        assertNotNull(test.get("command"));
        assertNotNull(test.get("arguments"));

        assertEquals("SVE", test.get("robot"));
        assertEquals("turn", test.get("command"));

        String[] args = (String[]) test.get("arguments");
        assertEquals("left", args[0]);

        test = Turn.turnJSON("turn right", rbt);
        args = (String[]) test.get("arguments");
        assertEquals("right", args[0]);
    }

    @Test
    public void testTurnWrongCommand(){
        Robot rbt = new Robot("LVM");
        JSONObject test = Turn.turnJSON("turn l??t", rbt);

        assertNotNull(test);
        assertNull(test.get("robot"));
        assertNull(test.get("command"));
        assertNotNull(test.get("message"));

        assertEquals("Please enter 'turn <left or right>'.", test.get("message"));
    }

    @Test
    public void testTurnNoRobot(){
        Robot rbt = null;
        JSONObject test = Turn.turnJSON("turn right", rbt);

        assertNotNull(test);
        assertNull(test.get("robot"));
        assertNull(test.get("command"));
        assertNotNull(test.get("message"));

        assertEquals("Please launch a robot first.", test.get("message"));
    }

    @Test
    public void testLaunchCorrect(){
        JSONObject test = Launch.launchJSON("launch test-type maverick");

        assertNotNull(test);
        assertNotNull(test.get("robot"));
        assertNotNull(test.get("command"));
        assertNotNull(test.get("arguments"));

        assertEquals("maverick", test.get("robot"));
        assertEquals("launch", test.get("command"));

        String[] args = (String[]) test.get("arguments");
        assertEquals("test-type", args[0]);
        assertEquals("5", args[1]);
        assertEquals("5", args[2]);
        CommandHandler.setRobot(null);
    }

    @Test
    public void testLaunchWrong(){
        JSONObject test = Launch.launchJSON("crunch test-type maverick");

        assertNotNull(test);
        assertNull(test.get("robot"));
        assertNull(test.get("command"));
        assertNotNull(test.get("message"));

        assertEquals("Please enter 'launch <robot type> <robot name>'.", test.get("message"));
    }

    @Test
    public void testHelp(){
        JSONObject test = Help.helpJSON();

        assertNotNull(test);
        assertNotNull(test.get("message"));
        assertEquals("These are the supported commands:\n" +
        "Launch: Use <launch> <robot type> <robot name> to create a new robot\n" +
        "Forward: Use <forward> <number of steps> to move the robot forward\n" +
        "Back: Use <back> <number of steps> to move the robot back\n" +
        "Help: Use <help> to display the list of supported commands.\n" +
        "Close: Use <close> to shutdown the client.\n", test.get("message"));
    }

    @Test
    public void testClose(){
        JSONObject test = Close.closeJSON();

        assertNotNull(test);
        assertNotNull(test.get("command"));
        assertEquals(test.get("command"), "close");
    }
}
