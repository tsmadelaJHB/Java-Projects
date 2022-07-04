package za.co.wethinkcode.robotworlds.command;

import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import za.co.wethinkcode.robotworlds.Response;
import za.co.wethinkcode.robotworlds.Robot;
import za.co.wethinkcode.robotworlds.WorldConfig;
import za.co.wethinkcode.robotworlds.world.Mine;


public class SetMineCommand extends Command {
    WorldConfig properties = new WorldConfig();
    private final int plant = properties.getSetMine();
    private final int timeDelay = properties.getShieldRestoreDelay();
    public SetMineCommand() {
        super("mine");
    }

    @Override
    public boolean execute(Robot target) {
        JsonObject data = new JsonObject();
        Response response = new Response();
        if(target.robotType.isMine()){
            JSONObject command = new JSONObject();
            command.put("command","forward");
            command.put("arguments","1");
            Mine mine = new Mine(target.getPosition().getX(), target.getPosition().getY());
            target.handleCommand(Command.create(command));
            int HP = target.shieldhp.getShieldHp();
            new Shield(0);
            System.out.println(target.shieldhp.getShieldHp());

            try {
                Thread.sleep(timeDelay); // Time delay while the mine is being set
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            if (target.IsDead())
                return false;

            new Shield(HP);
            MakeMine(mine);
            System.out.println(mine.getMineY() + " " + mine.getMineX());
            target.setStatus("SETMINE.");
            data.addProperty("message","done");
            response.setResult("OK");
            response.setData("message",data);
            response.setState(target);
            target.setServerResponse(response);
        } else {
            data = new JsonObject();
            response = new Response();
            response.setResult("ERROR");
            data.addProperty("message","Invalid robot type");
            response.setData("message",data);
            response.setState(target);
            target.setStatus("Sorry, your Robot type is not programmed to set mines.");
            target.setServerResponse(response);
        }
        return true;
    }
}
