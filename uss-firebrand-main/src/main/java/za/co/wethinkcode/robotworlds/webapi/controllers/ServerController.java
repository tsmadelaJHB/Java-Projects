package za.co.wethinkcode.robotworlds.webapi.controllers;

import io.javalin.Javalin;
import za.co.wethinkcode.robotworlds.webapi.repositories.ClientConnectionHandlerService;
import za.co.wethinkcode.robotworlds.webapi.repositories.ObstacleRepository;
import za.co.wethinkcode.robotworlds.webapi.repositories.WorldRepository;
import za.co.wethinkcode.robotworlds.webapi.services.ObstacleCommandHandlerService;
import za.co.wethinkcode.robotworlds.webapi.services.RobotCommandHandlerService;

/***
 * APIServer servlet web server, exposes supported endpoints
 *
 * @author              fkalombo
 * @iteration           4
 * @since               2021-10-21
 */
public class ServerController {
    private final Javalin server;

    /**
     * Starts running the APIServer on the specified port
     * @param port - the port to run the server on
     */
    public void start(int port) {
        this.server.start(port);
        System.out.println("API Server Running On Port : " + port);
    }

    public void stop() {
        System.out.println("Stopping API Server...");
        this.server.stop();
    }

    public ServerController() {
        server = Javalin.create(config -> {
            config.defaultContentType = "application/json";
        });

        ObstacleCommandHandlerService obstacleCommandHandlerService = new ObstacleCommandHandlerService(
                new WorldRepository(), new ObstacleRepository());

        this.server.get("/world/{name}", obstacleCommandHandlerService::getAllObjectsByWorldName);
        this.server.get("/world", obstacleCommandHandlerService::getAllObjectsForCurrentWorld);
        this.server.post("/robot/launch", RobotCommandHandlerService::launchRobot);
        this.server.post("/robot/commands", RobotCommandHandlerService::otherCommands);
        this.server.get("/connect/{ip}/{port}", ClientConnectionHandlerService::Connect);
    }
}
