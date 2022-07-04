package za.co.wethinkcode.robotworlds.webapi.services;

import io.javalin.http.Context;
import za.co.wethinkcode.robotworlds.webapi.models.Obstacle;
import za.co.wethinkcode.robotworlds.webapi.repositories.ObstacleRepository;
import za.co.wethinkcode.robotworlds.webapi.repositories.WorldRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/***
 * Handles requests made to the different endpoints.
 * Makes queries to the database and returns the results as json
 *
 * @author              fkalombo
 * @iteration           4
 * @since               2021-10-22
 */
public class ObstacleCommandHandlerService {

    private WorldRepository worldRepository;
    private ObstacleRepository obstacleRepository;

    public ObstacleCommandHandlerService(WorldRepository worldRepository, ObstacleRepository obstacleRepository) {
        this.worldRepository = worldRepository;
        this.obstacleRepository = obstacleRepository;
    }

    /***
     * Gets the all obstacles for world with given name
     * @param context
     */
    public void getAllObjectsByWorldName(Context context) {

        try
        {
            String worldName = context.pathParamAsClass("name", String.class).get();

            int worldId = worldRepository.getWorldIdByWorldName(worldName);

            if (worldId == 0) context.status(404);

            ResultSet resultSet = obstacleRepository.getAllObstaclesByWorldId(worldId);

            List<Obstacle> obstacles = new ArrayList<>();
            while (resultSet.next())
            {
                obstacles.add(Obstacle.create(
                        resultSet.getInt("ObsId"),
                        resultSet.getInt("WorldId"),
                        resultSet.getString("PositionTL"),
                        resultSet.getString("PositionBR"),
                        resultSet.getString("type"))
                );
            }
            context.json(obstacles);
        } catch (SQLException x) { System.err.println(x.getMessage()); }
    }

    /***
     * Gets all objects for the current world and returns them in json format
     * @param context
     */
    public void getAllObjectsForCurrentWorld(Context context)
    {

        try
        {
            int worldId = worldRepository.getWorldIdOfFirstWorldInTable();
            ResultSet resultSet = obstacleRepository.getAllObstaclesByWorldId(worldId);

            List<Obstacle> obstacles = new ArrayList<>();

            while (resultSet.next())
            {
                obstacles.add(Obstacle.create(
                        resultSet.getInt("ObsId"),
                        resultSet.getInt("WorldId"),
                        resultSet.getString("PositionTL"),
                        resultSet.getString("PositionBR"),
                        resultSet.getString("type"))
                );
            }
            context.json(obstacles);

        } catch (SQLException x) { System.err.println(x.getMessage()); }
    }
}
