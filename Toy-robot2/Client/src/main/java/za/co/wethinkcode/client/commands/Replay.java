package za.co.wethinkcode.client.commands;

import org.json.simple.JSONObject;

import za.co.wethinkcode.client.Robot;

import java.util.Arrays;

public class Replay extends CommandHandler{
    @SuppressWarnings("unchecked")
    public static JSONObject replayJSON(String command, Robot robot){
        JSONObject result = new JSONObject();
        String[] splitCommand = command.split(" ");

        try {
            result.put("robot", robot.getName());
            result.put("command", splitCommand[0]);

            if (command.equals("replay")) {
                result.put("arguments", new String[]{splitCommand[0]});
            } else if (command.equals("replay reversed")) {
                result.put("arguments", new String[]{splitCommand[1]});
            } else if (command.equals("replay "+splitCommand[1])) {
                if (splitCommand[1].contains("-")) {
                    String[] range = splitCommand[1].split("-");
                    result.put("arguments", new String[]{Arrays.toString(range)});
                } else {
                    result.put("arguments", new String[]{splitCommand[1]});
                }
            } else if (command.equals("replay reversed "+splitCommand[2])){
                if (splitCommand[2].contains("-")) {
                    System.out.println("2");
                    String com = splitCommand[1]+" "+splitCommand[2];
                    String[] range = com.split("-");
                    result.put("arguments", new String[]{Arrays.toString(range)});
                } else {
                    result.put("arguments", new String[]{splitCommand[2]});
                }
            } else if (splitCommand[1].contains("-")){
                result.put("arguments", new String[]{splitCommand[1]});
            }
        }catch (NullPointerException e) {
            generateErrorMessage(result, "Please launch a robot first.");
        }catch (IndexOutOfBoundsException e){
            generateErrorMessage(result, "Please enter '<replay> <number of steps>'.");
        }

        return result;
    }
}
