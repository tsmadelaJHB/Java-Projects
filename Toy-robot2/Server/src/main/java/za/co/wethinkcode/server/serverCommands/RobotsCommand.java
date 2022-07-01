package za.co.wethinkcode.server.serverCommands;

import java.util.ArrayList;

import za.co.wethinkcode.server.Robot;
import za.co.wethinkcode.server.commands.CommandHandler;

public class RobotsCommand {
    ArrayList<Robot> listOfRobots = new ArrayList<>();

    public RobotsCommand() {
        showRobots();
    }

    public void showRobots() {
        listOfRobots = CommandHandler.getRobots();

        for (Robot robot : listOfRobots) {
            String name = robot.getName();
            int[] position = robot.getPosition();
            String direction = robot.getDirection();
            int shields = robot.getShields();
            int shots = robot.getShots();
            String status = robot.getStatus();

            System.out.printf(
                    "name: %s\nstate: {\n\tposition: [%d, %d]\n\tdirection: %s\n\tshields: %d\n\tshots: %d\n\tstatus: %s\n}",
                    name, position[0], position[1], direction, shields, shots, status);
        }
    }
}
