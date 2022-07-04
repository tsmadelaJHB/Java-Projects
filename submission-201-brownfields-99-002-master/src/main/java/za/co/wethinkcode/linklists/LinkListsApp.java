package za.co.wethinkcode.linklists;

import io.javalin.Javalin;
import za.co.wethinkcode.linklists.motd.MotdResponse;

public class LinkListsApp {
    private static Javalin app;

    // <<<
    // COMPLETE THIS CLASS
    // >>>

    public LinkListsApp(String messageOfTheDay) {
        // <<<
        // >>>
    }

    public void start(int port){
        app.start(port);
    }

    public int port(){
        return app.port();
    }

}
