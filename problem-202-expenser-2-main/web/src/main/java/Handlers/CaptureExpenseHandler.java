package Handlers;


import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import dataStore.model.Person;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Objects;

/**
 * Controller for handling API calls for Expenses
 */
public class CaptureExpenseHandler {
    public static final String PATH = "/expenses";

    public static void createExpense(Context context){

        Person currentPerson = context.sessionAttribute("user");

        //TODO proper server-side validation of form params
        String description = context.formParam("description");
        double amount;
        try {
            amount = Double.parseDouble(Objects.requireNonNull(context.formParam("amount")));
        } catch (NumberFormatException e){
            context.status(HttpCode.BAD_REQUEST);
            context.result("Invalid amount specified");
            return;
        }
        LocalDate date;
        try {
            date = LocalDate.parse(Objects.requireNonNull(context.formParam("date")));
        } catch (DateTimeException e){
            context.status(HttpCode.BAD_REQUEST);
            context.result("Invalid due date specified");
            return;
        }
        HashMap<String,String> dataMap = new HashMap<>();
        dataMap.put("amount",String.valueOf(amount));
        dataMap.put("date",String.valueOf(date));
        dataMap.put("description",description);
        dataMap.put("person",String.valueOf(currentPerson));

        JSONObject json = new JSONObject(dataMap);

        System.out.println(json);


        Unirest.post("http://localhost:8080/expenses")
        .header("user", currentPerson.getEmail())
        .body(json);

        context.redirect("/home");
    }

    public static JSONObject getAllExpense(Context context){
        Person currentPerson = context.sessionAttribute("user");

        HttpResponse<String> response = Unirest.get("http://localhost:8081/expenses/{email=value}")
                .header("user", currentPerson.getEmail())
                .routeParam("email=value",currentPerson.getEmail()).asString();

        JSONObject json1 = new JSONObject(response);

        return json1;
    }

    public static JSONObject getAllTotals(Context context){
        Person currentPerson = context.sessionAttribute("user");

        HttpResponse<String> response = Unirest.get("http://localhost:8081/person/{email}")
                .header("user", currentPerson.getEmail())
                .routeParam("email", currentPerson.getEmail()).asString();

        JSONObject json1 = new JSONObject(response);

        return json1;
    }

    public static JSONObject getExpenseById(Context context){
        Person currentPerson = context.sessionAttribute("user");
        int id;

        HttpResponse<String> response = Unirest.get("http://localhost:8081/expensee/{id}")
                .header("user", currentPerson.getEmail())
                .routeParam("id", "id").asString();

        JSONObject json1 = new JSONObject(response);

        return json1;
    }


}