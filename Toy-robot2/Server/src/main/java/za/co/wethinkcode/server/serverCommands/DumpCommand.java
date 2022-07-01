package za.co.wethinkcode.server.serverCommands;

import java.util.ArrayList;
import java.util.List;

import za.co.wethinkcode.server.world.World;

public class DumpCommand {
    List<int[]> obstacles = new ArrayList<>();
    List<int[]> pits = new ArrayList<>();

    public DumpCommand() {
        RobotsCommand displayRobots = new RobotsCommand();
        displayWorld();
    }

    public void displayWorld() {
        obstacles = World.getObstacles();
        pits = World.getPits();

        System.out.println("Obstacles");
        for (int[] i : obstacles) {
            System.out.print("[" + i[0] + ", " + i[1] + "] ");
        }

        System.out.println("Pits");
        for (int[] i : pits) {
            System.out.print("[" + i[0] + ", " + i[1] + "] ");
        }
    }
}
