package Handlers;

import dataStore.model.Person;
import io.javalin.http.Context;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

public class RatingsHandler {

    public static JSONObject getRatings(Context context) {
        Person currentPerson =context.sessionAttribute("user");
        HttpResponse<String> response = Unirest.get("http://localhost:8084/ratings?{by=value}")
                .routeParam("by=value","value").asString();

                JSONObject json1 = new JSONObject(response);
        return json1;
    }

}
