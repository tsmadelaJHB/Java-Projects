package za.co.wethinkcode.robotworlds.webapi.services;

import io.javalin.http.Context;
import org.json.simple.parser.JSONParser;
import za.co.wethinkcode.robotworlds.AcceptClient;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class RobotCommandHandlerService {

    public static void launchRobot(Context context) {
        JSONParser parser = new JSONParser();
        String command = context.body();
        try {
            AcceptClient acceptClient = new AcceptClient(command);
            context.json(acceptClient.launchRobot());

            JSONObject response = (JSONObject) parser.parse(context.resultString());
            if(response.get("result").equals("OK")){
                context.status(201);
            }else {
                context.status(504);
            }

        } catch (IOException | ParseException ex) {
            ex.printStackTrace();
        }
    }

    public static void otherCommands(Context context){
        String command = context.body();
        JSONParser parser = new JSONParser();
        try{
            JSONObject jsonObject = (JSONObject) parser.parse(command);
            AcceptClient acceptClient = new AcceptClient(command);
            context.json(acceptClient.getCommandResponse(acceptClient.findRobot(jsonObject.get("robot").toString()), command));

        }catch(IOException | ParseException ex) {
            ex.printStackTrace();
        }
    }
}
