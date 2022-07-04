package Handlers.claim;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import dataStore.model.Person;
import io.javalin.http.Context;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

/**
 * Controller for handling calls from form submits for Claims
 */
public class ClaimExpenseHandler {
    public static final String PATH = "/claimexpense";

    public static void renderClaimExpensePage(Context context){
        Person currentPerson = context.sessionAttribute("user");
        UUID expenseId = UUID.fromString(context.queryParam("expenseId"));

        HttpResponse <String> expenseById = Unirest.get("http:localhost:8082/claim/{id}")
                .routeParam("id", String.valueOf(expenseId))
                .header("user", currentPerson.getEmail()).asString();

        context.render("claimexpense.html",
                Map.of("expense", expenseById));
    }
    public static void create(Context context){
        Person currentPerson = context.sessionAttribute("user");
        HashMap<String,String> json = new HashMap<>();

        Unirest.post("http://localhost:8083/claim").body(json).header("user",currentPerson.getEmail());
    }

    public static void updateClaim(Context context){
        Person currentPerson = context.sessionAttribute("user");

        HashMap<String,String> json = new HashMap<>();

        Unirest.put("http://localhost:8083/claim").body(json).header("user", currentPerson.getEmail());

    }

    public static JSONObject getClaimsFrom(Context context){
        Person currentPerson =context.sessionAttribute("user");

        HttpResponse<String> response = Unirest.get("http://localhost:8083/claims/from/{email}?{settled=value}").header("user",currentPerson.getEmail())
        .routeParam("email",currentPerson.getEmail()).routeParam("value","value").asString();

        JSONObject json1 = new JSONObject(response);

        return json1;

    }

    public static JSONObject getClaimsBy(Context context){
        Person currentPerson =context.sessionAttribute("user");

        HttpResponse<String> response = Unirest.get("http://localhost:8083/claims/by/{email}?{settled=value}").header("user",currentPerson.getEmail())
                .routeParam("email",currentPerson.getEmail())
                .routeParam("settled=value","value").asString();

        JSONObject json1 = new JSONObject(response);

        return json1;
        
    }

    public static void createSettlementClaim(Context context) {
        HashMap<String,String> json = new HashMap<>();

        Person currentPerson =context.sessionAttribute("user");
        Unirest.post("http://localhost:8083/settlement").body(json)
                .header("user",currentPerson.getEmail());
    }

    public static JSONObject getTotalClaimValueFrom(Context context) {
        Person currentPerson =context.sessionAttribute("user");
        
        HttpResponse<String> response = Unirest.get("http://localhost:8083/totalclaimvalue/from/{email}")
                .routeParam("email",currentPerson.getEmail()).asString();

        JSONObject json1 = new JSONObject(response);

        return json1;
    }

    public static JSONObject getTotalClaimValueBy(Context context) {
        Person currentPerson =context.sessionAttribute("user");
        
       HttpResponse <String> response = Unirest.get("http://localhost:8083/totalclaimvalue/by/{email}")
                .routeParam("email",currentPerson.getEmail()).asString();

        JSONObject json1 = new JSONObject(response);

        return json1;
    }
}