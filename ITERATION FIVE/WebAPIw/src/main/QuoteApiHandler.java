package main;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import io.javalin.http.NotFoundResponse;

import java.sql.SQLException;

public class QuoteApiHandler {
    private static main.QuoteDB database = null;

    static {
        try {
            database = new main.TestDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get all quotes
     *
     * @param context The Javalin Context for the HTTP GET Request
     */
    public static void getAll(Context context) {
        context.json(database.all());
    }

    /**
     * Get one quote
     *
     * @param context The Javalin Context for the HTTP GET Request
     */
    public static void getOne(Context context) {
        Integer id = context.pathParamAsClass("id", Integer.class).get();
        Quote quote = database.get(id);
        if (quote == null) {
            throw new NotFoundResponse("Quote not found: " + id);
        }
        context.json(quote);
    }
//    public static void getOne(Context context) {
//        Integer id = context.pathParamAsClass("id", Integer.class).get();
//        Quote quote = database.get(id);
//        context.json(quote);
//    }

    /**
     * Create a new quote
     *
     * @param context The Javalin Context for the HTTP POST Request
     */
    public static void create(Context context) {

        main.Quote quote = context.bodyAsClass(main.Quote.class);
        main.Quote newQuote = database.add(quote);
        context.header("Location", "/quote/" + newQuote.getId());

        context.status(HttpCode.CREATED);
        context.json(newQuote);

    }

}
