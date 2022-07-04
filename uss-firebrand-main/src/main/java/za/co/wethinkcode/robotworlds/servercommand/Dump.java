package za.co.wethinkcode.robotworlds.servercommand;

import za.co.wethinkcode.robotworlds.AcceptClient;
import za.co.wethinkcode.robotworlds.MultiServers;

public class Dump {
    /** Displays a representation of the world’s state showing robots, obstacles and pits
     */
    public Dump() {
        System.out.print("There are robots: "+AcceptClient.getUserNames()+"\n");
        MultiServers.World.showObstacles();
        MultiServers.World.showPits();
        try {
            MultiServers.World.showMines();
        } catch (IndexOutOfBoundsException e){
            ;
        }
    }
}
