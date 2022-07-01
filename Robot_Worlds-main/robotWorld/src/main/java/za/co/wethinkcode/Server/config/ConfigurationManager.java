package za.co.wethinkcode.Server.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import za.co.wethinkcode.Server.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**this is a singleton class because we only need
 * one configuration manager that will be shared
 * across the whole project
**/

public class ConfigurationManager {

    private static ConfigurationManager myConfigurationManager;
    private static Configuration myCurrentConfiguration;

    private ConfigurationManager() {
    }

    public static ConfigurationManager getInstance() {
        if (myConfigurationManager == null) //if we don't have a configuration manager, it will create one
            myConfigurationManager = new ConfigurationManager();
        return myConfigurationManager;
    }


    /**
     * used to load a configuration file by the path provided
     * it will produce an exception if the file is not at this
     * path though we have no permissions to read it
     **/

    public void loadConfigurationFile(String filePath) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(filePath);
        } catch (FileNotFoundException e) {
            throw new ConfigurationExceptions(e);
        }
        StringBuffer stringBuffer = new StringBuffer();
        int i;
        try {
            while ((i = fileReader.read()) != -1) {
                stringBuffer.append((char) i);
            }
        }
        catch (IOException e) {
            throw new ConfigurationExceptions(e);
        }
        JsonNode conf = null;
        try {
            conf = Json.parse(stringBuffer.toString());
        } catch (IOException e) {
            throw new ConfigurationExceptions("Error parsing the Configuration File");
        }
        try {
            myCurrentConfiguration = Json.fromJson(conf, Configuration.class);
        } catch (JsonProcessingException e) {
            throw new ConfigurationExceptions("Error parsing the Configuration File, internal");
        }
    }

    /**
     * returns the current loaded configuration
     * throws an exception when there is no configuration loaded
     **/
    public Configuration getCurrentConfiguration() {
        if(myCurrentConfiguration == null){
            throw new ConfigurationExceptions("No Current Configuration Set.");
        }
        return myCurrentConfiguration;
    }
}
