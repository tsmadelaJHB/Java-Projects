package za.co.wethinkcode.Server;

import za.co.wethinkcode.Server.config.Configuration;
import za.co.wethinkcode.Server.config.ConfigurationManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Enables multiple connections
 * Gets the PORT number
 * Opens a socket
 * **/

public class MultiServers {

    public static void main(String[] args) throws ClassNotFoundException, IOException {

        System.out.println("Server Starting...");
        ConfigurationManager.getInstance().loadConfigurationFile("robotWorld/src/main/resources/http.json");
        Configuration conf = ConfigurationManager.getInstance().getCurrentConfiguration();

        System.out.println("Using Port: " + conf.getPort());
        ServerSocket serverSocket = new ServerSocket(conf.getPort());
        System.out.println("Server running & waiting for Client connections.");

        while(true) {

                Socket socket = serverSocket.accept();
                Runnable r = new SimpleServer(socket);
                Thread task = new Thread(r);
                task.start();
        }
    }
}
