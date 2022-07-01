package main;

import main.Quote;
import main.QuoteDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDatabase implements QuoteDB {
    private Map<Integer, Quote> quotes;
    String dbUrl = "jdbc:sqlite:iteration.db";
    Connection connection = DriverManager.getConnection( dbUrl );
    final Statement stmt = connection.createStatement();
    int count=0;

    public TestDatabase() throws SQLException {
        Quote quote = null;
        quotes = new HashMap<>();
        this.add(Quote.create("There is no snooze button on a cat who wants breakfast.", "Unknown"));
        this.add(Quote.create("Never try to outstubborn a cat.","Robert A. Heinlein"));
        this.add(Quote.create("To err is human, to purr is feline.", "Robert Byrne"));
//        createData(quote.getName(),quote.getText());

    }

    @Override
    public Quote get(Integer id) {
        return quotes.get(id);
    }

    @Override
    public List<Quote> all() {
        return new ArrayList<>(quotes.values());
    }

    @Override
    public Quote add(Quote quote){
        Integer index = quotes.size() + 1;
        quote.setId(index);
        quotes.put(index, quote);

        try{
            stmt.executeUpdate( "CREATE TABLE IF NOT EXISTS books( test_id integer PRIMARY KEY," +
                    " Author NOT NULL," +
                    " text NOT NULL)" );
            System.out.println( "Success creating test table!" );
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }//

        try  {
            String value = "('" + quote.getName() + "','" + quote.getText() + "')";
            String inserting = "INSERT INTO books(Author,text) VALUES " + value;
            boolean addAResultSet = stmt.execute(
                    inserting
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println("Text is stored.");

        return quote;
    }

}
