package za.co.wethinkcode.robotworlds.webapi.repositories;

import com.google.gson.JsonObject;
import io.javalin.http.Context;
import za.co.wethinkcode.robotworlds.client.RobotWorldJsonClient;

public class ClientConnectionHandlerService {

    public static void Connect(Context context){

        int PortNumber = context.pathParamAsClass("port", Integer.class).get();

        String ipAddress = context.pathParamAsClass("ip", String.class).get();

        RobotWorldJsonClient client = new RobotWorldJsonClient();
        JsonObject response = new JsonObject();
        try{
            client.connect(ipAddress,PortNumber);

            response.addProperty("result","connected");
            context.status(201);
            context.json(response.toString());
            System.out.println("connected");
        }catch (RuntimeException e){
            response.addProperty("result","Error");
            context.status(504);
            context.json(response.toString());
        }
    }
}
