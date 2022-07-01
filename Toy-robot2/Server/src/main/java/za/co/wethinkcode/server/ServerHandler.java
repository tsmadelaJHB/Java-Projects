package za.co.wethinkcode.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.commons.cli.*;

import za.co.wethinkcode.server.world.World;

public class ServerHandler {
    public static ArrayList<Thread> threads = new ArrayList<>();
    public static String pits;
    public static String mines;

    public static void main(String[] args) throws ParseException {

        Options options = new Options();
        Option name = new Option("p", "port", true, "Server port");
        name.setRequired(false);
        options.addOption(name);
        Option lastName = new Option("s", "size", true, "World size");
        lastName.setRequired(false);
        options.addOption(lastName);
        Option email = new Option("o", "obstacles", true, "Obstacles and their location");
        email.setRequired(false);
        options.addOption(email);
        Option pit = new Option("pit", "pits", true, "Pits and their location");
        email.setRequired(false);
        options.addOption(pit);
        Option mine = new Option("mine", "mines", true, "Mines and their location");
        email.setRequired(false);
        options.addOption(mine);
        HelpFormatter formatter = new HelpFormatter();
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        cmd = parser.parse(options, args);
        String port = cmd.getOptionValue("port") == null ? "5000" : cmd.getOptionValue("port");
        String obstacle = cmd.getOptionValue("obstacles") == null ? "" : cmd.getOptionValue("obstacles");
        pits = cmd.getOptionValue("pits") == null ? "" : cmd.getOptionValue("pits");
        mines = cmd.getOptionValue("mines") == null ? "" : cmd.getOptionValue("mines");
        String size = cmd.getOptionValue("size") == null ? "1" : cmd.getOptionValue("size");
        System.out.println("in server");

        try {

            loop(port,obstacle, size);
        } catch (IOException e) {
            System.out.println("Port connection failed.");
        }
    }

    /**
     * Listens for connections and sends new ones to the connection cleaner
     *
     * Stops loop wheJSONn all active connections are closed.
     */
    private static void loop(String port,String obstacle,String size) throws IOException {
        System.out.println("World of size "+size+" is loaded");
        System.out.println("Obstacles on "+obstacle);

        ServerSocket ss = new ServerSocket(Integer.parseInt(port));
        ConnectionCleaner cleaner = new ConnectionCleaner(ss);
        ExecutorService service = Executors.newFixedThreadPool(8);
        ExecutorService serviceCommands = Executors.newFixedThreadPool(1);
        World world = new World(obstacle, Integer.parseInt(size));


        Thread clean = new Thread(cleaner);
        System.out.println("Waiting for new connections..");
        clean.start();

        while (!ss.isClosed()) {
            serviceCommands.submit(new ServerCommands(world,obstacle,size));
            try {
                Socket newSocket = ss.accept();
                //Could make this a sleep until something is done?
//                while (cleaner.iterating) {
                    // blocks the addition of the new socket until
                    // the list is available for adding onto
//                }
                int worldSize = (size.equals("")) ? 0: Integer.parseInt(size);
                Thread newThread = newThread(newSocket, cleaner, world,worldSize);
                threads.add(newThread);
                service.execute(newThread);
            } catch (IOException e) {
                break;
            }
        }
        System.out.println("All connections closed. Shutting down..");
        service.shutdownNow();
    }

    /**
     * Creates a new thread to run the newConnection on a server
     *
     * @param newConnection the socket that will be placed on a different thread
     * @param cleaner       the connection cleaner that will keep track of the new
     *                      thread's active state
     * @param size          size of the world example 2x2
     * @return the new thread
     */
    private static Thread newThread(Socket newConnection, ConnectionCleaner cleaner, World world, int size) {
        SimpleServer newServer = new SimpleServer(newConnection, world,size);
        Thread r = new Thread(newServer);
        cleaner.addSocket(newConnection);
        return r;
    }
}