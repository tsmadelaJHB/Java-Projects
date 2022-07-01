package za.co.wethinkcode.robotWorld.commands;

import za.co.wethinkcode.robotWorld.World;

public abstract class Command {
    private final String name;
    private String argument;

    private String robotName;

    public abstract boolean execute(World target);

    public Command(String robotName, String name){
        this.name = name.trim().toLowerCase();
        this.robotName = robotName;
        this.argument = "";
    }

    public Command(String robotName, String name, String argument) {
        this(robotName, name);
        this.argument = argument.trim();
    }

    public String getName() {                                                                           //<2>
        return name;
    }

    public String getArgument() {
        return this.argument;
    }

    public String getRobotName() {
        return this.robotName;
    }

    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ", 3);
        try {
            switch (args[1]) {
                case "shutdown":
                case "off":
                    return new ShutdownCommand(args[0]);
                case "help":
                    return new HelpCommand(args[0]);
                case "forward":
                    return new ForwardCommand(args[0], args[2]);
                case "back":
                    return new BackCommand(args[0], args[2]);
                case "left":
                    return new LeftCommand(args[0]);
                case "right":
                    return new RightCommand(args[0]);
                case "fire":
                    return new FireCommand(args[0]);
                case "look":
                    return new LookCommand(args[0]);
                case "launch":
                    return new LaunchCommand(args[0], args[2]);
                case "reload":
                    return new ReloadCommand(args[0]);
                case "repair":
                    return new RepairCommand(args[0]);
                case "set":
                    return new SetMineCommand(args[0]);
                case "detect":
                    return new DetectMineCommand(args[0]);
                default:
                    return new IllegalCommand(args[0]);
            }
        }catch (IndexOutOfBoundsException e){
            return new IllegalCommand(args[0]);
        }
    }
}

