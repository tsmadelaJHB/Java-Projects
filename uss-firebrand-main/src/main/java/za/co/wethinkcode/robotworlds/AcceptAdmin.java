package za.co.wethinkcode.robotworlds;

import za.co.wethinkcode.robotworlds.servercommand.ServerCommand;
import java.util.Scanner;

public class AcceptAdmin implements Runnable {
    private final Scanner scanner;

    public AcceptAdmin() {
        this.scanner = new Scanner(System.in);
    }
    /** Waits for an input as a String. Takes the input and sends it to ServerCommand which handles the commands, based
     * on the case. If the command isn't understood, it's caught by the IllegalArgumentException
     */
    @Override
    public void run() {
        while (true) {
            String messageFromAdmin = scanner.nextLine();
            while (messageFromAdmin != null) {
                try {
                    ServerCommand.create(messageFromAdmin);
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.print("Sorry, I did not understand '" + messageFromAdmin + "'.\n");
                    break;
                }
            }
        }
    }
}


