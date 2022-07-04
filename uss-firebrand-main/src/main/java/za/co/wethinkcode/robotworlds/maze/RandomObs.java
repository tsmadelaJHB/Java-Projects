package za.co.wethinkcode.robotworlds.maze;

import za.co.wethinkcode.robotworlds.Position;
import za.co.wethinkcode.robotworlds.world.Obstacle;
import za.co.wethinkcode.robotworlds.world.Pits;

import java.util.Random;

public class RandomObs extends AbstractMaze{

    int limit;

    public RandomObs(int ceiling) {
        setLimit(ceiling);
        //adding a buildObs here is ideal for adding obstacles
//        buildObs();
//        buildPits();
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Position[] PositionGenerator(int limit){
        int xRange;
        int yRange;
        Random random = new Random();
        int X = random.ints(-(limit/2),limit/2).findFirst().getAsInt();
        int Y = random.ints(-(limit/2),limit/2).findFirst().getAsInt();
        if (limit >= 10) {
             xRange = random.nextInt(5);
             yRange = random.nextInt(5);
        }else  {
             xRange = 0;
             yRange = 0;
        }
        Position TopLeft = new Position(X,Y);
        Position BottomRight = new Position(X+xRange,Y-yRange);
        return new Position[]{TopLeft,BottomRight};
    }

    /** Generates a random number between a certain range
     * @return the random int
     */
    public int RandomGenerator(int upper, int lower, int max){
        Random gen = new Random();
        return gen.nextInt(upper - (lower)) - max;
    }

    /** Builds random Obstacle for the robot to avoid
     */
//    public void buildObs(){
//        int bottomLeftX, bottomLeftY;
//        for (int i = 0; i < 8; i++) {
//            bottomLeftX = RandomGenerator(80, -95, 95);
//            bottomLeftY = RandomGenerator(180, -195, 195);
//            if(getObstacles().contains(new Position(bottomLeftX,bottomLeftY))
//                    || getObstacles().contains(new Position(bottomLeftX+1,bottomLeftY+1))
//                    || getObstacles().contains(new Position(bottomLeftX+2,bottomLeftY+2))
//                    || getObstacles().contains(new Position(bottomLeftX+3,bottomLeftY+3))
//                    || getObstacles().contains(new Position(bottomLeftX+4,bottomLeftY+4))){
//                continue;
//            }
//            if(bottomLeftX != 0 && bottomLeftY != 0)
//                MakeObsList(new Obstacle(bottomLeftX, bottomLeftY));
//        }
//    }


    public void buildObs(){
        Position BottomRight , TopLeft;
        Position[] cords = PositionGenerator(limit);
        BottomRight = cords[1];
        TopLeft = cords[0];
        Obstacle obstacle = new Obstacle(TopLeft,BottomRight);
        MakeObsList(obstacle);
    }

    public void buildPits(){
        Position BottomRight , TopLeft;
        Position[] cords = PositionGenerator(limit);
        BottomRight = cords[1];
        TopLeft = cords[0];
        Pits pit = new Pits(TopLeft,BottomRight);
        MakePitsList(pit);
        int bottomLeftX, bottomLeftY;
//        for (int i = 0; i < 4; i++) {
//            bottomLeftX = RandomGenerator(40, -55, 55);
//            bottomLeftY = RandomGenerator(140, -155, 155);
//            if(bottomLeftX != 0 && bottomLeftY != 0 && bottomLeftY!= -49 && bottomLeftX !=-49)
//                MakePitsList(new Pits(bottomLeftX, bottomLeftY));
//        }
    }

    public void setObs(){

    }
}

