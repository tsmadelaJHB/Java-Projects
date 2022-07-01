package za.co.wethinkcode.server.commands;

import org.json.simple.JSONObject;

import za.co.wethinkcode.server.Robot;

@SuppressWarnings("unchecked")
public class Fire extends CommandHandler{
    
    public static JSONObject fireJSON(CommandArgument arg){
        Robot robot = getRobot(arg.getName());
        JSONObject result = new JSONObject();

        result.put("result", "OK");
        result.put("data", generateData(robot));
        result.put("state", generateState(false, robot));

        return result;
    }

    private static JSONObject generateData(Robot robot){
        JSONObject data = new JSONObject();

        Robot hit = shoot(robot);

        if (hit == null){
            data.put("message", "Miss");
            return data;
        }

        hit.setShields(hit.getShields() - robot.getDamage());

        data.put("message", "Hit");
        data.put("robot", hit.getName());
        data.put("distance", distance(hit, robot) + "");
        data.put("state", generateState(hit.getStatus().equalsIgnoreCase("dead"), hit));
        return data;
    }

    private static int distance(Robot hit, Robot shooter){
        int[] hitPos = hit.getPosition();
        int[] robotPos = shooter.getPosition();

        return (int) Math.sqrt(Math.pow(hitPos[0] - robotPos[0], 2) + Math.pow(hitPos[1] - robotPos[1], 2));
    }

    private static Robot shoot(Robot shooter){
        if (shooter.getShots() <= 0){
            return null;
        }

        shooter.setShots(shooter.getShots()-1);

        switch(shooter.getDirection()){
            case "NORTH":
                for (int i = 1; i < shooter.bulletDistance; i++){
                    for (Robot rbt : robotsInWorld){
                        if (rbt.getPosition()[0] == shooter.getPosition()[0] &&
                        rbt.getPosition()[1] == shooter.getPosition()[1] + i){
                            return rbt;
                        }
                    }
                }
            case "SOUTH":
                for (int i = 1; i < shooter.bulletDistance; i++){
                    for (Robot rbt : robotsInWorld){
                        if (rbt.getPosition()[0] == shooter.getPosition()[0] &&
                        rbt.getPosition()[1] == shooter.getPosition()[1] - i){
                            return rbt;
                        }
                    }
                }
            case "EAST":
                for (int i = 1; i < shooter.bulletDistance; i++){
                    for (Robot rbt : robotsInWorld){
                        if (rbt.getPosition()[0] == shooter.getPosition()[0] + i &&
                        rbt.getPosition()[1] == shooter.getPosition()[1]){
                            return rbt;
                        }
                    }
            }case "WEST":
                for (int i = 1; i < shooter.bulletDistance; i++){
                    for (Robot rbt : robotsInWorld){
                        if (rbt.getPosition()[0] == shooter.getPosition()[0] - i &&
                        rbt.getPosition()[1] == shooter.getPosition()[1]){
                            return rbt;
                        }
                    }
                }
        }

        return null;
    }
}
