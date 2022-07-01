package za.co.wethinkcode.client;

import org.json.simple.JSONObject;

public class Robot {
    private String name = "";
    private int x;
    private int y;
    private String direction;
    private int shield;
    private int shots;
    private String status;

    // test line to see if I can commit

    public Robot(String name){
        this.name = name;
        this.x = 0;
        this.y = 0;
        this.direction = "North";
        this.shield = 5;
        this.shots = 6;
        this.status = "Normal";
    }

    /**
     * Return the robot's name
     * @return String: the robot name. Empty if unassigned
     */
    public String getName(){
        return this.name;
    }

    /**
     * Set the robot's name
     * @param newName: the new robot name
     */
    public void setName(String newName){
        this.name = newName;
    }

    /**
     * Apply the state from the server to the client copy of the robot
     * @param response: The object to extract a state JSON from
     * @return boolean: true if the state was found and applied, false otherwise
     */
    public boolean applyState(Object response){
        JSONObject responseJSON = (JSONObject) response;

        try{
            if (responseJSON.get("state") == null)
                throw new NullPointerException();

            JSONObject state = (JSONObject) responseJSON.get("state");

            this.x = ((int[]) state.get("position"))[0];
            this.y = ((int[]) state.get("position"))[1];
            this.direction = (String) state.get("direction");
            this.shield = (int) state.get("shields");
            this.shots = (int) state.get("shots");
            this.status = (String) state.get("status");
            System.out.println(this.toString());

        }catch(NullPointerException | IndexOutOfBoundsException e){
            return false;
        }
        return true;
    }

    /**
     * Create a string with the robot position, direction, shields, shots, and status 
     * @return String: the string for the robot state
     */
    @Override
    public String toString(){
        String robotString = String.format("Position: [%d,%d]\n" +
        "Direction: %s\n" +
        "Shields: %d\n" +
        "Shots: %d\n" + 
        "Status: %s\n", this.x, this.y, this.direction, this.shield, this.shots, this.status);

        return robotString;
    }

}