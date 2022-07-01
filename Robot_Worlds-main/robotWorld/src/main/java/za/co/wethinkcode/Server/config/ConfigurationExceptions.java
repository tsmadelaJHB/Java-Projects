package za.co.wethinkcode.Server.config;

import java.io.IOException;

/* Exception handling for the configurations*/

public class ConfigurationExceptions extends RuntimeException{

    public ConfigurationExceptions(IOException e){
    }

    public ConfigurationExceptions(String message){
        super(message);
    }

    public ConfigurationExceptions(String message, Throwable cause){
        super(message, cause);
    }

    public ConfigurationExceptions(Throwable cause){
        super(cause);
    }

}