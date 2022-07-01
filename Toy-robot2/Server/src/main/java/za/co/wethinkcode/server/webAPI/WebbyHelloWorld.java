package za.co.wethinkcode.server.webAPI;

import io.javalin.Javalin;

public class WebbyHelloWorld {
    public static void main(String[] args) {
        Javalin server = Javalin.create().start(7000);
        server.get("hello", context -> context.result("Hello, world!"));
    }
}
