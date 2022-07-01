package za.co.wethinkcode.server.serverCommands;

import java.util.ArrayList;

import za.co.wethinkcode.server.Robot;
import za.co.wethinkcode.server.commands.CommandHandler;
import za.co.wethinkcode.server.world.World;

public class QuitCommand {
    ArrayList<Robot> listOfRobots = new ArrayList<>();
    int count=0;
    private final World world;

    public QuitCommand(World world) {
        this.world = world;
        System.out.println("Quit everything");
        closeRobots();
    }

    public void closeRobots() {
        listOfRobots = CommandHandler.getRobots();

        System.out.println(listOfRobots.size());

        for (Robot robot : listOfRobots) {
            String name = robot.getName();

            System.out.println(name);
            CommandHandler.handleCommand("close", null, robot, name);
            count++;
            System.out.println(count);
            closeRobots();
        }

    }

}
