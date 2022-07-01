package za.co.wethinkcode.server.commands;

import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
public class State extends CommandHandler{
    
    public static JSONObject stateJSON(CommandArgument arg){
        JSONObject result = new JSONObject();
        JSONObject data = new JSONObject();
//        try {
//            if (!arg.getRbt().getName().equals(arg.getName())){
//                System.out.println(!arg.getRbt().getName().equals(arg.getName()));
//                throw new InterruptedException();
//            }
//        result.put("result", "OK");
//
//        data.put("message", "Done");
//        result.put("data", data);
//        result.put("state", generateState(false, arg.getRbt()));
//        }
//        catch (InterruptedException e){
//            result.put("result", "ERROR");
//            generateErrorMessage(result, "Robot does not exist");
//        }
        result.put("result", "OK");

        data.put("message", "Done");
        result.put("data", data);
        result.put("state", generateState(false, getRobot(arg.getName())));
        return result;
    }
}
