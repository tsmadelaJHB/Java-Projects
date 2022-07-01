package za.co.wethinkcode.server.database;

import java.sql.*;

public class ObjectTable {

    //Main for Test Locally
    public static void main(String[] args) {
        addObject("Obstacle", 2, 2,"Bongi");
    }

    public static void addObject(String object, int x, int y,String name) {

//        connecting to database
        String dbUrl = "jdbc:sqlite:robotWorldDatabase";
        try (final Connection connection = DriverManager.getConnection(dbUrl)) {
            System.out.println("Connected to database ");
            createData(connection, object, x, y,name);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }


    /**
     * Add a new object to the database
     * @param object the object to be added
     * @param x position of obstacle to be added
     * @param y position of obstacle to be added
     * @return  True if the object was added false if not
     */

    private static Boolean createData(final Connection connection, String object, int x, int y,String name)
            throws SQLException {

//        gets object_id of object from obstacles_type
        String id = "";

        try (final Statement stmt = connection.createStatement()) {
            String ChangeObject = "\"" + object + "\"";
            boolean getObjectId = stmt.execute(
                    "SELECT object_id FROM object_type WHERE type=" + ChangeObject
            );
            try (ResultSet results = stmt.getResultSet()) {
                String b = "";
                while (results.next()) {

                    id = results.getString("object_id");
                    b = id;
                    System.out.println(b);
                }
            }
// checks objects of the same positions are not added on the table

            boolean CheckXandY = stmt.execute(
                    "SELECT x_position,y_position,world_name FROM object"
            );
            try (ResultSet results = stmt.getResultSet()) {

                while (results.next()) {
                    final int y_position = results.getInt("y_position");
                    final int x_position = results.getInt("x_position");
                    String world_name = results.getString("world_name");

                    if ( y_position == y && x_position == x && world_name.equalsIgnoreCase(name)) {
                        System.out.println("Object already exists in the Object table");
                        connection.close();
                        return false;


                    }
                }
            }
            String Values = "\"" + id + "\", 1," + x + "," + y + ",\""+name+"\"";
            System.out.println(Values);

           boolean gotAResultSet = stmt.execute(
                        "INSERT INTO object(object_id, size, y_position, x_position,world_name) VALUES ("+Values+")"
           );
                if (gotAResultSet) {
                    throw new RuntimeException("Unexpectedly got a SQL resultset.");
                } else {
                    final int updateCount = stmt.getUpdateCount();
                    if (updateCount == 1) {
                        System.out.println("Object added into Object Table.");
                    } else {
                        throw new RuntimeException("Expected 1 row to be inserted, but got " + updateCount);
                    }
                }
            } catch (RuntimeException e) {
                return false;
            }
            return true;
        }

    }



