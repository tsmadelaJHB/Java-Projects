package za.co.wethinkcode.server.webAPI;

import com.fasterxml.jackson.databind.JsonNode;
import io.javalin.Javalin;
import za.co.wethinkcode.server.RobotWorldClient;
import za.co.wethinkcode.server.RobotWorldJsonClient;

import java.util.Scanner;

public class GreetingApi {

    private final Javalin server;
    private final static int DEFAULT_PORT = 5000;
    private final static String DEFAULT_IP = "localhost";
    private final RobotWorldClient serverClient = new RobotWorldJsonClient();

    public GreetingApi() {
        this.server = Javalin.create();
        serverClient.connect(DEFAULT_IP, DEFAULT_PORT);

        Scanner scanner = new Scanner(System.in);
        System.out.println("input name?");
        String name = scanner.nextLine().trim();
        System.out.println("input Command");
        String command = scanner.nextLine().trim();


        String robot1 = "{" +
                "  \"robot\": \"" + name + "\"," +
                "  \"command\": \"" + command + "\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";

        JsonNode response1 = serverClient.sendRequest(robot1);
        this.server.post("/greet", context -> {
            context.result(String.valueOf(response1));
            context.status(201);
        });

    }

    public Javalin start() {
        return this.server.start(7000);
    }

    public Javalin stop() {
        return this.server.stop();
    }

    public static void main(String[] args) {
        GreetingApi api = new GreetingApi();
        api.start();
    }
}
