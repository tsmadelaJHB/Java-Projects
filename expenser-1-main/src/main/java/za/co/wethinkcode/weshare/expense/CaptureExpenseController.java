package za.co.wethinkcode.weshare.expense;

import io.javalin.http.Context;

import java.time.format.DateTimeFormatter;
import java.util.Map;
import za.co.wethinkcode.weshare.app.db.DataRepository;
import za.co.wethinkcode.weshare.app.model.Expense;
import za.co.wethinkcode.weshare.app.model.Person;
import za.co.wethinkcode.weshare.nettexpenses.NettExpensesController;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Controller for handling API calls for Expenses
 */
public class CaptureExpenseController {

    public static final String NEW_EXPENSE_PAGE = "expenseform.html";
    public static final String NEW_EXPENSE_PATH = "/newexpense";

    public static void createExpense(Context context){
        Map<String, Object> viewModel = Map.of();

        context.render(NEW_EXPENSE_PAGE, viewModel);
    }

    public static void saveExpenseData(Context context) {
        LocalDate date = null;
        String dateString = context.formParam("date");
        System.out.println(dateString);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try{
            if (dateString != null || dateString.length() > 0)

                date = LocalDate.parse(dateString,formatter);
        } catch(DateTimeParseException e) {
            LocalDate newDate = LocalDate.parse(dateString.split("-")[0], DateTimeFormatter.ofPattern("ddMMyy"));
            int addYears = Integer.parseInt(dateString.split("-")[2]);
            date = newDate.plusYears(addYears);

        }

        Person currentPerson = context.sessionAttribute("user");
        double amount = Double.parseDouble(context.formParam("amount"));
        String description = context.formParam("description");
        DataRepository.getInstance().addExpense(new Expense(amount,date,description,currentPerson));
        context.redirect(NettExpensesController.PATH);
    }
}
