package za.co.wethinkcode.server.serverCommands;

import za.co.wethinkcode.server.world.World;

public class serverCommandHandler {
    public serverCommandHandler(String command , World world) {
        String[] args = command.split(" ");

        switch (args[0]) {
        case "quit":
            new QuitCommand(world);
            break;
        case "robots":
            new RobotsCommand();
            break;
        case "purge":
            new PurgeCommand(args[1]);
            break;
        case "dump":
            new DumpCommand();
            break;
        }
    }
}
