//package za.co.wethinkcode.server;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.Test;
//
//public class RobotTest {
//
//    private boolean compare(int[] first, int[] second){
//        if (first.length != second.length || first.length != 2)
//            return false;
//        if (first[0] != second[0] || first[1] != second[1])
//            return false;
//        return true;
//    }
//
//    @Test
//    public void testMoveNorth(){
//        Robot dummy = new Robot("spark", 5, 5, null);
//
//        dummy.setPosition(new int[]{33, 1});
//        dummy.setDirection("NORTH");
//
//        assertTrue(dummy.move(13));
//        assertTrue(compare(dummy.getPosition(), new int[]{33, 14}));
//        assertTrue(dummy.move(-42));
//        assertTrue(compare(dummy.getPosition(), new int[]{33, -28}));
//
//        dummy.setDirection("NTH");
//        assertFalse(dummy.move(200));
//        assertTrue(compare(dummy.getPosition(), new int[]{33, -28}));
//    }
//
//    @Test
//    public void testMoveEast(){
//        Robot dummy = new Robot("1:52", 5, 5, null);
//
//        dummy.setPosition(new int[]{17, -47});
//        dummy.setDirection("EAST");
//
//        assertTrue(dummy.move(1));
//        assertTrue(compare(dummy.getPosition(), new int[]{18, -47}));
//        assertTrue(dummy.move(-21));
//        assertTrue(compare(dummy.getPosition(), new int[]{-3, -47}));
//
//        dummy.setDirection("SMTH");
//        assertFalse(dummy.move(200));
//        assertTrue(compare(dummy.getPosition(), new int[]{-3, -47}));
//    }
//
//    @Test
//    public void testMoveWest(){
//        Robot dummy = new Robot("pure", 5, 5, null);
//
//        dummy.setPosition(new int[]{-9, 16});
//        dummy.setDirection("WEST");
//
//        assertTrue(dummy.move(4));
//        assertTrue(compare(dummy.getPosition(), new int[]{-13, 16}));
//        assertTrue(dummy.move(-10));
//        assertTrue(compare(dummy.getPosition(), new int[]{-3, 16}));
//
//        dummy.setDirection("THN");
//        assertFalse(dummy.move(200));
//        assertTrue(compare(dummy.getPosition(), new int[]{-3, 16}));
//    }
//
//    @Test
//    public void testMoveSouth(){
//        Robot dummy = new Robot("ND-South", 5, 5, null);
//
//        dummy.setPosition(new int[]{-20, 0});
//        dummy.setDirection("SOUTH");
//
//        assertTrue(dummy.move(-50));
//        assertTrue(compare(dummy.getPosition(), new int[]{-20, 50}));
//        assertTrue(dummy.move(8));
//        assertTrue(compare(dummy.getPosition(), new int[]{-20, 42}));
//
//        dummy.setDirection("HAI");
//        assertFalse(dummy.move(200));
//        assertTrue(compare(dummy.getPosition(), new int[]{-20, 42}));
//    }
//
//    @Test
//    public void testTurn(){
//        Robot dummy = new Robot("jikelele", 5, 5, null);
//
//        dummy.setPosition(new int[]{3, 4});
//        dummy.setDirection("WEST");
//
//        assertTrue(dummy.turnRobot(false));
//        assertTrue(compare(dummy.getPosition(), new int[]{3, 4}));
//        assertEquals(dummy.getDirection(), "SOUTH");
//
//        assertTrue(dummy.turnRobot(false));
//        assertEquals(dummy.getDirection(), "EAST");
//
//        dummy.setDirection("NORTH");
//        assertTrue(dummy.turnRobot(true));
//        assertEquals(dummy.getDirection(), "EAST");
//
//        assertTrue(dummy.turnRobot(false));
//        assertEquals(dummy.getDirection(), "NORTH");
//
//        dummy.setDirection("PN");
//        assertFalse(dummy.turnRobot(true));
//    }
//}
