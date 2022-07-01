package za.co.wethinkcode.server.commands;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import za.co.wethinkcode.server.Robot;

import java.util.List;

@SuppressWarnings("unchecked")
public class Look extends CommandHandler{
    
    /**
     * Generate the response object for look

     * @return JSONObject: the data for the response
     */
    public static JSONObject lookJSON(CommandArgument arg){
        JSONObject response = new JSONObject();
        Robot rbt = getRobot(arg.getName());
        response.put("result", "OK");
        response.put("data", lookAround(rbt));
        response.put("state", generateState(false, rbt));

        return response;
    }

    /**
     * Create a JSON of every item around the given robot
     * @param rbt: the robot to look around
     * @return JSONObject: a JSON with a list of objects mapped to a key of "objects".
     *         Every objects
     */
    private static JSONObject lookAround(Robot rbt){
        JSONObject data = new JSONObject();
        ArrayList<JSONObject> objects = new ArrayList<>(0);
        for (int[] pos : rbt.findObstacles()){
            JSONObject singleObject = new JSONObject();
            singleObject.put("direction", direction(pos, rbt.getPosition()));
            singleObject.put("type", "OBSTACLE");
            singleObject.put("distance", distance(pos, rbt.getPosition()));

            objects.add(singleObject);
        }

        for (int[] pos : rbt.findPits()){
            JSONObject singleObject = new JSONObject();
            singleObject.put("direction", direction(pos, rbt.getPosition()));
            singleObject.put("type", "pit");
            singleObject.put("distance", distance(pos, rbt.getPosition()));

            objects.add(singleObject);
        }

        for (int[] pos : findRobots(rbt)){
            JSONObject singleObject = new JSONObject();
            singleObject.put("direction", direction(pos, rbt.getPosition()));
            singleObject.put("type", "ROBOT");
            singleObject.put("distance", distance(pos, rbt.getPosition()));

            objects.add(singleObject);

        }

        data.put("visibility","1");
        int[] arr = rbt.getPosition();

        int x = arr[0];
        int y = arr[1];


        data.put("position", List.of(x, y));
        data.put("objects", objects);
        return data;
    }

    /**
     * Find the absolute distance between the robot and the obstacle
     * @param obstacle: the position of the obstacle, [x, y]
     * @param robot: the position of the robot, [x, y]
     * @return String: the absolute distance between the two as a string, "33"
     */
    private static String distance(int[] obstacle, int[] robot){
        double dist = Math.sqrt( Math.pow(obstacle[0] - robot[0], 2) + Math.pow(obstacle[1] - robot[1], 2));

        return (int) Math.abs(dist) + "";
    }

    /**
     * Create the string that tells you where obstacle is in relation to robot
     * @param obstacle: the position of the obstacle, [x, y]visibility
     * @param robot: the position of the robot, [x, y]
     * @return String: the string describing where the obstacle is from the robot
     * (NORTH, EAST, SOUTH, WEST, or a combination of two)
     */
    private static String direction(int[] obstacle, int[] robot){
        boolean left = obstacle[0] < robot[0];
        boolean right = obstacle[0] > robot[0];

        String result = "";

        result += "";
        result += left ? "WEST" : (right ? "EAST" : "");
//        System.out.println("Result "+result);
        return result;
    }


    public static List<int[]> findRobots(Robot rbt){

        List<int[]> robotList = new ArrayList<>();
        for (Robot o : robotsInWorld) {
            if (Math.abs(o.getPosition()[0] - rbt.getPosition()[0]) <= rbt.getVisibility()
                    && Math.abs(o.getPosition()[1] - rbt.getPosition()[1]) <= rbt.getVisibility()) {
                robotList.add(o.getPosition());
            }
        }
        return robotList;
    }
}
