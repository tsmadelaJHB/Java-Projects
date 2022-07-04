package za.co.wethinkcode.robotworlds.servercommand;

import za.co.wethinkcode.robotworlds.MultiServers;
import za.co.wethinkcode.robotworlds.Position;
import za.co.wethinkcode.robotworlds.world.Mine;
import za.co.wethinkcode.robotworlds.world.Obstacle;
import za.co.wethinkcode.robotworlds.world.Pits;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Restore {
    static Scanner scanner;
    ResultSet resultSet;
    ArrayList<Position> ObstaclesTL = new ArrayList<>();
    ArrayList<Position> ObstaclesBR = new ArrayList<>();
    ArrayList<Position> PitsTL = new ArrayList<>();
    ArrayList<Position> PitsBR = new ArrayList<>();
    ArrayList<Position> Mines = new ArrayList<>();

    public void RestoreHandler() {
        ArrayList<String> Worlds = new ArrayList<>();
        ArrayList<Integer> WorldIds = new ArrayList<Integer>();
        try (Connection dbConnection = DriverManager.getConnection("jdbc:sqlite:RDB.db")) {
            Statement stm = dbConnection.createStatement();
            resultSet = stm.executeQuery("Select * from Worlds;");
            if (resultSet.next()){
                System.out.println("Restorable worlds:");
                System.out.println(" - "+resultSet.getString("WorldName"));
                Worlds.add(resultSet.getString("WorldName"));
                WorldIds.add(resultSet.getInt("WorldId"));
                while (resultSet.next()){
                    System.out.println(" - "+resultSet.getString("WorldName"));
                    Worlds.add(resultSet.getString("WorldName"));
                    WorldIds.add(resultSet.getInt("WorldId"));
                }
                scanner = new Scanner(System.in);
                String World = getInput("Which world would you like to restore:");
                if(Worlds.contains(World)){
                    int i = Worlds.indexOf(World);
                    setObstacles(dbConnection,WorldIds.get(i));
                    setPits(dbConnection,WorldIds.get(i));
                    setMines(dbConnection,WorldIds.get(i));
                    SweepWorld();
                    WorldSet();
                }else{
                    System.out.println("World's Non Existent ");
                    return;
                }
            }else {
                System.out.println("No restorable worlds");
            }
            System.out.println("World \"restored\"");

        } catch (SQLException x) {
            x.printStackTrace();
        }

    }

    public void WorldSet(){
        ArrayList<Obstacle> NewObs = new ArrayList<>();
        ArrayList<Pits> NewPits  = new ArrayList<>();
        ArrayList<Mine> NewMines = new ArrayList<>();

        for(int i = 0; i < ObstaclesTL.size(); i++){
            NewObs.add(new Obstacle(ObstaclesTL.get(i),ObstaclesBR.get(i)));
        }

        for(int i = 0; i < PitsTL.size(); i++){
            NewPits.add(new Pits(PitsTL.get(i),PitsBR.get(i)));
        }

        for (Position mine : Mines) {
            NewMines.add(new Mine(mine.getX(), mine.getY()));
        }

        for (Obstacle obs : NewObs){
            MultiServers.obs.MakeObsList(obs);
        }

        for (Pits pit : NewPits){
            MultiServers.obs.MakePitsList(pit);
        }

        for (Mine mine : NewMines){
            MultiServers.obs.MakeMinesList(mine);
        }

    }

    public void SweepWorld(){
        MultiServers.obs.cleanObstacles();
        MultiServers.obs.cleanPits();
    }


    public void setMines(Connection connection, int WorldId){
        try(Statement stm = connection.createStatement()){
            String obs = "SELECT * FROM Obstacles\n" +
                    "WHERE WorldId is " + WorldId+"\n" +
                    "AND type is \"mine\"";
            resultSet = stm.executeQuery(obs);
            while (resultSet.next()){
                String sCords = resultSet.getString("PositionTL").replaceAll("\\(","").replaceAll("\\)","");
                String[] cords = sCords.split(",");
                Mines.add(StringToPos(cords));
            }
        }catch (SQLException x){
            x.printStackTrace();
        }

    }

    public void setPits(Connection connection, int WorldId){
        try(Statement stm = connection.createStatement()){
            String pits = "SELECT * FROM Obstacles\n" +
                    "WHERE WorldId is " + WorldId+"\n" +
                    "AND type is \"Pit\"";
            resultSet = stm.executeQuery(pits);
            while (resultSet.next()){
                String sCordsTL = resultSet.getString("PositionTL").replaceAll("\\(","").replaceAll("\\)","");
                String sCordsBR = resultSet.getString("PositionBR").replaceAll("\\(","").replaceAll("\\)","");
                String[] cordsTL = sCordsTL.split(",");
                String[] cordsBR = sCordsBR.split(",");
                System.out.println(sCordsTL);
                System.out.println(sCordsBR);
                PitsTL.add(StringToPos(cordsTL));
                PitsBR.add(StringToPos(cordsBR));
            }
        }catch (SQLException x){
            x.printStackTrace();
        }
    }

    public void setObstacles(Connection connection, int WorldId){
        try(Statement stm = connection.createStatement()){
            String obs = "SELECT * FROM Obstacles\n" +
                           "WHERE WorldId is " + WorldId+"\n" +
                           "AND type is \"obstacle\"";
            resultSet = stm.executeQuery(obs);
            while (resultSet.next()){
                String sCordsTL = resultSet.getString("PositionTL").replaceAll("\\(","").replaceAll("\\)","");
                String sCordsBR = resultSet.getString("PositionBR").replaceAll("\\(","").replaceAll("\\)","");
                String[] cordsTL = sCordsTL.split(",");
                String[] cordsBR = sCordsBR.split(",");
                ObstaclesTL.add(StringToPos(cordsTL));
                ObstaclesBR.add(StringToPos(cordsBR));
            }
        }catch (SQLException x){
            x.printStackTrace();
        }

    }

    public Position StringToPos(String[] cords){
        return new Position(Integer.parseInt(cords[0]),Integer.parseInt(cords[1]));
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
