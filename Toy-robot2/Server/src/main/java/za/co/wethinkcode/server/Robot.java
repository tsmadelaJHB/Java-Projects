package za.co.wethinkcode.server;

import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;
import java.util.Objects;

import za.co.wethinkcode.server.world.World;

public class Robot {
    private World world;

    private int[] pos;
    private String direction,status,lastAttempt = "Done", type,name;

    public int bulletDistance = 4,shots,damage,maxShots,reload,mine;

    private int shield,repair,maxShield,visibility;
    private final int size;

    private LocalTime bound;

    List<int[]> coordinate = new ArrayList<>();

    public Robot(String name, int shots, int shieldCap, World world, int size) {
        this.type = "dummy";
        this.status = "NORMAL";
        this.size = size;
        this.pos = generateRandomPosition();
        this.world = world;
        this.name = name;
        this.shield = shieldCap;
        this.shots = shots;
        Configure.setValues(this);
    }

    private int[] generateRandomPosition() {
        int[] newCord = CoordinateGenerator.coordinate(this.size, this.world);
        int x = newCord[0];
        int y = newCord[1];
        int[] cord = {x,y};
        if(coordinate.size()>0){
            coordinate.remove(0);
        }
        coordinate.add(cord);
        return cord;
    }

    public int getWorldSize(){
        return this.size;
    }

    /**
     * Return the robot's name
     * 
     * @return String: the robot name. Empty if unassigned
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the robot's name
     * 
     * @param newName: the new robot name
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * Return the robot's position
     * 
     * @return int[]: the robot position, x at [0] y at [1]
     */
    public int[] getPosition() {
        return this.pos;
    }
    public List<int[]> getCo(){

        return coordinate;
    }

    /**
     * Set the robot's position
     * 
     * @param newPosition: the new robot position
     */
    public void setPosition(int[] newPosition) {
        this.pos = newPosition;
    }

    /**
     * Return the robot's status
     * 
     * @return String: the robot status
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Set the robot's status
     * 
     * @param status the new status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Return the robot's available shots
     * 
     * @return int: the remaining shots
     */
    public int getShots() {
        return this.shots;
    }

    /**
     * Set the robot's shots
     * 
     * @param shots: the new number of available shots
     */
    public void setShots(int shots) {
        this.shots = shots;
    }

    public void setMaxShots(int shots) {
        this.maxShots = shots;
    }

    public void setMaxShields(int shields) {
        this.maxShield = shields;
    }

    public void setBound(int add) {
        this.bound = LocalTime.now().plusSeconds(add);
    }

    public boolean checkIfBusy() {
        if (this.bound == null || this.bound.compareTo(LocalTime.now()) < 0) {
            this.bound = null;

            if (this.status.equalsIgnoreCase("reload")) {
                this.shots = this.maxShots;
            }

            if (this.status.equalsIgnoreCase("repair")) {
                this.shield = this.maxShield;
            }

            this.status = "NORMAL";
            return false;
        }
        return true;
    }

    public void reload() {
        this.setBound(reload);
        this.status = "RELOAD";
    }

    public void repair() {
        this.setBound(repair);
        this.status = "REPAIR";
    }

    /**
     * Return the robot's remaining shield points. Every point is a shot the robot
     * can absorb. 0 means the next shot will kill it. -1 means it is dead.
     * 
     * @return int: the shield points
     */
    public int getShields() {
        return this.shield;
    }

    /**
     * Set the new number of hits this robot can take
     * 
     * @param shields: the number of shield bars available
     */
    public void setShields(int shields) {
        this.shield = shields;

        if (this.shield <= -1) {
            this.status = "DEAD";
        }
    }

    /**
     * Return the robot's current direction
     * 
     * @return String: the robot direction (NORTH, SOUTH, EAST, or WEST)
     */
    public String getDirection() {
        return this.direction;
    }

    /**
     * Set the robot's direction
     * 
     * @param newDirection : the new robot direction (NORTH, EAST, SOUTH, or WEST)
     */
    public void setDirection(String newDirection) {
        this.direction = newDirection;
    }

    /**
     * Get the distance this robot can see
     * 
     * @return int: the distance (in steps) this robot can see from itself
     */
    public int getVisibility() {
        return this.visibility;
    }

    /**
     * Set the robot's visibility
     * 
     * @param visibility: the new number of steps this robot can see away from
     *                    itself
     */
    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    /**
     * Set the time taken to set mine
     * 
     * @param mine: the number of seconds this robot needs to set mine
     */
    public void setMine(int mine) {
        this.mine = mine;
    }

    /**
     * Get the amount of time it takes this robot to set mine
     * 
     * @return int: the time take to set mine
     */
    public int getMine() {
        return this.mine;
    }

    public String getLastAttempt() {
        return this.lastAttempt;
    }

    /**
     * Set the time taken to reload a full clip
     * 
     * @param reload: the time (in seconds) taken to reload fully
     */
    public void setReload(int reload) {
        this.reload = reload;
    }

    /**
     * Get the number of seconds taken to reload fully
     * 
     * @return int: the seconds this robot takes to reload
     */
    public int getReload() {
        return this.reload;
    }

    /**    public string Id { get; set; }

    public string FirstName { get; set; }
    public string LastName { get; set; }

    public string Address { get; set; }
    public string PostCode { get; set; }
    public string City { get; set; }
    public string Country { get; set; }
     * Get the time taken to repair to full health
     * 
     * @return int: the time taken (in seconds) to repair this robot
     */
    public int getRepair() {
        return this.repair;
    }

    /**
     * Set the time this robot needs to repair fully
     * 
     * @param repair: the new time (in seconds) it will take this robot to repair
     *                itself
     */
    public void setRepair(int repair) {
        this.repair = repair;
    }

    /** */
    public List<int[]> findObstacles() {
        List<int[]> obstacles = new ArrayList<>();

        for (int[] block : World.getObstacles()) {
            if (Math.abs(block[0] - pos[0]) <= visibility && Math.abs(block[1] - pos[1]) <= visibility) {
                obstacles.add(block);
            }
        }

        return obstacles;
    }

    /** */
    public List<int[]> findPits() {
        List<int[]> pits = new ArrayList<>();

        for (int[] block : World.getPits()) {
            if (Math.abs(block[0] - pos[0]) <= visibility && Math.abs(block[1] - pos[1]) <= visibility) {
                pits.add(block);
            }
        }

        return pits;
    }

    /**
     * Move the robot in its current direction by the number of steps given
     * 
     * @param nrSteps number of steps to move. Positive number for forward,
     *                 negative for back
     * @return boolean: true if the robot moved successfully.
     */
    public boolean move(int nrSteps) {
        int[] currentPosition = this.getPosition();
        int[] newPosition;

        switch (this.getDirection()) {
        case "NORTH":
            newPosition = new int[] { currentPosition[0], currentPosition[1] + nrSteps };
            break;
        case "SOUTH":
            newPosition = new int[] { currentPosition[0], currentPosition[1] - nrSteps };
            break;
        case "EAST":
            newPosition = new int[] { currentPosition[0] + nrSteps, currentPosition[1] };
            break;
        case "WEST":
            newPosition = new int[] { currentPosition[0] - nrSteps, currentPosition[1] };
            break;
        default:
            return false;
        }
        if(!movingAllowed(currentPosition,newPosition)){
            return false;
        }
        this.setPosition(newPosition);


        return true;
    }

    /**
     * Checks if the movement is allowed
     * @param currentPosition current position
     * @param newPosition for new position
     * @return false if is not allowed, true if is allowed
     */
    Boolean movingAllowed(int[] currentPosition,int[] newPosition){
        if (this.world != null) {
            //if robot is on the pit
            if (this.world.isPitOnPath(currentPosition[0], currentPosition[1], newPosition[0], newPosition[1])) {
                this.status = "DEAD";
                this.lastAttempt = "Fell";
                return false;
            }
            //if the obstacle is on the way
            if (this.world.isObstacleOnPath(currentPosition[0], currentPosition[1], newPosition[0], newPosition[1])) {
                this.lastAttempt = "Obstructed";
                return false;
            }
            //if mine is on the way
            if (this.world.isMineOnPath(currentPosition[0], currentPosition[1], newPosition[0], newPosition[1])){
                this.setShields(this.shield - 3);
            }
            //if is on the edge
            if(this.world.isOnEdge(currentPosition[0], currentPosition[1], newPosition[0], newPosition[1])){
                this.lastAttempt = getEdgeString();
                return false;
            }

        }
        return true;
    }

    private String getEdgeString() {
        if(this.pos[1] == size/2 & Objects.equals(this.direction, "NORTH")){
            return "At the NORTH edge";
        }
        else if(this.pos[0] == size/2 & Objects.equals(this.direction, "EAST")){
            return "At the EAST edge";
        }
        else if(this.pos[1] == -size/2 & Objects.equals(this.direction, "SOUTH")){
            return "At the SOUTH edge";
        }
        else{
            return "At the WEST edge";
        }
    }

    private String[] mkString(int[] newPosition) {
        return new String[]{String.valueOf(newPosition[0]), String.valueOf(newPosition[1])};
    }

    /**
     * Turn the robot 90 degrees in the given direction
     * 
     * @param rightTurn : true if the robot should turn right, false if left
     */
    public void turnRobot(boolean rightTurn) {
        switch (this.getDirection()) {
        case "NORTH":
            TurnIfNorth(rightTurn);
            break;
        case "SOUTH":
            TurnIfSouth(rightTurn);
            break;
        case "EAST":
            TurnIfEast(rightTurn);
            break;
        case "WEST":
            TurnIfWest(rightTurn);
        }
    }

    /**
     * Turn East or west
     * @param rightTurn true or false
     */
    void TurnIfNorth(Boolean rightTurn){
        if (rightTurn) {
            this.setDirection("EAST");
        } else {
            this.setDirection("WEST");
        }
    }

    /**
     * Turn West or East
     * @param rightTurn true or false
     */
    void TurnIfSouth(Boolean rightTurn){
        if (rightTurn) {
            this.setDirection("WEST");
        } else {
            this.setDirection("EAST");
        }
    }

    /**
     * Turn South or North
     * @param rightTurn true or false
     */
    void TurnIfEast(Boolean rightTurn){
        if (rightTurn) {
            this.setDirection("SOUTH");
        } else {
            this.setDirection("NORTH");
        }
    }

    /**
     * Turn North or South
     * @param rightTurn true or false
     */
    void TurnIfWest(Boolean rightTurn){
        if (rightTurn) {
            this.setDirection("NORTH");
        } else {
            this.setDirection("SOUTH");
        }
    }

    public void placeMine() {
        int[] where = { this.getPosition()[0], this.getPosition()[1] };
        if (!this.move(1)) {
            this.setShields(this.shield - 3);
            return;
        }
        this.world.placeMine(where[0], where[1]);
        this.setBound(mine);
        this.status = "SETMINE";
    }

    public void setType(String string) {
        this.type = string;
    }

    public String getType(){
        return this.type;
    }

    public void setDamage(int intValue) {
        this.damage = intValue;
    }

    public int getDamage(){
        return this.damage;
    }

    public World getWorld(){ return this.world;}
}
