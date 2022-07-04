package za.co.wethinkcode.robotworlds.servercommand;

import za.co.wethinkcode.robotworlds.AcceptClient;

public class Robots {
    public Robots() { // Prints out a list of all clients connected
        System.out.print("The current Robots connected are: "+AcceptClient.getUserNames()+"\n");
    }
}
