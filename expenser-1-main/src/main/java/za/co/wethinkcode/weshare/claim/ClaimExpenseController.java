package za.co.wethinkcode.weshare.claim;


import io.javalin.http.Context;

import org.json.JSONObject;
import za.co.wethinkcode.weshare.app.db.DataRepository;
import za.co.wethinkcode.weshare.app.model.Claim;
import za.co.wethinkcode.weshare.app.model.Expense;
import za.co.wethinkcode.weshare.app.model.Person;

import java.time.LocalDate;
import java.util.*;

/**
 * Controller for handling calls from form submits for Claims
 */
public class ClaimExpenseController {

    public static final String PATH = "/claimexpenses";

    public static void renderClaimExpensePage(Context context){
        Map<String, Object> viewModel = new HashMap<>();
        UUID uuid = UUID.fromString(context.queryParam("expenseId"));


        DataRepository dataRepository = DataRepository.getInstance();

        Expense expense = dataRepository.getExpense(uuid).get();

        Set<Claim> claims = expense.getClaims();

        viewModel.put("expense",expense);
        viewModel.put("claims",claims);

        context.render("claimexpense.html", viewModel);


    }


}
