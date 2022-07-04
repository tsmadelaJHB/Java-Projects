package za.co.wethinkcode.robotworlds;

import org.apache.commons.cli.*;
import za.co.wethinkcode.robotworlds.maze.Maze;
import za.co.wethinkcode.robotworlds.maze.RandomObs;
import za.co.wethinkcode.robotworlds.webapi.controllers.ServerController;
import za.co.wethinkcode.robotworlds.world.IWorld;
import za.co.wethinkcode.robotworlds.world.TextWorld;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultiServers{
    public static int IWorldSize;
    public static Maze obs;
    public static IWorld World;
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static ServerSocket serverSocket;
    public static List<Socket> clientSocketList = new ArrayList<>();
    public static List<Robot> RobotList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Options options = new Options();
        Option portNumber = new Option("p","port",true, "Port number");
        portNumber.setRequired(true);
        options.addOption(portNumber);
        Option worldSize = new Option("s","size", true,"integer to make world x by x in size" );
        worldSize.setRequired(true);
        options.addOption(worldSize);
        Option obstacles = new Option("o","obstacles",true,"makes a 1 by 1 obstacle at specified cord");
        obstacles.setRequired(false);
        options.addOption(obstacles);
        Option apiServerPortNumber = new Option("a", "apiport", true, "Port number for Web API Server");
        options.addOption(apiServerPortNumber);

        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine config;
        config = commandLineParser.parse(options,args);
        IWorldSize = Integer.parseInt(config.getOptionValue("size"));
        System.out.print(ANSI_BLUE + "Welcome to RobotWorlds, server.\n" + ANSI_RESET);
        System.out.print("Server running & waiting for client connections.\n");
        System.out.print("You can run the following commands: " +
                "\nDUMP - prints out the state of the world's obstacles, pits and mines" +
                "\nQUIT - removes all robots from the game" +
                "\nROBOTS - prints out all usernames of robots in the world\n");



        serverSocket = new ServerSocket(Integer.parseInt(config.getOptionValue("port")));

        obs = new RandomObs(IWorldSize);
        if(config.getOptionValue("o") != null){
            String[] forcedPosString = config.getOptionValue("o").split(",");
            Position forcedPos = new Position(Integer.parseInt(forcedPosString[0]),Integer.parseInt(forcedPosString[1]));
            obs.MakeSpecificObstacle(forcedPos,forcedPos);
        }

        World = new TextWorld(obs);


        Socket clientSocket;
        Thread runServerSideCommands = new Thread(new AcceptAdmin()); // Enables server-side commands to run seamlessly
        runServerSideCommands.start();
        /*** Create an instance of the WebAPIServer and start it on the specified port number (apiport) **/
        ServerController serverController = new ServerController();
        try {
            serverController.start(Integer.parseInt(config.getOptionValue("apiport")));
        } catch (NumberFormatException ex) { //added try and catch as this was resulting in a NumberFormatException when api port is null
            serverController.start(8080);
        }

        /*while(true) {
            try {
                clientSocket = serverSocket.accept();
                clientSocketList.add(clientSocket);
                Runnable acceptClient = new AcceptClient(clientSocket);
                Thread task = new Thread(acceptClient);
                task.start();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }*/
    }
}
