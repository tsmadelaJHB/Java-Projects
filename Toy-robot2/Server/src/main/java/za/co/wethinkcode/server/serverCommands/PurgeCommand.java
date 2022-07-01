package za.co.wethinkcode.server.serverCommands;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import za.co.wethinkcode.server.Robot;
import za.co.wethinkcode.server.commands.CommandHandler;

//import static za.co.wethinkcode.server.commands.CommandHandler.generateState;

public class PurgeCommand {
    ArrayList<Robot> listOfRobots = new ArrayList<>();

    public PurgeCommand(String name) {
        killRobot(name);
    }

    public void killRobot(String robotName) {
        listOfRobots = CommandHandler.getRobots();

        int count = 0;
        for (Robot robot : listOfRobots) {
            if (robot.getName().equalsIgnoreCase(robotName)) {
                robot.setStatus("DEAD");

            }
            System.out.println(robot.getName() + " Disconnected");
            count++;


        }
        if (count > 0) {
            listOfRobots.subList(0, count).clear();
        }

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
