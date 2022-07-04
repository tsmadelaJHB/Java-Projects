package za.co.wethinkcode.robotworlds;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.Serializable;

public class Response implements Serializable {
    private String result;

    private JsonObject data = new JsonObject();

    private JsonObject state = new JsonObject();

    public void setResult(String result){
        this.result = result;
    }

    public void setData(String key, JsonObject value){
        data.add(key,value.get(key));
    }

    public String getResult(){
        return result;
    }

    public void setState(Robot robot){
        JsonArray jPosition = new JsonArray();
        jPosition.add(robot.getPosition().getX());
        jPosition.add(robot.getPosition().getY());
        state.add("Position",jPosition);
        state.addProperty("Direction",robot.getCurrentDirection().toString());
        state.addProperty("Shields",robot.getShieldhp().getShieldHp());
        state.addProperty("Shots",robot.getMagazine().getAmmo());
        state.addProperty("status",robot.getStatus());
    }

    public JsonObject getData(){
        return data;
    }

    public JsonObject getState() {
        return state;
    }
}

