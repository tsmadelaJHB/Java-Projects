package za.co.wethinkcode.linklists;

public class WebServer {
    private final int LISTEN_PORT = 8000;
    private static LinkListsApp app;

    public static void main(String[] args){
        app = new LinkListsApp();
        app.start(LISTEN_PORT);
    }
}
