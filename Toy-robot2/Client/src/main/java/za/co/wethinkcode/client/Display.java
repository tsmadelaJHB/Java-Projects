package za.co.wethinkcode.client;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

public class Display {

    public static String Direction = "North";
    public static String position = "[0,0]";
    public static String status = "Normal";
    public static int shots = 10;
    public static List<int[]> obstacles;
    public static List<int[]> pits;
    public static String lookString = "";
    public static String message = "";
    public static List<String> look = new ArrayList<>();

    public Display(){
        Direction = "North";
        position = "[0,0]";
        status = "Normal";
        shots = 10;
    }

    /**
     * Clear the terminal and display the robot status, shots,
     * position, and direction
     */
    public void displayGame(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.format(
                "--------------------------------------------------\n"+
                        "* Status: " + status + "     Shots: "+ shots +"\n"+
                        "* Position :" + position + "    Direction: " + Direction + "\n"+
                        "* \n" + "--------------------------------------------------\n"
        );

        System.out.format(
                "--------------------------------------------------\n"+
                        "* Message: " + message + "\n* Look: " + lookString + "\n" +
                        "--------------------------------------------------\n"
        );

        lookString = "";
        message = "";
    }
    
    /**
     * Update the state of the current object data
     * @param obj: the object to extract data from
     */
    public void update(JSONObject obj){
        checkIfLooking(obj.get("data"));
        checkIfShooting(obj.get("data"));
        checkObstaclesPits(obj);
        checkState((JSONObject) obj.get("state"));
    }

    private void checkState(JSONObject state){
        if (state != null){
            Direction = (String) state.get("direction");
            
            int[] pos = (int[]) state.get("position");
            position = String.format("[%d,%d]", pos[0], pos[1]);
            
            status = (String) state.get("status");
            shots = (int) state.get("shots");
        }
    }

    @SuppressWarnings("unchecked")
    private void checkObstaclesPits(JSONObject obj){
        List<int[]> newObstacles = (List<int[]>) obj.get("obstacles"); // getting obstacles from response
        List<int[]> newPits = (List<int[]>) obj.get("pits");

        if (newObstacles != null){
            obstacles = newObstacles;
        }else{
            obstacles = new ArrayList<>();
        }

        if (newPits != null){
            pits = newPits;
        }else{
            pits = new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    private void checkIfLooking(Object data){
        
        if (data != null){
            Object listOfObjects = ((JSONObject) data).get("objects");

            lookString = (listOfObjects == null) ? "" : generateLookString((ArrayList<JSONObject>) listOfObjects);
        }
    }


    private void checkIfShooting(Object data){
        
        if (data != null){
            String newMessage = (String) ((JSONObject) data).get("message");

            if (newMessage != null && newMessage.equalsIgnoreCase("hit")){
                message += String.format("Hit the robot %s. It is %s away from you.", 
                            (String) ((JSONObject) data).get("robot"), 
                            (String) ((JSONObject) data).get("distance"));
            }else if (newMessage != null){
                message = newMessage;
            }
        }
    }

    private String generateLookString(ArrayList<JSONObject> list){
        String result = "";
        for (JSONObject item: list){
            String itemString = String.format(" -- There is a %s at a distance of %s in the %s direction from you.%n"
            , item.get("type"), item.get("distance"), item.get("direction"));
            result += itemString;
        }
        look.add(result);

        return result;
    }
}