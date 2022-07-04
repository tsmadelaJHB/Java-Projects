package za.co.wethinkcode.weshare.settle;

import io.javalin.http.Context;
import za.co.wethinkcode.weshare.app.db.DataRepository;
import za.co.wethinkcode.weshare.app.model.Claim;
import za.co.wethinkcode.weshare.app.model.Person;
import za.co.wethinkcode.weshare.nettexpenses.NettExpensesController;

import java.util.Map;
import java.util.UUID;

/**
 * Controller for handling calls from form submits for Claims
 */
public class SettlementViewController {
    public static final String PATH = "/settleclaim";
    public static final String ROOT_PATH = "/index.html";

    //    DataRepository database = new DataRepository() {
//        @Override
//        public Person addPerson(Person person) {
//            return null;
//        }
//
//        @Override
//        public void removePerson(Person person) {
//
//        }
//
//        @Override
//        public void updatePerson(Person updatedPerson) {
//
//        }
//
//        @Override
//        public Optional<Person> findPerson(String email) {
//            return Optional.empty();
//        }
//
//        @Override
//        public ImmutableList<Person> allPersons() {
//            return null;
//        }
//
//        @Override
//        public ImmutableList<Expense> getExpenses(Person belongsTo) {
//            return null;
//        }
//
//        @Override
//        public ImmutableList<Expense> findExpensesPaidBy(Person person) {
//            return null;
//        }
//
//        @Override
//        public double getTotalExpensesFor(Person person) {
//            return 0;
//        }
//
//        @Override
//        public double getNettExpensesFor(Person person) {
//            return 0;
//        }
//
//        @Override
//        public Optional<Expense> getExpense(UUID id) {
//            return Optional.empty();
//        }
//
//        @Override
//        public Expense addExpense(Expense expense) {
//            return null;
//        }
//
//        @Override
//        public void removeExpense(Expense expense) {
//
//        }
//
//        @Override
//        public void updateExpense(Expense updatedExpense) {
//
//        }
//
//        @Override
//        public Optional<Claim> getClaim(UUID id) {
//            return Optional.empty();
//        }
//
//        @Override
//        public ImmutableList<Claim> getClaims() {
//            return null;
//        }
//
//        @Override
//        public ImmutableList<Claim> getClaimsBy(Person claimedBy, boolean onlyUnsettled) {
//            return null;
//        }
//
//        @Override
//        public ImmutableList<Claim> getClaimsFrom(Person claimedFrom, boolean onlyUnsettled) {
//            return null;
//        }
//
//        @Override
//        public ImmutableList<Claim> findUnsettledClaimsClaimedFrom(Person person) {
//            return null;
//        }
//
//        @Override
//        public double getTotalUnsettledClaimsClaimedFrom(Person person) {
//            return 0;
//        }
//
//        @Override
//        public List<Claim> findUnsettledClaimsClaimedBy(Person person) {
//            return null;
//        }
//
//        @Override
//        public double getTotalUnsettledClaimsClaimedBy(Person person) {
//            return 0;
//        }
//
//        @Override
//        public Claim addClaim(Claim claim) {
//            return null;
//        }
//
//        @Override
//        public void removeClaim(Claim claim) {
//
//        }
//
//        @Override
//        public void updateClaim(Claim updatedClaim) {
//
//        }
//
//        @Override
//        public Settlement addSettlement(Settlement settleClaim) {
//            return null;
//        }
//
//        @Override
//        public void dropExpenses() {
//
//        }
//
//        @Override
//        public void dropClaims() {
//
//        }
//
//        @Override
//        public void dropSettlements() {
//
//        }
//    };

    public static void renderSettleClaimForm(Context context){

        DataRepository db = DataRepository.getInstance();

        String username = context.formParam("settleId");

        UUID uuid = UUID.fromString(username);

        Claim claim = db.getClaim(uuid).get();




//
//        final Person person = DataRepository.getInstance().addPerson(new Person(username));
//        context.sessionAttribute("user", person);
//        Object db = DataRepository.getInstance().findUnsettledClaimsClaimedBy(person);





        //Object claim = DataRepository.getInstance().getTotalUnsettledClaimsClaimedBy();

        Map<String, Object> viewModel = Map.of(
                "description",claim.getDescription(),
                "email",claim.getClaimedFrom(),
                "due_date",claim.getDueDate(),
                "claim_amount",claim.getAmount()



        );
        //context.render("home.html", viewModel);
        context.render("settleclaim.html", viewModel);
        return;

    }

    public static void submitSettlement(Context context) {

        String username = context.formParam("email");
        final Person person = DataRepository.getInstance().addPerson(new Person(username));
        context.sessionAttribute("user", person);
        DataRepository.getInstance().dropClaims();
        context.redirect(NettExpensesController.PATH);

    }

}