package za.co.wethinkcode.robotworlds.servercommand;

import za.co.wethinkcode.robotworlds.MultiServers;
import za.co.wethinkcode.robotworlds.world.Mine;
import za.co.wethinkcode.robotworlds.world.Obstacle;
import za.co.wethinkcode.robotworlds.world.Pits;

import java.sql.*;
import java.util.Objects;
import java.util.Scanner;

public class Save{
    static Scanner scanner;
    ResultSet resultSet;
    int lastWorld;
    int lastEntity;

    public void Handle_Save(){
        try(Connection dbConnection = DriverManager.getConnection("jdbc:sqlite:RDB.db")){
            HandleTables(dbConnection);
            Statement stm = dbConnection.createStatement();
            String worldName = GetWorldName();
            resultSet = stm.executeQuery("Select * from Worlds;");
            while (resultSet.next()){
                lastWorld = resultSet.getInt("WorldID");
                if(Objects.equals(worldName, resultSet.getString("WorldName"))){
                    String answer = getInput("World name already exists. Would you like to overwrite this world? (yes/no)");
                        if(answer.equalsIgnoreCase("yes")){
                            WorldDelete(dbConnection,worldName);
                            EntityDelete(dbConnection,lastWorld);
                            lastWorld = lastWorld -1;
                            break;
                        }else {
                            System.out.println("Operation canceled Canceled ^_^ .");
                            return;
                        }
                }
            }
            lastWorld = lastWorld+1;
            String sqlWorld = " insert into Worlds (WorldId, WorldName, WorldSize)"
                        + " values ("+lastWorld+", "+"\""+worldName+"\", "+MultiServers.IWorldSize+")";
            stm.execute(sqlWorld);

            System.out.println("World has been saved, Working on world entities...");
            resultSet = stm.executeQuery("Select * from Obstacles");
            while (resultSet.next()){
                lastEntity = resultSet.getInt("ObsId");
            }
            lastEntity++;
            for (Obstacle obstacle : MultiServers.World.getObstacles()){
                String sqlObstacle = "insert into Obstacles (ObsId,WorldID, PositionTL, PositionBR, type)"
                        + "values ("+lastEntity +", " +lastWorld+", \""+obstacle.getTopLeft().posAsString()+ "\", \""+ obstacle.getBottomRight().posAsString()+"\", \"obstacle\")" ;
                stm.execute(sqlObstacle);
                System.out.println("Saved Obstacle--> "+obstacle.getTopLeft().posAsString() + " to " + obstacle.getBottomRight().posAsString());
                lastEntity++;
            }

            for(Pits pits : MultiServers.World.getPits()){
                String sqlObstacle = "insert into Obstacles (ObsId,WorldID, PositionTL, PositionBR, type)"
                        + "values ("+lastEntity+", " +lastWorld+", \""+pits.getTopLeft().posAsString()+ "\", \""+ pits.getBottomRight().posAsString()+"\", \"Pit\")" ;
                stm.execute(sqlObstacle);
                System.out.println("Saved Pit--> "+pits.getTopLeft().posAsString() + " to " + pits.getBottomRight().posAsString());
                lastEntity++;
            }

            for(Mine mine : MultiServers.World.getMines()){
                String sqlMines = "insert into Obstacles (ObsId,WorldID, PositionTL, PositionBR, type)"
                        + "values ("+lastEntity+", " +lastWorld+", \""+mine.minePos().posAsString()+ "\", \""+ mine.minePos().posAsString()+"\", \"mine\")" ;
                stm.execute(sqlMines);
                System.out.println("Saved Mine --> "+ mine.minePos().posAsString());
                lastEntity++;
            }

            System.out.println("World entities saved. \nOperation Complete ^_^ ");

        }catch (SQLException x){
            System.err.println(x.getMessage());
        }
    }

    public void WorldDelete(Connection connection, String worldName){
        try (Statement statement = connection.createStatement()){
            String WorldDelete = "DELETE FROM Worlds WHERE WorldName is \"" + worldName + "\"";
            statement.execute(WorldDelete);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void EntityDelete(Connection connection, int worldId){
        try(Statement statement = connection.createStatement()){
            String ObsDelete = "DELETE FROM Obstacles WHERE WorldId is "+ worldId;
            statement.execute(ObsDelete);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void worldOverWriter(Connection connection, String worldName){
        try (Statement statement = connection.createStatement()) {
            String WorldUpdate = "UPDATE Worlds\n" +
                                "SET WorldSize = " +MultiServers.IWorldSize +"\n" +
                                "WHERE WorldName = \"" + worldName + "\"";
            statement.execute(WorldUpdate);
//            ResultSet Obstacle = stm.executeQuery("Select * from Obstacles");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ObstacleOverWriter(Connection connection){
        try (Statement stm = connection.createStatement()){
            String ObsUpdate = "";
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void HandleTables(Connection connection){
        String worlds = "CREATE TABLE IF NOT EXISTS \"Worlds\" (\n" +
                "\t\"WorldId\"\tINTEGER NOT NULL UNIQUE,\n" +
                "\t\"WorldName\"\tTEXT NOT NULL UNIQUE,\n" +
                "\t\"WorldSize\"\tINTEGER NOT NULL,\n" +
                "\tPRIMARY KEY(\"WorldId\")\n" +
                ")";

        String WorldContent ="CREATE TABLE IF NOT EXISTS\"Obstacles\" (\n" +
                "\t\"ObsId\"\tINTEGER UNIQUE,\n" +
                "\t\"WorldID\"\tINTEGER,\n" +
                "\t\"PositionTL\"\tTEXT,\n" +
                "\t\"PositionBR\"\tTEXT,\n" +
                "\t\"type\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"ObsId\" AUTOINCREMENT)\n" +
                ")";
        try {
            Statement stm = connection.createStatement();
            stm.execute(worlds);
            stm.execute(WorldContent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String GetWorldName(){
        scanner = new Scanner(System.in);
        return getInput("What would you like to name your world: ");
    }


    private static String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();

        while (input.isBlank()) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input;
    }
}
