package za.co.wethinkcode.weshare.claim;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
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
    public static final String PATH = "/claimexpense";

    public static void renderClaimExpensePage(Context context){
        Person currentPerson = context.sessionAttribute("user");
        UUID expenseId = UUID.fromString(context.queryParam("expenseId"));
        HttpResponse <String> expenseById = (HttpResponse<String>) Unirest.get("http:localhost:8082/claim/{id}")
                .routeParam("id", String.valueOf(expenseId))
                .header("user", currentPerson.getEmail());


        Optional<Expense> maybeExpense = DataRepository.getInstance().getExpense(expenseId);
        if (maybeExpense.isEmpty()) {
            context.status(HttpCode.BAD_REQUEST);
            context.result("Invalid expense");
            return;
        }
        Expense expense = maybeExpense.get();
        context.sessionAttribute("expense", expense);
        context.render("claimexpense.html",
                Map.of("newClaim", new Claim( expense, new Person("email@domain.com"), 0.0, LocalDate.now() ),
                        "expense", expenseById));
    }
}