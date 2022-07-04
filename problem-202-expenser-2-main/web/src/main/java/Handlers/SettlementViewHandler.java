package Handlers;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import dataStore.db.DataRepository;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

import java.util.*;

/**
 * Controller for handling calls from form submits for Claims
 */
public class SettlementViewHandler {
    public static final String PATH = "/settleclaim";

    public static void renderSettleClaimForm(Context context){
        UUID claimId = UUID.fromString(Objects.requireNonNull(context.queryParam("claimId")));

        HashMap<String,String> settlementMap = new HashMap<>();


         context.render("settleclaim.html", Map.of("claim", settlementMap));

        if (settlementMap.isEmpty()) {
            context.status(HttpCode.BAD_REQUEST);
            context.result("Invalid claim");
        }
    }

    public static void submitSettlement(Context context) {
        UUID claimId = UUID.fromString(Objects.requireNonNull(context.formParam("id")));

        HashMap<String,String> settlementMap = new HashMap<>();

        if (settlementMap.isEmpty()) {
            context.status(HttpCode.BAD_REQUEST);
            context.result("Invalid claim");
            return;
        }
        JSONObject json = new JSONObject(settlementMap);

        Unirest.post("http://localhost:8083/settlement").header("user","currentUser").body(json);


        context.redirect("/home");
    }


}