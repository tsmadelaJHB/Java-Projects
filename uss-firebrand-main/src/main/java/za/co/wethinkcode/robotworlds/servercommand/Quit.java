package za.co.wethinkcode.robotworlds.servercommand;

import za.co.wethinkcode.robotworlds.MultiServers;

import java.net.Socket;

public class Quit {
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    /** Accesses each client Socket from a list and closes them one by one. Uses System.exit() to close the server.
     * Prints a nice goodbye message.
     */
    public Quit() {
        try {
            Socket clientSocket;
            for (int i = 0; i < MultiServers.clientSocketList.size(); i++) {
               clientSocket = MultiServers.clientSocketList.get(i);
               clientSocket.close();
            }

            System.out.print(ANSI_BLUE + "Thank you for managing RobotWorlds. Goodbye." + ANSI_RESET);
            System.exit(0);
        } catch (Exception e) {
            System.out.print("Error terminating client and server: " +e+ "\n");
        }
    }
}
