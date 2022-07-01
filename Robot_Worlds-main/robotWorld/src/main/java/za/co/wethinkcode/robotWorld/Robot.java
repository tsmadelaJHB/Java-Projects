package za.co.wethinkcode.robotWorld;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Robot {
    private final String name;
    private Position position;
    private IWorld.Direction direction;
    private int shield;
    private final int maxShield;
    private Gun gun;
    private String status;
    Map<String, Object> response;

    public Robot(String name, Position position, int max, Gun gun){
        this.name = name;
        this.position = position;
        this.direction = IWorld.Direction.NORTH;
        this.shield = max;
        this.maxShield = max;
        this.gun = gun;
        this.status = "NORMAL";
    }

    public Gun getGun() {
        return this.gun;
    }

    public void resetShield(){
        this.shield = this.maxShield;
    }

    public int getMaxShield(){return this.maxShield;}

    public int getShield() {
        return this.shield;
    }

    public void decreaseShield() {
        if (shield > 0) {
            this.shield -= 1;
        }
        if (shield == 0){
            this.status = "DEAD";
        }
    }

    public void increaseShied(){
        if (this.shield < maxShield){
            this.shield+=1;
        }
    }

    public void displayResponse(String message){
        System.out.println(message);
    }

    public String getName() {
        return this.name;
    }

    public String getStatus(){
        return this.status;
    }

    public IWorld.Direction getDirection() {
        return this.direction;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position){
        this.position = position;
    }

    public void setDirection(IWorld.Direction direction) {
        this.direction = direction;
    }

    public void setStatus(String statement) {
        this.status = statement;
    }

    public void setResponse(Map<Object, Object> data) {
        Map<String, Object> responseTemp = new HashMap<String, Object>();

        responseTemp.put("result", "OK");
        responseTemp.put("data", data);
        responseTemp.put("state", getState());

        this.response = responseTemp;
    }

    public void setError(Map<Object, Object> data) {
        Map<String, Object> responseTemp = new HashMap<String, Object>();

        responseTemp.put("result", "Error");
        responseTemp.put("data", data);

        this.response = responseTemp;
    }

    public Map<String, Object> getResponse(){
        return this.response;
    }

    public Object getResponseData(){
        return this.response.get("data");
    }

    public Map<String, Object> getState(){
        Map<String, Object> state = new HashMap<String, Object>();
        state.put("position", Arrays.toString(new int[] {position.getX(), position.getY()}));
        state.put("direction", this.direction.toString());
        state.put("shields", this.shield);
        state.put("shots", this.gun.getShots());
        state.put("status", this.status);

        return state;
    }

    public boolean blocksPath(Position a, Position b) {
        int x = position.getX();
        int y = position.getY();
        if(a.getX() == b.getX() && x == a.getX()) {
            if(a.getY() > b.getY()) {
                return b.getY() <= y && y <= a.getY();
            } else {
                return a.getY() <= y && y <= b.getY();
            }
        } 
        
        else if(a.getY() == b.getY() && y == a.getY()) {
            if(a.getX() > b.getX()) {
                return b.getX() <= x && x <= a.getX();
            } else {
                return a.getX() <= x && x <= b.getX();
            }
        }
        return false;
    }
}
