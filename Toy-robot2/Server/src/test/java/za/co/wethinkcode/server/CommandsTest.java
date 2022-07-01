//package za.co.wethinkcode.server;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.json.simple.JSONObject;
//import org.junit.jupiter.api.Test;
//
//import za.co.wethinkcode.server.commands.*;
//
//public class CommandsTest {
//
//    @Test
//    public void testHandleCommandForward(){
//        Robot testRobot = new Robot("placeholder", 5, 5, null);
//        JSONObject testFwd = CommandHandler.handleCommand("forward", new String[]{"12"}, testRobot, "placeholder");
//
//        assertEquals("OK", testFwd.get("result"));
//        assertEquals("Done", ((JSONObject) testFwd.get("data")).get("message"));
//
//        testFwd = CommandHandler.handleCommand("forward", new String[]{"kool"}, testRobot, "placeholder");
//
//        assertEquals("ERROR", testFwd.get("result"));
//        assertEquals("Could not parse arguments.", ((JSONObject) testFwd.get("data")).get("message"));
//    }
//
//    @Test
//    public void testHandleCommandClose(){
//        JSONObject testFwd = CommandHandler.handleCommand("close", null, new Robot("close", 5, 5, null), "placeholder");
//        assertEquals("Goodnight.", ((JSONObject) testFwd.get("data")).get("message"));
//        assertEquals("OK", testFwd.get("result"));
//    }
//
//    @Test
//    public void testHandCommandTurn(){
//        Robot testRobot = new Robot("turn", 5, 5, null);
//        JSONObject testFwd = CommandHandler.handleCommand("turn", new String[]{"left"}, testRobot, "turn");
//
//        assertEquals("OK", testFwd.get("result"));
//        assertEquals("Done", ((JSONObject) testFwd.get("data")).get("message"));
//
//        testFwd = CommandHandler.handleCommand("turn", new String[]{"up"}, testRobot, "turn");
//
//        assertEquals("ERROR", testFwd.get("result"));
//        assertEquals("Could not parse arguments.", ((JSONObject) testFwd.get("data")).get("message"));
//    }
//
//    @Test
//    public void testHandleCommandWrong(){
//        JSONObject testFwd = CommandHandler.handleCommand("tatakai", new String[]{"TATAKAI"}, new Robot("Eren", 5, 5, null), "Eren");
//        assertEquals("Unsupported command.", ((JSONObject)testFwd.get("data")).get("message"));
//    }
//
//    @Test
//    public void testMovementCorrect(){
//        Robot testRobot = new Robot("move", 5, 5, null);
//        JSONObject test = Movement.movementJSON(true, new String[]{"12"}, testRobot);
//
//        assertNotNull(test);
//        assertNotNull(test.get("result"));
//        assertNotNull(test.get("data"));
//        assertNotNull(test.get("state"));
//
//        assertEquals("OK", test.get("result"));
//
//        JSONObject state = (JSONObject) test.get("state");
//        int[] pos = (int[]) state.get("position");
//        assertEquals(0, pos[0]);
//        assertEquals(12, pos[1]);
//
//        test = Movement.movementJSON(false, new String[]{"3"}, testRobot);
//        state = (JSONObject) test.get("state");
//        pos = (int[]) state.get("position");
//        assertEquals(0, pos[0]);
//        assertEquals(9, pos[1]);
//
//        test = Movement.movementJSON(true, new String[]{"-9"}, testRobot);
//        state = (JSONObject) test.get("state");
//        pos = (int[]) state.get("position");
//        assertEquals(0, pos[0]);
//        assertEquals(0, pos[0]);
//
//        test = Movement.movementJSON(false, new String[]{"-5"}, testRobot);
//        state = (JSONObject) test.get("state");
//        pos = (int[]) state.get("position");
//        assertEquals(0, pos[0]);
//        assertEquals(5, pos[1]);
//    }
//
//    @Test
//    public void testMovementWrongCommand(){
//        JSONObject test = Movement.movementJSON(true, new String[]{"nope"}, new Robot("fail", 5, 5, null));
//
//        assertNotNull(test);
//        assertNull(test.get("robot"));
//        assertNull(test.get("command"));
//
//        assertEquals("Could not parse arguments.", ((JSONObject) test.get("data")).get("message"));
//    }
//
//    @Test
//    public void testTurnCorrect(){
//        Robot testRobot = new Robot("testRbt", 5, 5, null);
//        JSONObject test = Turn.turnJSON(new String[]{"left"}, testRobot);
//
//        assertNotNull(test);
//        assertNotNull(test.get("result"));
//        assertNotNull(test.get("data"));
//        assertNotNull(test.get("state"));
//
//        assertEquals("OK", test.get("result"));
//
//        JSONObject state = (JSONObject) test.get("state");
//        assertEquals("WEST", state.get("direction"));
//
//        Turn.turnJSON(new String[]{"right"}, testRobot);
//        test = Turn.turnJSON(new String[]{"right"}, testRobot);
//        state = (JSONObject) test.get("state");
//        assertEquals("EAST", state.get("direction"));
//    }
//
//    @Test
//    public void testTurnWrongCommand(){
//        JSONObject test = Turn.turnJSON(new String[]{"haibo"}, new Robot("fail", 5, 5, null));
//
//        assertNotNull(test);
//        assertNull(test.get("robot"));
//        assertNull(test.get("command"));
//
//        assertEquals("Could not parse arguments.", ((JSONObject)test.get("data")).get("message"));
//    }
//
//    @Test
//    public void testLaunchCorrect(){
//        Robot testRobot = new Robot("placeholder", 5, 5, null);
//        JSONObject test = Launch.launchJSON(new String[]{"SniperMask", "5", "5"}, testRobot, "maverick");
//
//        assertNotNull(test);
//        assertNotNull(test.get("result"));
//        assertNotNull(test.get("data"));
//        assertNotNull(test.get("state"));
//
//        JSONObject data = (JSONObject) test.get("data");
//        assertEquals(testRobot.getName(), "maverick");
//        assertEquals(100, data.get("visibility"));
//        assertEquals(3, data.get("mine"));
//        assertEquals(3, data.get("reload"));
//        assertEquals(3, data.get("repair"));
//        assertEquals(5, data.get("shields"));
//    }
//
//    @Test
//    public void testLaunchWrong(){
//        JSONObject test = Launch.launchJSON(new String[]{"crunch"}, new Robot("fail", 5, 5, null), "please-fail");
//
//        assertNotNull(test);
//        assertEquals(test.get("result"), "ERROR");
//        assertEquals("Could not launch robot. Someone shot it down lmao.", ((JSONObject)test.get("data")).get("message"));
//    }
//}
//
