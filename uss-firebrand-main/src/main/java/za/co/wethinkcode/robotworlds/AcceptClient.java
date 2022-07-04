package za.co.wethinkcode.robotworlds;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import za.co.wethinkcode.robotworlds.command.Command;
import za.co.wethinkcode.robotworlds.world.Obstacle;

import javax.net.ssl.CertPathTrustManagerParameters;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.*;

import static za.co.wethinkcode.robotworlds.world.AbstractWorld.RandomGenerator;

public class AcceptClient{
    private DataInputStream in;
    private PrintStream out;
    boolean shouldContinue = true;
    boolean keepAskingForCommand = true;
    String clientMachine;
    Robot robot ;
    int GameWorld;

    public static List<String> robotNames = new ArrayList<>();
    static List<String> robotTypes = new ArrayList<>();
// can't find a better way of dealing with commands that are executed before a robot is launched so...
    public String[] validCommand = new String[]{"disconnect","shutdown","off","state","forward","back","turn","repair","orientation","look","mine"};

    public static List<String> robotUserNames = new ArrayList<>();

    Gson gson = new Gson();

    String clientMessage;
    String clientResponse;

    /*public AcceptClient(Socket socket) throws IOException {
        GameWorld = MultiServers.IWorldSize;
        out = new PrintStream(socket.getOutputStream());
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        System.out.println("\nConnection: " + socket);
    }*/

    public AcceptClient(String clientMessage) throws IOException{
        GameWorld = MultiServers.IWorldSize;
        this.clientMessage = clientMessage;
    }

    public void worldSizer(){
        if(GameWorld%2 == 0){
               robot.setTOP_LEFT(new Position(-(GameWorld/2),GameWorld/2));
               robot.setBOTTOM_RIGHT(new Position(GameWorld/2,-(GameWorld/2)));
        }
    }

    public Position spawnHandler(){
        List<Position> world = new ArrayList<>();
        Random random = new Random();
        List<Obstacle> obstacles = MultiServers.obs.getObstacles();
        List<Position> unavailableSpots = new ArrayList<>();
        Position centre = new Position(0,0);

        for(int x = -(GameWorld/2); x <= (GameWorld/2); x++){
            for(int y = -(GameWorld/2); y <= (GameWorld/2); y++){
                world.add(new Position(x,y));
            }
        }

        for (Obstacle obstacle : obstacles) {
            unavailableSpots.addAll(obstacle.blockedCords());
        }
        for (Robot robot : MultiServers.RobotList){
            unavailableSpots.add(robot.getPosition());
        }

        world.removeAll(unavailableSpots);
        if(world.size() > 0){
            if(world.contains(centre)){
                return centre;
            }

            int pos = random.nextInt(world.size());
            return world.get(pos);
        }
        return null;
    }

    public Response launchHandler(JSONObject clientCommand){
        String name = clientCommand.get("robot").toString();
        Response launchResponse = new Response();
        JsonObject message = new JsonObject();
        if (Arrays.asList(validCommand).contains(clientCommand.get("command").toString())){
            launchResponse.setResult("ERROR");
            message.addProperty("message","Robot does not exist");
            launchResponse.setData("message",message);
        }else if(!Objects.equals(clientCommand.get("command").toString(), "launch")){
            launchResponse.setResult("ERROR");
            message.addProperty("message","Unsupported command");
            launchResponse.setData("message",message);
        }else if (robotNames.contains(name)) {
            launchResponse.setResult("ERROR");
            message.addProperty("message","Too many of you in this world");
            launchResponse.setData("message",message);
        }else {
            String args = clientCommand.get("arguments").toString();
            String[] arguments = args.split(",");
            String type = arguments[0].replaceAll("[^a-zA-Z0-9]", "");
            Position spawn = spawnHandler();
            if(spawn == null){
                launchResponse.setResult("ERROR");
                message.addProperty("message","No more space in this world");
                launchResponse.setData("message",message);
            }else {
                robot = new Robot(name, type, spawnHandler());
                launchResponse.setResult("OK");
                JsonObject position = new JsonObject();
                position.addProperty("position",robot.getPosition().getX() + "," + robot.getPosition().getY());
                launchResponse.setData("position",position);
                JsonObject visibility = new JsonObject();
                visibility.addProperty("visibility",robot.config.getRobotVisibility());
                launchResponse.setData("visibility",visibility);
                JsonObject reload = new JsonObject();
                reload.addProperty("reload",robot.robotType.getAmmo());
                launchResponse.setData("reload",reload);
                JsonObject repair = new JsonObject();
                repair.addProperty("repair",robot.config.getRepairShield());
                launchResponse.setData("repair",repair);
                JsonObject mine = new JsonObject();
                mine.addProperty("mine",robot.config.getSetMine());
                launchResponse.setData("mine",mine);
                JsonObject shields = new JsonObject();
                shields.addProperty("shields",robot.config.getShieldStrength());
                MultiServers.RobotList.add(robot);
                robotNames.add(name);
                robotUserNames.add(name);
                launchResponse.setState(robot);
            }
        }
        return launchResponse;
    }

    public Robot findRobot(String robotName){
        Robot robot1 = null;
        for (Robot robot : MultiServers.RobotList){
            if (robot.getName().equalsIgnoreCase(robotName))
                robot1 = robot;
        }
        return robot1;
    }

    public String launchRobot(){
        boolean launched = false;
        String message;
        while (!launched) {
            try {
                //String clientMessage = in.readLine();
                JSONParser parser = new JSONParser();
                if (clientMessage == null){
                    return "";
                }
                JSONObject clientRequestMessage = (JSONObject) parser.parse(clientMessage);
                Response response = launchHandler(clientRequestMessage);
                String sResponse = gson.toJson(response);
                message = sResponse;
                System.out.println(sResponse);
                JSONObject JResponse = (JSONObject) parser.parse(sResponse);
                if (Objects.equals(JResponse.get("result").toString(), "OK")) {
                    launched = true;
                    System.out.println("Robot has been launched");
                }
                //out.println(sResponse);
            } catch (/*IOException | */ParseException e) {
                e.printStackTrace();
                break;
            }
            return message;
        }

            while(keepAskingForCommand) {
                if (robotNames.size()>0){
                    try {
                        worldSizer();
                        getCommand(robot);

                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
            return "";
    }

    /** Waits for an input as a String. Takes the input and sends it to Command which handles the commands, based
     * on the case. If the command cannot be understood, the loop doesn't continue. If the command isn't understood,
     * it's caught by the IllegalArgumentException. This also returns the state of the robot to the client.
     */
    void getCommand(Robot robot) throws IOException {
        robot.setWorld(MultiServers.World);
        try {
            Command command;
            boolean shouldContinue = true;

            do {
                String messageFromClient;

                while((messageFromClient = in.readLine()) != null) {
                    System.out.println("Message \"" + messageFromClient + "\" from " + clientMachine);
                    JSONParser parser = new JSONParser();
                    JSONObject request = (JSONObject) parser.parse(messageFromClient);

                    try {
                        command = Command.create(request);
                        shouldContinue = robot.handleCommand(command);
                        System.out.println(robot.State());
                        if (robot.IsDead())
                            robot.setStatus("dead");
                    } catch (IllegalArgumentException e) {
                        out.println("{\"result\" : \"ERROR\", \"data\" : {\"message\" : \"Unsupported command\"}}");
                        robot.setStatus("Sorry, I did not understand '" + messageFromClient + "'.");
                        System.out.println(robot.State());
                    }
                    String Response = gson.toJson(robot.serverResponse);
                    out.println(Response);
                }
            } while (shouldContinue);
        } catch(IOException | ParseException ex) {
            keepAskingForCommand = false;
        } finally {
            closeQuietly();
        }
    }

    public String getCommandResponse(Robot robot, String messageFromClient){
        Command command;
        System.out.println("Message \"" + messageFromClient + "\" from " + clientMachine);
        JSONParser parser = new JSONParser();


        try{
            JSONObject request = (JSONObject) parser.parse(messageFromClient);
            command = Command.create(request);
            shouldContinue = robot.handleCommand(command);
            System.out.println(robot.State());
            if (robot.IsDead())
                robot.setStatus("dead");
        }catch (IllegalArgumentException | ParseException e){
            robot.setStatus(("Sorry, I did not understand '" + messageFromClient + "'."));
            System.out.println(robot.State());
        }
        String Response = gson.toJson(robot.serverResponse);
        System.out.println(Response);
        return Response;
    }

    /**Gets robot's name from the first item in the list. The list is added too every time a new Robot is instantiated
    */
    public static String getRobotName() {
        if (robotNames.size() > 0) {
            return robotNames.get(robotNames.size()-1);
        } else {
            return robotNames.toString();
        }
    }

    /** Every robot's name is added to this list. The list formats the names and returns them as a String to be used in
     * Dump and Robots
     */
    public static List<String> getUserNames() { return robotUserNames;}

    public static boolean isSniper(){
        return getRobotType().equals("sniper");
    }

    public static boolean isBomber(){
        return getRobotType().equals("bomber");
    }

    public static String getRobotType() {
        if (robotTypes.size() > 0) {
            String type = robotTypes.get(robotTypes.size()-1);
            return type;
        } else {
            return robotTypes.toString();
        }
    }

    /** Generates a random location for the Robot to spawn at. Each Robot spawns somewhere different on the map.
     */
    public static Position getRandomSpawn(){
        return new Position(RandomGenerator(100, -100, 100)
                , RandomGenerator(200, -200, 200));
    }

    private void closeQuietly() {
        try { in.close(); out.close();
        } catch(IOException ignored) {}
    }
}
