package za.co.wethinkcode.server;

import java.sql.SQLException;
import java.util.Scanner;


import za.co.wethinkcode.server.database.robotWorldDatabaseDAO;
import za.co.wethinkcode.server.serverCommands.serverCommandHandler;
import za.co.wethinkcode.server.world.World;

public class ServerCommands implements Runnable {
    Scanner scanner = new Scanner(System.in);
    private final World world;
    private final String obs;
    private final String pit;
    private final String mine;
    private final String size;

    /**
     * Assigning the objects and size and world
     * @param world
     * @param Obs
     * @param Size
     */
    public ServerCommands(World world,String Obs,String Size) {
        this.world = world;
        this.obs = Obs;
        this.size = Size;
        this.pit = ServerHandler.pits;
        this.mine = ServerHandler.mines;

    }

    /**
     * Running the commands
     */
    @Override
    public void run() {
        //looping the commands
        while (true) {
            //input from the user for server commands
            String input = scanner.nextLine();
            //executing the save command
            if(input.equalsIgnoreCase("save")){
                try {
                    System.out.println("Enter world name");
                    String name = scanner.nextLine().trim();
                    //add obstacles if there are available
                    if(!obs.equalsIgnoreCase("")){
                        String[] splitCommand = obs.split(",");
                        String x = splitCommand[0];
                        String y = splitCommand[1];
                        robotWorldDatabaseDAO.addObject("Obstacle",Integer.parseInt(x),Integer.parseInt(y),name);
                    }
                    //add pit if there are available
                    if(!pit.equalsIgnoreCase("")){
                        String[] splitCommand = pit.split(",");
                        String x = splitCommand[0];
                        String y = splitCommand[1];
                        robotWorldDatabaseDAO.addObject("Pit",Integer.parseInt(x),Integer.parseInt(y),name);
                    }
                    //add mine if there are available
                    if(!mine.equalsIgnoreCase("")){
                        String[] splitCommand = mine.split(",");
                        String x = splitCommand[0];
                        String y = splitCommand[1];
                        robotWorldDatabaseDAO.addObject("Mine",Integer.parseInt(x),Integer.parseInt(y),name);
                    }
                    else if(mine.equals("") && pit.equals("") && obs.equals(""))
                    {
                        System.out.println("The world won't have objects");
                    }
                    //add the world
                    robotWorldDatabaseDAO.addDBWorld(name, Integer.parseInt(size));

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else if(input.equalsIgnoreCase("restore")){
                System.out.println("Enter world name");
                String name = scanner.nextLine().trim();
                try {
                    robotWorldDatabaseDAO.getDBWorld(name);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            new serverCommandHandler(input, this.world);
        }
    }
}