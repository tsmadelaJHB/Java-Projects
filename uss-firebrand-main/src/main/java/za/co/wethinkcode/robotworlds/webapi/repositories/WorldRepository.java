package za.co.wethinkcode.robotworlds.webapi.repositories;

import java.sql.*;

/***
 * This class is responsible for interacting with the
 * world table
 *
 * @author          fkalombo
 * @iteration       4
 * @since           2021-10-21
 */
public class WorldRepository {

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:RDB.db");
    }

    public Integer getWorldIdOfFirstWorldInTable() throws SQLException {

        Statement sqlStatement = getConnection().createStatement();
        ResultSet firstResultSet = sqlStatement.executeQuery("SELECT WorldId FROM Worlds LIMIT 1;");

        int worldId = 0;
        while (firstResultSet.next()) {
                worldId = firstResultSet.getInt("WorldId");
        };
        return worldId;
    }

    public Integer getWorldIdByWorldName(String name) throws SQLException {

        Statement sqlStatement = getConnection().createStatement();
        ResultSet firstResultSet = sqlStatement.executeQuery("SELECT WorldId FROM Worlds WHERE WorldName='" +
                name + "'");

        int worldId = 0;
        while (firstResultSet.next()) {
            worldId = firstResultSet.getInt("WorldId");
        };
        return worldId;
    }
}
