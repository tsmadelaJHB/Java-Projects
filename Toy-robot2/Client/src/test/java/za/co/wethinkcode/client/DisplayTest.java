package za.co.wethinkcode.client;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

public class DisplayTest {
    
    @SuppressWarnings("unchecked")
    private JSONObject generateState(){
        JSONObject state = new JSONObject();
        state.put("direction", "South");
        state.put("position", new int[]{93, 20});
        state.put("status", "WhoKnows");
        state.put("shots", 2);
        return state;
    }

    @Test
    public void testDisplayGame(){
        PrintStream temp = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream outputStream = new PrintStream(out);
        System.setOut(outputStream);

        Display test = new Display();
        test.displayGame();
        String printed = out.toString();

        assertEquals("\033[H\033[2J--------------------------------------------------\n" +
        "* Status: Normal     Shots: 10\n" +
        "* Position :[0,0]    Direction: North\n" +
        "* \n" + 
        "--------------------------------------------------\n" +
        "--------------------------------------------------\n"+
        "* Message: \n* Look: \n" +
        "--------------------------------------------------\n", printed);
        System.setOut(temp);
    }

    @Test
    public void testUpdateValid(){
        Display test = new Display();
        JSONObject sampleData = new JSONObject();
        sampleData.put("state", generateState());

        test.update(sampleData);

        PrintStream temp = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream outputStream = new PrintStream(out);
        System.setOut(outputStream);

        test.displayGame();
        String printed = out.toString();

        assertEquals("\033[H\033[2J--------------------------------------------------\n"+
        "* Status: WhoKnows     Shots: 2\n"+
        "* Position :[93,20]    Direction: South\n"+
        "* \n" + "--------------------------------------------------\n" +
        "--------------------------------------------------\n"+
        "* Message: \n* Look: \n" +
        "--------------------------------------------------\n", printed);
        System.setOut(temp);
    }
}
