package za.co.wethinkcode.server.commands;

import org.json.simple.JSONObject;
import za.co.wethinkcode.server.Configure;
import za.co.wethinkcode.server.Robot;
import za.co.wethinkcode.server.world.World;

import java.util.List;

public class Launch extends CommandHandler{
    /**
     * Launch a robot and generate a JSONObject for the response
     */

    public static Boolean sameName = false;
    static JSONObject result = new JSONObject();
    static int count =1;


    /**
     * Launch robots to the world
     * @param argument an object containg all the arguments used for a command
     * @return
     */

    public static JSONObject launchJSON(CommandArgument argument){


        int maxRobots;
        maxRobots = argument.getWorld().getSize()%2 == 0 ? (argument.getWorld().getSize()+1) * (argument.getWorld().getSize() +1) : argument.getWorld().getSize() * argument.getWorld().getSize();
        maxRobots = maxRobots - getRobot(argument.getName()).findObstacles().size();
        try {
            String MyCheck = checking(argument.getName(),maxRobots);
            if(MyCheck.equalsIgnoreCase("same name")){
                throw new InterruptedException();
            }
            if(MyCheck.equalsIgnoreCase("full")){
                throw new InterruptedException();
            }
            results("result", "OK");
            LaunchDummy(argument.getName(),argument.getArgs(),argument.getWorld());

        }
        catch (InterruptedException e){
            Caught(argument.getName());
        }

        return result;
    }

    /**
     * Catching the the try
     * @param name of the robot
     */
    static void Caught(String name){
        Robot rbt = getRobot(name);
        for(Robot r: robotsInWorld){
            if (r.getName().equals(name)){
                sameName = true;
                break;
            }
        }
        if (sameName) {
            generateErrorMessage(result, "Too many of you in this world");
            sameName = false;
        }
        else if (count > rbt.getWorldSize()) {
            results("result", "ERROR");
            generateErrorMessage(result, "No more space in this world");

        }
    }

    /**
     * Checks if the robot is in the world
     * @param name of the robot
     * @param maxRobots allowed in the world
     * @return string
     */
    static String checking(String name,int maxRobots){
        for (Robot existing : robotsInWorld){
            //if the name is already in the world
            if (existing.getName().equals(name)){
                return "Same name";
            }
            count++;
            //if maximum of robots is reached return full
            if(count >= maxRobots && !existing.getName().equals(name)){
                return "full";
            }
            else
                return "launch";

        }
        return "launch";
    }


    /**
     * Launching the dummy robot
     * @param name of the robot
     * @param args arguments
     * @param world the world
     */

    static void LaunchDummy(String name,String[] args,World world){

        Robot rbt = getRobot(name);
        if(rbt.getName().equals("dummy") && rbt.getShots() == -1 && rbt.getShields() ==-1){
            Robot robot = new Robot(name, Integer.parseInt(args[2]), Integer.parseInt(args[1]), world, world.getSize());
            robot.setType(args[0]);
            robot.setMaxShields(Integer.parseInt(args[1]));
            robot.setMaxShots(Integer.parseInt(args[2]));
            robotsInWorld.add(robot);

            Configure.setValues(robot);

            result.put("data",generateData(robot));
            result.put("state", generateState(false, robot));
        }
    }

    /**
     * put strings in a JSON
     * @param res result
     * @param message to be placed
     * @return
     */
    static JSONObject results(String res, String message){
        result.put(res,message);
        return result;
    }


    /**
     * Generate a data JSONObject based on the robot
     * @param rbt: the robot to extract data from
     * @return JSONObject: an object containing the robot position,
     * visibility, reload time, repair time, mine placement time, and shield points
     */
    @SuppressWarnings("unchecked")
    private static JSONObject generateData(Robot rbt){
        JSONObject data = new JSONObject();

        int[] arr = rbt.getPosition();
        int x = arr[0];
        int y = arr[1];


        data.put("position", List.of(x, y));
        data.put("visibility", rbt.getVisibility());
        data.put("reload", rbt.getReload());
        data.put("repair", rbt.getRepair());
        data.put("mine", rbt.getMine());
        data.put("shields", rbt.getShields());

        return data;
    }
}
