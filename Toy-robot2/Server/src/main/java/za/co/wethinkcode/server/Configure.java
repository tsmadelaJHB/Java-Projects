package za.co.wethinkcode.server;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Configure {

    /**
     * Setup a robot to follow the config values of the game
     * @param rbt: the robot whose values should be modified
     */
    @SuppressWarnings("unchecked")
    public static void setValues(Robot rbt){
        JSONObject defaults = readConfig();
        
        if (defaults == null || !keysExist(defaults)){
            defaults.clear();

            defaults.put("shield", 5);
            defaults.put("visibility", 5);
            defaults.put("mine", 3);
            defaults.put("reload", 3);
            defaults.put("repair", 3);
        }

        if (!rbt.getName().equals("placeholder") && !rbt.getType().equals("dummy")){
            rbt.setDamage(((Long) defaults.get(rbt.getType())).intValue());
        }
        
        rbt.setDirection("NORTH");

        int worldCap = ((Long) defaults.get("shield")).intValue();
        int shieldCap = rbt.getShields();
        rbt.setShields(shieldCap > worldCap ? worldCap : shieldCap);

        rbt.setMine(((Long) defaults.get("mine")).intValue());
        rbt.setReload(((Long) defaults.get("reload")).intValue());
        rbt.setVisibility(((Long) defaults.get("visibility")).intValue());
        rbt.setRepair(((Long) defaults.get("repair")).intValue());
    }

    /**
     * Read the config file and return its contents as a JSONObject
     * @return JSONObject: the data extracted from the config file.
     * Null if there is a problem reading the file
     */
    private static JSONObject readConfig(){
        
        try{
            JSONParser jsonParser = new JSONParser();
            File f = new File(".robot-config.json");
            String direction = f.getAbsolutePath();
            File config = new File(direction);


            Object object = jsonParser.parse(new FileReader(config));
            return (JSONObject) object;
        }catch(IOException | ParseException e){
            System.out.println("Error reading config. Using alt values. (Make sure you are working in Test-Client-Server");
            return null;
        }
    }

    /**
     * Confirm the config has all the necessary keys and values
     * @param defaults: the data extracted from the config
     * @return boolean: true if all the keys exist.
     */
    private static boolean keysExist(JSONObject defaults){
        boolean visibile = defaults.containsKey("visibility");
        boolean shielded = defaults.containsKey("shield");
        boolean reloaded = defaults.containsKey("reload");
        boolean mining = defaults.containsKey("mine");
        boolean repaired = defaults.containsKey("repair");

        return visibile && shielded && reloaded && mining && repaired;
    }
}
