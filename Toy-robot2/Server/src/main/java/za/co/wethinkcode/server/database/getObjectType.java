package za.co.wethinkcode.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class getObjectType {
    public static void main(String[] args ) {
        new getObjectType();
    }

    /**
     * Connects to the database
     * Once connected it will call readData
     */

    private String dbUrl = "jdbc:sqlite:robotWorldDatabase";

    private getObjectType() {

        try( final Connection connection = DriverManager.getConnection( dbUrl ) ){
            System.out.println( "Connected to database " );
            readData( connection );
        }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
    }

    /**
     * readData will retrieve data from the Database
     * The object_type name will be added to a list
     * @return a list of all the object types as string names
     */

    private List<String> readData(final Connection connection )
            throws SQLException {
        List<String> listType = new ArrayList<>();
        try (final Statement stmt = connection.createStatement()) {
            boolean gotAResultSet = stmt.execute(
                    "SELECT * "
                            + "FROM object_type "
            );
            if (!gotAResultSet) {
                throw new RuntimeException("Expected a SQL resultSet, but we got an update count instead!");
            }
            try (ResultSet results = stmt.getResultSet()) {
                while (results.next()) {
                    final String objectName = results.getString("type");

                    listType.add(objectName);
                    System.out.println(listType);
                }
            }
        }
        return listType;
    }
}