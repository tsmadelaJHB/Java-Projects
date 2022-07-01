package za.co.wethinkcode.client;

import org.json.simple.JSONObject;

import java.util.concurrent.TimeUnit;

public class ContinuesUpdate implements Runnable {

    private static SimpleClient connect;


    // Variables to manage updating of display
    public static boolean updating = false;
    public static boolean runningCommand = false;
    private static Display display = new Display();

    public ContinuesUpdate(SimpleClient newConnect){
        connect = newConnect;
    }

    @Override
    public void run() {
        while (true) {

            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            while (runningCommand){}
            updating = true;
            SimpleClient.Written outcome;
            outcome = connect.writeToServer("state");
            if (outcome == SimpleClient.Written.FAILED)break;
            Object object1 = connect.readFromServer();
            if (object1 != null) {
                System.out.println("found");
                JSONObject object = (JSONObject) object1;
                display.update(object);
                display.displayGame();
            }

            System.out.println("Testing thread"); //:TODO DELETE After Testing

            updating = false;
        }

    }
}