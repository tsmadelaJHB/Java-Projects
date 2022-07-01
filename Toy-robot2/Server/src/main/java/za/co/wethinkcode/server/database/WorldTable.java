package za.co.wethinkcode.server.database;

import java.sql.*;
import java.util.Scanner;


public abstract class WorldTable implements robotWorldDatabaseDAO{


    /**
     * get the world from the table
     * @param name of the world
     * @throws SQLException
     */
    public static void getDBWorld(String name) throws SQLException {
        String dbUrl = "jdbc:sqlite:robotWorldDatabase";

        final Connection connection = DriverManager.getConnection( dbUrl );
        //get the world in world and in object
        try (final Statement stmt = connection.createStatement()) {
            String where = "'"+name+"'";
            boolean gotAResultSet = stmt.execute(
                    "SELECT *,object_id,x_position,y_position FROM world,object WHERE name="+where+" AND" +
                            " world_name="+where
            );

            //display the world nicely
            try (ResultSet results = stmt.getResultSet()) {
                String b = "";
                System.out.println("id\tname\tsize\tobj_ID\tx\ty\tObjectName");
                while (results.next()) {

                    final String id = results.getString("id");
                    final String WorldName = results.getString("name");
                    final String WorldSize = results.getString("size");
                    final String Obj_id = results.getString("object_id");
                    final String x_posi = results.getString("x_position");
                    final String y_posi = results.getString("y_position");

                    String ObjectName=getObjectName(Integer.parseInt(Obj_id));

                    b= id + "\t" +
                            WorldName + "\t\t" +
                            WorldSize + "\t\t" +
                            Obj_id +"\t"+
                            x_posi+"\t"+
                            y_posi+"\t"+
                            ObjectName
                    ;
                    System.out.println(b);

                }
                System.out.println("Successfully retrieving the world "+name+" from World table!");
                if(b.equals("")){
                    System.out.println("World "+name+" does not exists in the world");
                }
            } catch (SQLException e) {

                System.err.println(e.getMessage());
            }

        }}

    /**
     * get the name of the object
     * @param id for object type id
     * @return the name of the object
     * @throws SQLException sql exception
     */
    public static String getObjectName(int id)  throws SQLException {
        String dbUrl = "jdbc:sqlite:robotWorldDatabase";
        String ObjectName = "";

        final Connection connection = DriverManager.getConnection(dbUrl);
        //get the world in world and in object
        try (final Statement stmt = connection.createStatement()) {
            boolean gotAResultSet = stmt.execute(
                    "SELECT type FROM object_type WHERE object_id=" +id
            );
            try (ResultSet results = stmt.getResultSet()) {
                while (results.next()) {
                    ObjectName = results.getString("type");
                }

            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
        return ObjectName;
    }

    private static Connection c = null;

    /**
     * Adding the world
     * @param name of the world
     * @param size of the world
     * @return true if created, false if not
     * @throws SQLException
     */
    public static Boolean addDBWorld(String name, int size) throws SQLException {
        String dbUrl = "jdbc:sqlite:robotWorldDatabase";

        Connection connection = DriverManager.getConnection( dbUrl );
        final Statement stmt = connection.createStatement();

        //Check if the world exist
        boolean gotAResultSet = stmt.execute(
                "SELECT * FROM world"
        );
        ResultSet results = stmt.getResultSet();
        while (results.next()) {
            final String WorldName = results.getString("name");
            if(WorldName.equals(name)){
                System.out.println("Name "+name+" already exists in the world table");
                connection.close();
                connection = DriverManager.getConnection(dbUrl);
                return false;
            }
        }

        //Add the world to the table
        try  {
            String value = "('" + name + "'," + size + "," + "1" + ")";
            String inserting = "INSERT INTO world(name,size,objects) VALUES " + value;
            boolean addAResultSet = stmt.execute(
                    inserting
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        System.out.println("World "+name+" is created.");
        if(c == null) {
            c = (Connection) DriverManager.getConnection(dbUrl);
        } else {
            c.close();
            c = (Connection) DriverManager.getConnection(dbUrl);
        }
        return true;
    }

    }
