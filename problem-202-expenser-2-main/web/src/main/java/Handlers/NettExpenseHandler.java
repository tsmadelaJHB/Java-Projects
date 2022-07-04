package Handlers;

import io.javalin.http.Context;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import dataStore.model.Person;


import java.util.Map;
import kong.unirest.json.JSONObject;

public class NettExpenseHandler {
    public static final String PATH = "/home";
    public static final String EXPENSE_SERVER = "http://localhost:8080/";
    public static final String CLAIMS_SERVER = "http://localhost:8081/";
    public static final String RATINGS_SERVER = "http://localhost:8082/";

    public static void renderHomePage(Context context){
        Person currentPerson = context.sessionAttribute("user");

        HttpResponse<String> response1 =  Unirest.get(EXPENSE_SERVER +
                "/expense/{email=value}").header("user", currentPerson.getEmail())
                .routeParam("email=value",currentPerson.getEmail())
                .asString();

//        JSONObject json1 = new JSONObject(response1);
//
//        System.out.println(json1);


        HttpResponse<String> response2 = Unirest.get(EXPENSE_SERVER +
                "/person/{email=value}")
                .header("user", currentPerson.getEmail())
                .routeParam("email=value", currentPerson.getEmail()).asString();

        JSONObject json2 = new JSONObject(response2);


        HttpResponse<String> response3 = Unirest.get(CLAIMS_SERVER +
                "/claims/from/{email}?{settled=true}").header("user", currentPerson.getEmail())
                .routeParam("settled=value","true")
                .routeParam("email",currentPerson.getEmail()).asString();

        JSONObject json3 = new JSONObject(response3);


        HttpResponse<String> response4 = Unirest.get(CLAIMS_SERVER +
                "/claims/by/{email}?{settled=value")
                .routeParam("email",currentPerson.getEmail())
                .routeParam("settled=value","true")
                .header("user", currentPerson.getEmail()).asString();

        JSONObject json4 = new JSONObject(response4);



        Map<String, Object> viewModel =
                Map.of("expenses", response1,
                       "totalExpenses", json2.get("ExpensesAmountSum"),
                       "owedToOthers", response4,
                       "totalIOwe", json2.get("UnsettledClaimsSum"),
                       "owedToMe", response3,
                       "totalOwedToMe", json2.get("totalOwedToMe"),
                       "nettExpenses", json2.get("NetExpenseSum"));

        context.render("home.html", viewModel);
    }
}