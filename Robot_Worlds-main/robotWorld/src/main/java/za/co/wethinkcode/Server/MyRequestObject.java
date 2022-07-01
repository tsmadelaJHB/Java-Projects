package za.co.wethinkcode.Server;

/**
 * Creates a request object
 * Returns the request object
 **/

public class MyRequestObject {

    private String robot;
    private String command;
    private String[] arguments;

    public String getRobot(){
        return robot;
    }

    public void setRobot(String robot) {
        this.robot = robot;
    }

    public String getCommand(){
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String[] getArguments(){
        return arguments;
    }

    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }
}
