package za.co.wethinkcode.server.webAPI;

import org.apache.commons.cli.ParseException;

import java.util.List;

public class QuoteDB {

    /**
     * Get a single quote by id.
     *
     * @param id the Id of the quote
     * @return a Quote
     */
    Quote get(Integer id) {
        return null;
    }

    /**
     * Get all quotes in the database
     *
     * @return A list of quotes
     */
    List<Quote> all() {
        return null;
    }

    /**
     * Add a single quote to the database.
     *
     * @param quote the quote to add
     * @return the newly added Quote
     */
    Quote add(Quote quote) throws ParseException {
        return null;
    }
}
