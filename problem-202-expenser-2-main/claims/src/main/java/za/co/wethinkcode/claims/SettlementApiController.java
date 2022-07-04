package za.co.wethinkcode.claims;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import org.eclipse.jetty.util.ajax.JSON;
import za.co.wethinkcode.claims.app.db.DataRepository;
import za.co.wethinkcode.claims.app.model.Claim;
import za.co.wethinkcode.claims.app.model.Settlement;

import java.time.LocalDate;
import java.util.*;

public class SettlementApiController {
    public static final String SETTLE_CLAIM_PATH = "/settlement";

    public static void submitSettlement(Context context) {
        JSONObject object = new JSONObject(context.body());
        UUID claimId = UUID.fromString(Objects.requireNonNull(object.getString("id")));
        Optional<Claim> maybeClaim = DataRepository.getInstance().getClaim(claimId);
        if (maybeClaim.isEmpty()) {
            context.status(HttpCode.BAD_REQUEST);
            context.result("Invalid claim");
        }else {
            Claim claim = maybeClaim.get();
            System.out.println(claim);
            System.out.println(claim.getExpenseId());
            String GET_EXPENSE = "http://localhost:8080/expenses/"+ claim.getExpenseId();
            System.out.println(GET_EXPENSE);
            HttpResponse<JsonNode> response = Unirest.get(GET_EXPENSE).asJson();
            if (response.getStatus() == 200){
                HashMap<String, String> newExpense= new HashMap<String, String>();
                newExpense.put("date", LocalDate.now().toString());
                newExpense.put("description",response.getBody().getObject().get("description").toString());
                newExpense.put("amount",claim.getAmount().toString());
                HashMap<String, String> newPerson= new HashMap<String, String>();
                newPerson.put("email", claim.getClaimedFrom());
                HashMap<String, HashMap<String,String>> expenseMap = new HashMap<String,HashMap<String, String>>();
                expenseMap.put("expense",newExpense);
                expenseMap.put("person", newPerson);
                HttpResponse<JsonNode> expense = Unirest.post("http://localhost:8080/expenses/")
                        .body(expenseMap)
                        .asJson();
                Settlement settlement = claim.settleClaim(LocalDate.now());
                DataRepository.getInstance().addSettlement(settlement);
                DataRepository.getInstance().updateClaim(claim);
                context.status(HttpCode.OK);
                context.result("Claim Settled");
            }else {
                context.status(HttpCode.NOT_IMPLEMENTED);
                context.result("Claim Could not be settled");
            }
        }

    }
}
