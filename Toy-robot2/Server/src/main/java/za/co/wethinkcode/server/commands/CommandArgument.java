package za.co.wethinkcode.server.commands;

import za.co.wethinkcode.server.Robot;
import za.co.wethinkcode.server.world.World;

public class CommandArgument {
    private final String name;
    private final World world;
    private final String[] args;
    private final Robot rbt;
    private final String command;

    public CommandArgument(String name, World world, String[] args, Robot rbt, String command){
        this.name = name;
        this.world = world;
        this. args = args;
        this.rbt = rbt;
        this.command = command;
    }

    public String getName(){return this.name;}

    public World getWorld(){return this.world;}

    public String[] getArgs(){return this.args;}

    public Robot getRbt() {return this.rbt;}

    public String getCommand(){return this.command;}
}
