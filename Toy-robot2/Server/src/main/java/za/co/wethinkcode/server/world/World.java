package za.co.wethinkcode.server.world;


import java.util.ArrayList;
import java.util.List;

public class World {
    // Obstacle obstacle;
    ReadImage imageObstacles = new ReadImage();
    Pit pit;
    int size;


    static List<int[]> obstacleCoordinates = new ArrayList<>();
    static List<int[]> pitCoordinates = new ArrayList<>();

    /**
     * Initially the world is an empty array with null elements. The array can be
     * filled with obstacles objects
     */
    int[][] array;
    List<int[]> myObstacleList = imageObstacles.getObstacles();
    List<int[]> myPitList = imageObstacles.getPits();

    public World(String obstacle, Integer size) {

        this.size = size;
        String[] obs = obstacle.split(",");
        if (!obstacle.equals("")) {
            int xc = Integer.parseInt(obs[0]);
            int yc = Integer.parseInt(obs[1]);
            placeObstacle(xc, yc);
        }


    }

    /**
     * Places the obstacles in the world.
     */
    public void placeObstacle(int xc, int yc) {
        int[] pos = new int[]{xc, yc};
        obstacleCoordinates.add(pos);
    }

    private void setTo(int[] indexes, int value) {
        indexes = new int[]{indexes[0] * 15, indexes[1] * 15};

        for (int y = indexes[0]; y < indexes[0] + 15; y++) {
            setToFor(indexes,value,y);
        }
    }

    /**
     * for loop from setTo
     * @param indexes array
     * @param value integer
     * @param y value
     */
    private void setToFor(int[] indexes,int value, int y){
        for (int x = indexes[1]; x < indexes[1] + 15; x++) {
            if (y < array.length && x < array[0].length) {
                array[y][x] = value;
            }
        }
    }

    /**
     * Places the pits in the world
     */
    public void placePits() {
        for (int[] i : myPitList) {
            continue;
        }
    }

    public void placeMine(int x, int y) {
        int[] pos = new int[]{x, y};
        setTo(pos, 3);
    }

    public boolean isPitOnPath(int x1, int y1, int x2, int y2) {
        return isXOnPath(x1, y1, x2, y2);
    }

    public boolean isObstacleOnPath(int x1, int y1, int x2, int y2) {
        return isXOnPath(x1, y1, x2, y2);
    }

    public boolean isMineOnPath(int x1, int y1, int x2, int y2) {
        return isXOnPath(x1, y1, x2, y2);
    }

    public boolean isOnEdge(int x1, int y1, int x2, int y2) {
        int[] first = new int[]{x1, y1};
        int[] second = new int[]{x2, y2};
        switch (isNegative(xOrY(first, second), first, second)) {
            case "x":{return x2 > size/2;}
            case "-x":{return x2 < -size/2;}
            case "y":{return y2 > size/2;}
            case  "-y":{return  y2 < -size/2;}
        }
        return false;
    }

    /**
     * A function to determine if the movement on the supplied axis is negative or positive i.e. increases the value on
     * that axis or decreases
     * @param axis the axis I am looking at
     * @param first the current co-ordiante of the robot 
     * @param second the new to co-ordinate of the robot
     * @return x -x y -y to highlight which axis the robot is moving on and if it is positive or negative
     */
    private String isNegative(String axis, int[] first, int[] second) {
        if(axis.equals("x") & first[0] < second[0]){
            return "x";
        }
        else if (axis.equals("x") & first[0] > second[0]){
            return "-x";
        }
        else if (axis.equals("y") & first[1] < second[1]){
            return "y";
        }
        else{
            return "-y";
        }
    }

    /**
     * Is X on the path
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public boolean isXOnPath(int x1, int y1, int x2, int y2) {
        int[] first = new int[]{x1, y1};
        int[] second = new int[]{x2, y2};
        //if my current co-ordinate (first) at y is equal to my changed co-ordinate (second) at y

        switch (xOrY(first, second)){
            case "x":{
                if (!caseX(first,second)){
                    return false;
                }
            }
            case"y": {
                if(!caseY(first,second)){
                    return false;
                }
            }
        }

        return false;
    }

    /**
     * case for x
     * @param first
     * @param second
     * @return
     */
    Boolean caseX(int[] first,int[] second){
        //the bigger and smaller number between their x values are
        int biggerX = Math.max(first[0], second[0]);
        int smallerX = Math.min(first[0], second[0]);
        for (int i = smallerX; i <= biggerX; i++) {
            int [] temp = new int[]{i, first[1]};
            if (obstacleCoordinates.contains(temp)) {
                return true;
            }
        }
        return false;
    }

    /**
     * case for y
     * @param first
     * @param second
     * @return
     */
    Boolean caseY(int[] first,int[] second){
        int biggerY = Math.max(first[1], second[1]);
        int smallerY = Math.min(first[1], second[1]);

        for (int i = smallerY; i <= biggerY; i++) {
            int[] temp = new int[]{first[0], i};
            if (obstacleCoordinates.contains(temp)) {
                return true;
            }
        }
        return false;
    }

    private String xOrY(int[] first, int[] second) {
        if (first[1] == second[1]) {
            return "x";
        } else if (first[0] == second[0]) {
            return "y";
        }
        return "a";
    }



    public static List<int[]> getObstacles () {
        return obstacleCoordinates;
    }

    public static List<int[]> getPits () {
        return pitCoordinates;
    }

    public int getSize() { return this.size;
    }
}

