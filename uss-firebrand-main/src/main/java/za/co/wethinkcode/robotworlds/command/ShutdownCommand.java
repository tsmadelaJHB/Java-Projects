package za.co.wethinkcode.robotworlds.command;

import za.co.wethinkcode.robotworlds.MultiServers;
import za.co.wethinkcode.robotworlds.Robot;

import static za.co.wethinkcode.robotworlds.AcceptClient.robotNames;
import static za.co.wethinkcode.robotworlds.AcceptClient.robotUserNames;

public class ShutdownCommand extends Command {
    public ShutdownCommand() {
        super("off");
    }

    @Override
    public boolean execute(Robot target) {
        target.setStatus("{\"result\" : \"ok\"}");
        System.out.println("Client " + target.getName()+ " has disconnected");
        robotNames.remove(target.getName());
        robotUserNames.remove(target.getName());// Removing Robot from the list of usernames
        MultiServers.RobotList.remove(target);
        return false;
    }
}
