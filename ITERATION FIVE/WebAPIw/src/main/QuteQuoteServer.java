package main;

import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class QuteQuoteServer {
    private final Javalin server;

    public QuteQuoteServer() throws SQLException {
        server = Javalin.create(config -> {
            config.defaultContentType = "application/json";
            config.enableCorsForAllOrigins();
        });


        this.server.get("/quotes", context -> main.QuoteApiHandler.getAll(context));
        this.server.get("/quote/{id}", context -> main.QuoteApiHandler.getOne(context));
        this.server.post("/quotes", context -> main.QuoteApiHandler.create(context));
//        System.out.println(context);

    }

    public static void main(String[] args) throws SQLException {
        main.QuteQuoteServer server = new main.QuteQuoteServer();
        server.start(5000);

    }

    public void start(int port) {
        this.server.start(port);
    }

    public void stop() {
        this.server.stop();
    }

}
