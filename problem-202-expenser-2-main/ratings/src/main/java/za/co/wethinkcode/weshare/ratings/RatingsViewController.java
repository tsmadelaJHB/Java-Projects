package za.co.wethinkcode.weshare.ratings;

import io.javalin.http.Context;
import za.co.wethinkcode.weshare.app.db.DataRepository;
import za.co.wethinkcode.weshare.app.model.Person;

import java.util.*;
import java.util.stream.Stream;

public class RatingsViewController {
    public static final String PATH = "/ratings";

    public static void renderRatingsPage(Context context){
        Person currentPerson = context.sessionAttribute("user");

        Map<String, Object> viewModel =
                Map.of("expenses", DataRepository.getInstance().getExpenses(currentPerson),
                        //"totalExpenses", DataRepository.getInstance().getTotalExpensesFor(currentPerson),
                        "owedToOthers", DataRepository.getInstance().getClaimsFrom(currentPerson, true),
                        //"totalIOwe", DataRepository.getInstance().getTotalUnsettledClaimsClaimedFrom(currentPerson),
                        "owedToMe", DataRepository.getInstance().getClaimsBy(currentPerson, true),
                        //"totalOwedToMe", DataRepository.getInstance().getTotalUnsettledClaimsClaimedBy(currentPerson),
                        "nettExpenses", DataRepository.getInstance().getNettExpensesFor(currentPerson));

        //List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer, Integer>>((Collection<? extends Map.Entry<Integer, Integer>>) viewModel.entrySet());
//        Collections.sort(list, new Comparator<Object>() {
//            @SuppressWarnings("unchecked")
//            public int compare(Object o1, Object o2) {
//                return ((Comparable<Integer>) ((Map.Entry<Integer, Integer>) (o1)).getValue()).compareTo(((Map.Entry<Integer, Integer>) (o2)).getValue());
//            }
//        });
//        Map<Integer, Integer> result = new LinkedHashMap<>();
//        for (Iterator<Map.Entry<Integer, Integer>> it = list.iterator(); it.hasNext();) {
//            Map.Entry<Integer, Integer> entry = (Map.Entry<Integer, Integer>) it.next();
//            result.put(entry.getKey(), entry.getValue());
//        }
//        result.forEach((k,v)->System.out.println(k+"="+v));
//

        context.render("ratings.html", viewModel);
    }
}