package za.co.wethinkcode.weshare.claim;

import io.javalin.http.Context;
import org.eclipse.jetty.util.ajax.JSON;
import org.json.JSONObject;
import za.co.wethinkcode.weshare.app.db.DataRepository;
import za.co.wethinkcode.weshare.app.model.Claim;
import za.co.wethinkcode.weshare.app.model.Expense;
import za.co.wethinkcode.weshare.app.model.Person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * Controller for handling API calls for Claims
 */
public class ClaimsApiController {

    public static final String API_PATH = "/api/claims";
    public static void create(Context context) {
        Map<String, Object> viewModel = new HashMap<>();

        UUID uuid = UUID.fromString(context.queryParam("expenseId"));


        JSONObject message = new JSONObject(context.body());
        DataRepository dataRepository = DataRepository.getInstance();

        Expense expense =dataRepository.getExpense(uuid).get();

        Double amount = Double.parseDouble(message.getString("claimAmount"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date;
        String dateString = message.getString("dueDate");
        int addYears = Integer.parseInt(dateString.split("-")[2]);
        try{
                date = LocalDate.parse(dateString,formatter);
        } catch(DateTimeParseException e) {
            String dateToFormat = dateString.split("-")[0];
            if (dateToFormat.length()==5){
                String formattedDate = dateToFormat + "0";
                LocalDate newDate = LocalDate.parse(formattedDate, DateTimeFormatter.ofPattern("ddMMyy"));
                date = newDate.plusYears(addYears);
            }else {
                LocalDate newDate = LocalDate.parse(dateToFormat, DateTimeFormatter.ofPattern("ddMMyy"));
                date = newDate.plusYears(addYears);
            }
        }
        Claim claim = expense.createClaim(new Person(message.getString("claimFromWho")),amount,date);

        dataRepository.addClaim(claim);
        viewModel.put("claimFromWho",claim.getClaimedFrom().getEmail());
        viewModel.put("claimAmount", claim.getAmount());
        viewModel.put("dueDate", claim.getDueDate().toString());

        context.json(viewModel);

        // context.redirect("/claimexpenses?expenseId=" +uuid);

    }
}