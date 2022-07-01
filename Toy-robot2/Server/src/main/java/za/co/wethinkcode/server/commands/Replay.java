package za.co.wethinkcode.server.commands;

import org.json.simple.JSONObject;

import za.co.wethinkcode.server.Robot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Replay extends CommandHandler{
    static int size = CommandHandler.userCommands.size();
    private static Robot rbt;

    //testing replay
    @SuppressWarnings("unchecked")
    public static JSONObject replayJSON(CommandArgument arg){
        Robot rbt = getRobot(arg.getName());
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
        String args = arg.getCommand()+ " " + arg.getArgs()[0];
        String[] arguments = args.split(" ");
        try {
            if (arguments[1].equals("replay")) {
                replay();
            } else if (arguments[1].equals("reversed")) {
                Collections.reverse(userCommands);
                replay();
                Collections.reverse(userCommands);
            } else if (arguments[1].matches("[0-9]+")){
                replay(Integer.parseInt(arguments[1]));
            } else if (arguments[2].matches("[1-9]+")) {
                Collections.reverse(userCommands);
                replay(Integer.parseInt(arguments[1]));
                Collections.reverse(userCommands);
            } else if (arguments[1].contains(",")) {
                replaySelected(arguments[1], arguments[2]);
            } else {
                replayReversedSelected(arguments[2], arguments[3]);
            }

            data.put("message", "Done");

            result.put("result", "OK");
            result.put("data", data);
            result.put("state", generateState(false, rbt));
        }catch (IndexOutOfBoundsException | NumberFormatException e){
            generateErrorMessage(result, "Could not parse arguments.");
        }
        return result;
    }
    public static void replayReversedSelected(String value1, String value2) {
        List<String> replayN = new ArrayList<>();
        Collections.reverse(userCommands);
        value1=value1.replace("[","").replace(",","");
        value2=value2.replace("]","");
        for (int j = Integer.parseInt(value2); j < Integer.parseInt(value1); j++) {
            replayN.add(CommandHandler.userCommands.get(j));
        }

        for (int i = 0; i < Integer.parseInt(value2); i++) {
            String[] c = replayN.get(i).split(" ");

            getCommands(c);
        }
        replayN.clear();
        Collections.reverse(userCommands);
    }

    public static void replaySelected(String value1, String value2) {
        List<String> replayN = new ArrayList<>();
        Collections.reverse(userCommands);
        value1=value1.replace("[","").replace(",","");
        value2=value2.replace("]","");
        for (int j = Integer.parseInt(value2); j < Integer.parseInt(value1); j++) {
            replayN.add(CommandHandler.userCommands.get(j));
        }
        Collections.reverse(replayN);

        for (int i = 0; i < Integer.parseInt(value2); i++) {
            String[] c = replayN.get(i).split(" ");

            getCommands(c);
        }
        replayN.clear();
        Collections.reverse(userCommands);
    }


    public static void replay(int args) {
        List<String> replayN = new ArrayList<>();
        Collections.reverse(userCommands);
        for (int j = 0; j < args; j++) {
            replayN.add(CommandHandler.userCommands.get(j));
        }
        Collections.reverse(replayN);

        for (int i = 0; i < args; i++) {
            String[] c = CommandHandler.userCommands.get(i).split(" ");

            getCommands(c);
        }
        replayN.clear();
        Collections.reverse(userCommands);
    }



    public static void replay() {
        for (int i = 0; i < size; i++) {

            String[] c = CommandHandler.userCommands.get(i).split(" ");
            getCommands(c);
        }
    }

    public static void getCommands(String[] command){
        switch (command[0]) {
            case "forward":
                movementReplayJSON(true, command);
                break;
            case "back":
                movementReplayJSON(false, command);
                break;
            case "turn":
                turnReplayJSON(command);
                break;
            default:
                sprintReplayJSON(true, command);
                break;
        }
    }

@SuppressWarnings("unchecked")
public static void movementReplayJSON(boolean forward, String[] args){
    JSONObject result = new JSONObject();
    JSONObject data = new JSONObject();

    try {
        int multiplier = forward ? 1 : -1;
        int steps = multiplier * Integer.parseInt(args[1]);

        rbt.move(steps);
        data.put("message", "Done");

        result.put("result", "OK");
        result.put("data", data);
        result.put("state", generateState(false, rbt));
    }catch (IndexOutOfBoundsException | NumberFormatException e){
        generateErrorMessage(result, "Could not parse arguments.");
    }
}

    @SuppressWarnings("unchecked")
    public static void turnReplayJSON(String[] args){
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();

        try {
            rbt.turnRobot(args[1].equalsIgnoreCase("right"));
            data.put("message", "Done");

            result.put("result", "OK");
            result.put("data", data);
            result.put("state", generateState(false, rbt));
        }catch (IndexOutOfBoundsException e){
            generateErrorMessage(result, "Could not parse arguments.");
        }
    }

    @SuppressWarnings("unchecked")
    public static void sprintReplayJSON(boolean sprint, String[] args){
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();

        int nrSteps = Integer.parseInt(args[1]);
        try {
            for (int i = 1; i <= nrSteps; i++) {
                int steps = sprint ? i : -1;
                rbt.move(steps);
            }

            data.put("message", "Done");

            result.put("result", "OK");
            result.put("data", data);
            result.put("state", generateState(false, rbt));
        }catch (IndexOutOfBoundsException | NumberFormatException e){
            generateErrorMessage(result, "Could not parse arguments.");
        }
    }
}
