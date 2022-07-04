package za.co.wethinkcode.robotworlds;

import com.google.gson.JsonObject;
import za.co.wethinkcode.robotworlds.command.Ammo;
import za.co.wethinkcode.robotworlds.command.Command;
import za.co.wethinkcode.robotworlds.command.Shield;
import za.co.wethinkcode.robotworlds.world.IWorld;
import za.co.wethinkcode.robotworlds.world.Obstacle;
import za.co.wethinkcode.robotworlds.world.Pits;
import za.co.wethinkcode.robotworlds.world.Mine;

import static za.co.wethinkcode.robotworlds.world.AbstractWorld.RandomGenerator;

public class Robot extends WorldNavigator {

    JsonObject RobotResponse  = new JsonObject();
    Response serverResponse = new Response();
    WorldConfig config = new WorldConfig();
    private int shieldStrength = config.getShieldStrength();
    private Shield hp = new Shield(shieldStrength);
    public static Position CENTRE = new Position(RandomGenerator(100,-100,100)
            ,RandomGenerator(200,-200,200));
    public static int directionIndex = 0;
    private IWorldNavigator.Direction currentDirection;
    private String status;

    private final String name;
    private final String type;
    private final Position spwan;

    public Shield shieldhp;
    public Ammo Magazine;
    public RobotType robotType;

    IWorld World;

    public Robot(String name,String type,Position spawn) {
        this.name = name;
        robotType = new RobotType(type);
        this.type = type;
        this.spwan = spawn;
        this.status = "ready";
        this.setPosition(spawn);

        this.shieldhp = new Shield(robotType.getShields());

        this.Magazine = new Ammo(robotType.getAmmo());


        this.currentDirection = Direction.NORTH;

    }

    public void setServerResponse(Response response){
        this.serverResponse = response;
    }

    public IWorld getWorld() {
        return World;
    }

    public void setWorld(IWorld World) {
        this.World = World;
    }

    /** Gets Robot's X and Y. Increments either the X or Y axis depending on the direction the Robot is facing. If
     * this new position is within the boundary of the board (Top Left & Bottom Right), it's a success.
     * If it's obstructed by an obstacle or pit it's a failure.
     * If it's obstructed by a mine, health takes damage.
     */
    public IWorldNavigator.UpdateResponse updatePosition(int nrSteps) {
        int newX = this.getPosition().getX();
        int newY = this.getPosition().getY();

        if (IWorldNavigator.Direction.NORTH.equals(this.getCurrentDirection())) {
            newY = newY + nrSteps;
        }
        else if (IWorldNavigator.Direction.SOUTH.equals(this.getCurrentDirection())){
            newY = newY - nrSteps;
        }
        else if (IWorldNavigator.Direction.EAST.equals(this.getCurrentDirection())) {
            newX = newX + nrSteps;
        }
        else if (IWorldNavigator.Direction.WEST.equals(this.getCurrentDirection())) {
            newX = newX - nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        Position OldPosition = new Position(this.getPosition().getX(),this.getPosition().getY());
        for (Obstacle obs : getObstacles()) {
            if (obs.blocksPath(OldPosition, newPosition)) {
                this.setPosition(OldPosition);
                return IWorldNavigator.UpdateResponse.FAILED_OBSTRUCTED;
            }
        }

        for (Pits pit: getPits()) {
            if (pit.blocksPath(OldPosition,newPosition)){
                return IWorldNavigator.UpdateResponse.FAILED_FELL_INTO_PIT;
            }
        }

        for (Mine mine: getMines()){ // If robot steps on a mine, it incurs damage
            if (mine.goBoom(OldPosition,newPosition))
                updateShieldHp(3);
        }

        if (isNewPositionAllowed(newPosition)){
            this.setPosition(newPosition);
            return IWorldNavigator.UpdateResponse.SUCCESS;
        }
        return IWorldNavigator.UpdateResponse.FAILED_OUTSIDE_WORLD;
    }

    /** Gets the current direction and if turnRight is true, turns right based on the direction it's facing.
     * Else, the direction is set to left
     */
    public void updateDirection(boolean turnRight) {
        if(turnRight){
            if (Direction.EAST.equals(getCurrentDirection())) {setCurrentDirection(Direction.SOUTH);
            }
            else if (Direction.NORTH.equals(getCurrentDirection())) {
                setCurrentDirection(Direction.EAST);
            }
            else if (Direction.SOUTH.equals(getCurrentDirection())) {
                setCurrentDirection(Direction.WEST);
            }
            else if (Direction.WEST.equals(getCurrentDirection())) {
                setCurrentDirection(Direction.NORTH);
            }
        }
        else{
            if (Direction.EAST.equals(getCurrentDirection())) {
                setCurrentDirection(Direction.NORTH);
            }
            else if (Direction.NORTH.equals(getCurrentDirection())) {
                setCurrentDirection(Direction.WEST);
            }
            else if (Direction.SOUTH.equals(getCurrentDirection())) {
                setCurrentDirection(Direction.EAST);
            }
            else if (Direction.WEST.equals(getCurrentDirection())) {
                setCurrentDirection(Direction.SOUTH);
            }
        }
    }

    public String getStatus() {
        return this.status;
    }

    public boolean handleCommand(Command command) {
        return command.execute(this);
    }

    public void updateShieldHp(int damage){
        int HP = this.hp.getShieldHp();
        HP = HP - damage;
        Shield newShield = new Shield(HP);
        System.out.println(HP);
        this.hp = newShield;
    }

    public void shieldRepair() {
        int HP = this.hp.getShieldHp();
        if (HP < 5 && HP >= 0) {
            Shield newShield = (new Shield(shieldStrength));
            this.hp = newShield;
        }
    }

    /** Returns the cardinal direction the robot is facing, represented by numbers 0 - 3. This index is incremented in
     * RightCommand and decremented in LeftCommand.
     */
    public int getCardinalDirection() {return directionIndex;}

    @Override
    public String toString() {
        return this.status;
    }

    public String State(){
        return "NAME              : " + getName()
                +"\nROBOT TYPE        : "   + getType()
                +"\nCURRENT POSITION  : [" + this.getPosition().getX() + "," + this.getPosition().getY() + "] "
                + "\nSHIELD HEALTH     : " + this.hp.getShieldHp()
                + "\nCURRENT DIRECTION : " + check()
                + "\nAMMO              : " + "INSERT NUMBER HERE";
    }

    public String check(){
        if (Direction.EAST.equals(getCurrentDirection())) {
            return "EAST";
        }
        else if (Direction.NORTH.equals(getCurrentDirection())) {
            return "NORTH";
        }
        else if (Direction.SOUTH.equals(getCurrentDirection())) {
            return "SOUTH";
        }
        else if (Direction.WEST.equals(getCurrentDirection())) {
            return "WEST";
        }
        return "up";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Shield getShieldhp() {
        return shieldhp;
    }

    public Ammo getMagazine() {
        return Magazine;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean IsDead(){
        return this.hp.getShieldHp() <=-1;
    }
}
