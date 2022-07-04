package za.co.wethinkcode.weshare.nettexpenses;

import io.javalin.http.Context;
import za.co.wethinkcode.weshare.app.db.DataRepository;
import za.co.wethinkcode.weshare.app.model.Person;

import java.util.Map;

public class NettExpensesController {
    public static final String PATH = "/home";

    public static void renderHomePage(Context context){
        
        Person person = context.sessionAttribute("user");
        // Object expp  = exp;
        Map<String, Object> viewModel =
                Map.of("exp",DataRepository.getInstance().getExpenses(person),
        "myExp",DataRepository.getInstance().getTotalExpensesFor(person),
        "oweMe",DataRepository.getInstance().getClaimsBy(person, true),
        "allOweMe",DataRepository.getInstance().getTotalUnsettledClaimsClaimedBy(person),
        "peopleIOwe",DataRepository.getInstance().getClaimsFrom(person, true),
        "allIOwe",DataRepository.getInstance().getTotalUnsettledClaimsClaimedFrom(person),
        "nett",DataRepository.getInstance().getNettExpensesFor(person)
        );

//       System.out.println(viewModel);

        context.render("home.html", viewModel);
    }
}