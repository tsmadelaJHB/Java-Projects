package za.co.wethinkcode.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

public class RobotTest {
    
    @SuppressWarnings("unchecked")
    private void generateData(JSONObject data){
        JSONObject state = new JSONObject();

        state.put("direction", "South");
        state.put("position", new int[]{37, 43});
        state.put("status", "Dead");
        state.put("shots", -3);
        state.put("shields", 0);

        data.put("state", state);
    }

    @Test
    public void testName(){
        Robot rbt = new Robot("test name");

        assertEquals(rbt.getName(), "test name");
        rbt.setName("test name...but different");
        assertEquals(rbt.getName(), "test name...but different");
    }

    @Test
    public void testApplyState(){
        Robot rbt = new Robot("OW");
        JSONObject data = new JSONObject();
        JSONObject empty = new JSONObject();

        generateData(data);
        assertFalse(rbt.applyState(empty));
        assertTrue(rbt.applyState(data));
        assertEquals("Position: [37,43]\n" +
        "Direction: South\n" +
        "Shields: 0\n" +
        "Shots: -3\n" + 
        "Status: Dead\n", rbt.toString());
    }
}
