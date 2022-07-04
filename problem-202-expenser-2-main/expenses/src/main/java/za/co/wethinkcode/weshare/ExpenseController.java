package za.co.wethinkcode.weshare;
import io.javalin.http.HttpCode;
import io.javalin.http.Context;
import kong.unirest.HttpStatus;
import kong.unirest.json.JSONObject;
import za.co.wethinkcode.weshare.app.db.DataRepository;
import za.co.wethinkcode.weshare.app.model.Expense;
import za.co.wethinkcode.weshare.app.model.Person;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class ExpenseController {

    public static void createExpense(Context context) {
        JSONObject jsonString = new JSONObject(context.body());
        JSONObject expenseJSONObject = new JSONObject(jsonString.get("expense").toString());
        JSONObject personJSONObject = new JSONObject(jsonString.get("person").toString());

        double amount = Double.parseDouble(expenseJSONObject.get("amount").toString());
        String dateString = expenseJSONObject.get("date").toString();
        String  description = expenseJSONObject.get("description").toString();

       Person currentPerson = new Person(personJSONObject.get("email").toString());

        LocalDate date;
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeException e){
            context.status(HttpCode.BAD_REQUEST);
            context.result("Invalid due date specified");
            return;
        }

        Expense expense = new Expense( amount, date, description, currentPerson );
        DataRepository.getInstance().addExpense(expense);
        addUserToDB(currentPerson);

        context.status(HttpStatus.OK);
        context.json(expense);
    }

    public static void getExpense(Context context) {
        String email = context.queryParam("email");
        if (email != null) {
            Optional<Person> currentPerson = DataRepository.getInstance().findPerson(email);
            List<Expense> expenses = DataRepository.getInstance().getExpenses(currentPerson.get());
            context.json(expenses.toString());
        } else {
            context.status(HttpStatus.BAD_REQUEST);
            context.result("{error: Missing email}");
        }
    }

    public static void getExpensesById(Context context) {

        String id = context.pathParam("id");
        System.out.println(id);
        Optional<Expense> expenses = DataRepository.getInstance().getExpense(UUID.fromString(id));
        context.json(Map.of("id",expenses.get().getId(),
                "description", expenses.get().getDescription(),
                "person",expenses.get().getPaidBy(),
                "amount",expenses.get().getAmount(),
                "date", expenses.get().getDate()));

    }

    public static void getPerson(Context context) {

        String email = context.pathParam("email=value").split("=")[1];
        Optional<Person> person = DataRepository.getInstance().findPerson(email);
        context.json(person.get());
            context.json(Map.of("ExpensesAmountSum", DataRepository.getInstance().getTotalExpensesFor(person.get()),
                    "NetExpenseSum", DataRepository.getInstance().getNettExpensesFor(person.get()),
                    "UnsettledClaimsSum", DataRepository.getInstance().getTotalUnsettledClaimsClaimedBy(person.get())));
    }

    private static void addUserToDB(Person person) {
        List<Person> listPersons = DataRepository.getInstance().allPersons();
        boolean isInDb = false;
        for (Person dbPerson : listPersons) {
            if (dbPerson.getEmail().equalsIgnoreCase(person.getEmail())) {
                isInDb = true;
            }
        }
        if(!isInDb) {
            DataRepository.getInstance().addPerson(person);
        }
    }
}
