package za.co.wethinkcode.robotworlds;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static za.co.wethinkcode.robotworlds.AcceptClient.isBomber;
import static za.co.wethinkcode.robotworlds.AcceptClient.isSniper;

public class WorldConfig {
    private String worldWidth, worldHeight, repairShield, shieldStrengthBasic, reloadWeapon, setMine, robotVisibility;
    private List<String> allProperties = new ArrayList<>();
    private final boolean hpCheck;
    private final boolean mineCheck;

    public WorldConfig() {
        this.hpCheck = isSniper();
        this.mineCheck = isBomber();
        try {
            getProperties();
        } catch (IOException e){
            System.out.print("Could not get world properties: " +e+ "\n");
        }
    }
    
    /** Try creating a properties object. Use inputstream variable to store the name of the config file and find it
     * in the class path. If it's found, load the file.
     * Stores the key and value from properties into String variables of the same name.
     * Adds every property into a List<String> of properties which will be used in the Getters and Setters, to be used
     * globally.
     */
    public List<String> getProperties() throws IOException {
        InputStream inputStream;

        try {
            Properties properties = new Properties();
            inputStream = new FileInputStream("src/main/java/za/co/wethinkcode/robotworlds/config.properties");

            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("Property file '" + inputStream + "' not found");
            }
            String worldWidth = properties.getProperty("worldWidth");
            String worldHeight = properties.getProperty("worldHeight");
            String repairShield = properties.getProperty("repairShield");
            String shieldStrengthBasic = properties.getProperty("shieldStrengthBasic");
            String reloadWeapon = properties.getProperty("reloadWeapon");
            String setMine = properties.getProperty("setMine");
            String robotVisibility = properties.getProperty("robotVisibility");
            String shieldRestoreDelay = properties.getProperty("shieldRestoreDelay");
            String shieldStrengthSniper = properties.getProperty("shieldStrengthSniper");
            String noMine = properties.getProperty("noMine");

            allProperties.add(worldWidth);
            allProperties.add(worldHeight);
            allProperties.add(repairShield);
            allProperties.add(shieldStrengthBasic);
            allProperties.add(reloadWeapon);
            allProperties.add(setMine);
            allProperties.add(robotVisibility);
            allProperties.add(shieldRestoreDelay);
            allProperties.add(shieldStrengthSniper);
            allProperties.add(noMine);


            inputStream.close();

        } catch (Exception e) {
            System.out.print("Exception: " + e);
        }
        return allProperties;
    }

    public Integer getWorldWidth() {
        String s = allProperties.get(0);
        return Integer.parseInt(s);
    }

    public Integer getWorldHeight() {
        String s = allProperties.get(1);
        return Integer.parseInt(s);
    }

    public Integer getRepairShield() {
        String s = allProperties.get(2);
        return Integer.parseInt(s);
    }

    public Integer getShieldStrength() {
        String s;
        if (hpCheck) {
            s = allProperties.get(8);
        }else {
            s = allProperties.get(3);
        }
        return Integer.parseInt(s);
    }

    public Integer getReloadWeapon() {
        String s = allProperties.get(4);
        return Integer.parseInt(s);
    }

    public Integer getSetMine() {
        String s;
        if(mineCheck) {
             s = allProperties.get(5);
        } else {
             s = allProperties.get(9);
        }
        return Integer.parseInt(s);
    }

    public Integer getRobotVisibility() {
        String s = allProperties.get(6);
        return Integer.parseInt(s);
    }

    public Integer getShieldRestoreDelay() {
        String s = allProperties.get(7);
        return Integer.parseInt(s);
    }
}

