package za.co.wethinkcode.robotWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Maze {
    private List<Obstacle> obstacles = new ArrayList<>();
    private List<Pit> pits = new ArrayList<>();

    public Maze(int xMax, int xMin, int yMax, int yMin) {
        createObstacles(xMax, xMin, yMax, yMin);
        createPits(xMax, xMin, yMax, yMin);
        System.out.println("Loaded Maze.");
    }

    public void createObstacles(int xMax, int xMin, int yMax, int yMin) {
        Random rand = new Random();
        int randomNum = rand.nextInt((10 - 1) + 1) + 1;
        for(int i = 0; i < randomNum; ++i) {
            int xCoor = rand.nextInt((xMax - xMin) + 1) + xMin;
            int yCoor = rand.nextInt((yMax - yMin) + 1) + yMin;

            if(ObstacleOverLap(new Position(xCoor, yCoor))){
                --i;
            } else {
                addObstacles(new Obstacle(xCoor, yCoor));
            }
        }
    }

    public void createPits(int xMax, int xMin, int yMax, int yMin) {
        Random rand = new Random();
        int randomNum = rand.nextInt((10 - 1) + 1) + 1;
        for(int i = 0; i < randomNum; ++i) {
            int xCoor = rand.nextInt((xMax - xMin) + 1) + xMin;
            int yCoor = rand.nextInt((yMax - yMin) + 1) + yMin;

            if(ObstacleOverLap(new Position(xCoor, yCoor))){
                --i;
            } else {
                addPit(new Pit(xCoor, yCoor));
            }
        }
    }

    public boolean ObstacleOverLap(Position bottomLeft) {
        Position topLeft = new Position(bottomLeft.getX(), bottomLeft.getY() + 4);
        Position topRight = new Position(topLeft.getX() + 4, topLeft.getY());
        Position bottomRight = new Position(topRight.getX(), bottomLeft.getY());

        boolean check1 = blocksPath(bottomLeft,topLeft);
        boolean check2 = blocksPath(topLeft, topRight);
        boolean check3 = blocksPath(topRight, bottomRight);
        boolean check4 = blocksPath(bottomRight, bottomLeft);

        return check1 || check2 || check3 || check4;
    }


    private boolean blocksPath(Position a, Position b) {
        for(Obstacle obstacle:this.obstacles){
            if(obstacle.blocksPath(a, b)) {
                return true;
            }
        }
        return false;
    }

    public List<Obstacle> getObstacles() {
        return this.obstacles;
    }

    public List<Pit> getPits() {
        return this.pits;
    }

    public void addObstacles(Obstacle obstacle) {
        this.obstacles.add(obstacle);
    }

    public void addPit(Pit pit) {
        this.pits.add(pit);
    }
}