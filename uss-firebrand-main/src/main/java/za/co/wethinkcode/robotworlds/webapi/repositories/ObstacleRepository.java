package za.co.wethinkcode.robotworlds.webapi.repositories;

import java.sql.*;

/***
 * This class contains methods, responsible for interacting with
 * the obstacles table
 *
 * @author              fkalombo
 * @iteration           4
 * @since               2021-10-21
 */
public class ObstacleRepository {

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:RDB.db");
    }

    /**
     * Get's a list of all the obstacles associated
     * with the specified world Id
     * @return a list of obstacles
     */
    public ResultSet getAllObstaclesByWorldId(Integer worldId) throws SQLException {
        Statement sqlStatement = getConnection().createStatement();
        return sqlStatement.executeQuery("SELECT * FROM Obstacles WHERE WorldId='" + worldId + "'");
    }
}
