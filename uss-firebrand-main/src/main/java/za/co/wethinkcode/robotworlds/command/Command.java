package za.co.wethinkcode.robotworlds.command;

import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import za.co.wethinkcode.robotworlds.Robot;
import za.co.wethinkcode.robotworlds.WorldNavigator;
import za.co.wethinkcode.robotworlds.maze.Maze;
import za.co.wethinkcode.robotworlds.world.AbstractWorld;
import za.co.wethinkcode.robotworlds.world.Mine;
import za.co.wethinkcode.robotworlds.world.Obstacle;
import za.co.wethinkcode.robotworlds.world.Pits;

import java.util.ArrayList;
import java.util.List;

public abstract class Command implements ICommand {
    List <Mine> mine = new ArrayList<>();
    private final String name;;

    private String argument;
    private Maze maze;

    public abstract boolean execute(Robot target);

    public Command(String name){
        this.name = name.trim().toLowerCase();
        this.argument = "";
        this.maze = AbstractWorld.maze;
        this.mine = WorldNavigator.mine;
    }
    
    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
    }

    public String getArgument() {
        return this.argument;
    }

    public static Command create(JSONObject instruction) {
        switch (instruction.get("command").toString()){
            case "disconnect":
            case "shutdown":
            case "off":
                return new ShutdownCommand();
            case "state":
                return new StateCommand();
            case "forward":
                try {
                    return new ForwardCommand(instruction.get("arguments").toString());
                } catch (IndexOutOfBoundsException e){
                    throw new IllegalArgumentException("Unsupported command: " + instruction);
                }
            case "back":
                try {
                    return new BackCommand(instruction.get("arguments").toString());
                } catch (IndexOutOfBoundsException e){
                    throw new IllegalArgumentException("Unsupported command: " + instruction);
                }
            case "turn":
                try {
                    if (instruction.get("arguments").toString().equalsIgnoreCase("right"))
                        return new RightCommand();
                    else if (instruction.get("arguments").toString().equalsIgnoreCase("left"))
                        return new LeftCommand();
                } catch (IndexOutOfBoundsException e){
                    throw new IllegalArgumentException("Unsupported command: " + instruction);
                }
            case "repair":
                return new ShieldRepairCommand();
            case "orientation":
                return new OrientationCommand();
            case "look":
                return new LookCommand();
            case "mine":
                return new SetMineCommand();
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }


    @Override
    public List<Obstacle> getObstacles() {
        return maze.getObstacles();
    }

    @Override
    public List<Pits> getPits() {
        return maze.getPits();
    }

    @Override
    public List<Mine> getMines() {
        return null;
    }

    public void MakeMine(Mine mine){
        this.mine.add(mine);
    }
}
