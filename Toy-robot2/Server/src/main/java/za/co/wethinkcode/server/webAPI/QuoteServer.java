package za.co.wethinkcode.server.webAPI;

import io.javalin.Javalin;
import za.co.wethinkcode.server.RobotWorldClient;
import za.co.wethinkcode.server.RobotWorldJsonClient;

import static io.javalin.apibuilder.ApiBuilder.*;

public class QuoteServer {
    private final Javalin server;

    public QuoteServer() {
        server = Javalin.create(config -> {
            config.defaultContentType = "application/json";
            config.enableCorsForAllOrigins();
        });

        this.server.get("/world/1", QuoteApiHandler::getAll);
        this.server.get("/myworlds", QuoteApiHandler::getOne);
        this.server.get("/position", QuoteApiHandler::getAll);
//        this.server.get("/quote/{id}", QuoteApiHandler::getOne);
        this.server.post("/quotes", QuoteApiHandler::create);
        this.server.post("/obstacles", QuoteApiHandler::create);
        this.server.post("/myworlds", QuoteApiHandler::getWorld);
        this.server.post("/world/1", QuoteApiHandler::createAdmin);
    }

    public static void main(String[] args) {
        QuoteServer server = new QuoteServer();
        server.start(7000);
    }

    public void start(int port) {
        this.server.start(port);
    }

    public void stop() {
        this.server.stop();
    }
}
