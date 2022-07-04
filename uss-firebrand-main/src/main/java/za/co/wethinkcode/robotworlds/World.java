//package za.co.wethinkcode.robotworlds;
//
//import za.co.wethinkcode.robotworlds.world.Obstacle;
//import za.co.wethinkcode.robotworlds.world.Pits;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class World {
//    Direction currentDirection;
//    Position position;
//    WorldConfig properties = new WorldConfig();
//    int width;
//    int height;
//    int halfWidth = width / 2;
//    int halfHeight = height / 2;
//
//    private final Position TOP_LEFT = new Position(-halfWidth,halfHeight);
//    private final Position BOTTOM_RIGHT = new Position(halfWidth,-halfHeight);
//    private final Position CENTRE = new Position(0,0);
//    private final List<Obstacle> obstacleList;
//    private final List<Pits> pitList;
//
//     public World() {
//         this.width = properties.getWorldWidth();
//         this.height = properties.getWorldHeight();
//         this.currentDirection = Direction.NORTH;
//         this.position = CENTRE;
//         this.obstacleList = new ArrayList<Obstacle>(); // Set it to an empty list so it's not null
//         this.pitList = new ArrayList<Pits>();
//         createObstacles(); // The world has obstacles in it
//         createPits(); // The world has pits in it
//    }
//
//    /** The world's directions. North points to the top edge of the world.
//     */
//    public enum Direction {
//        NORTH,
//        EAST,
//        SOUTH,
//        WEST
//    }
//
//    public Direction getCurrentDirection() {
//        return this.currentDirection;
//    }
//
//    public void setCurrentDirection(Direction currentDirection) {
//        this.currentDirection = currentDirection;
//    }
//
//    public Position[] PositionGenerator(){
//        Random random = new Random();
//        int X = random.ints(-100,100).findFirst().getAsInt();
//        int Y = random.ints(-100,100).findFirst().getAsInt();
//        int xRange = random.nextInt(10);
//        int yRange = random.nextInt(10);
//        Position TopLeft = new Position(X,Y);
//        Position BottomRight = new Position(X+xRange,Y-yRange);
//        return new Position[]{TopLeft,BottomRight};
//    }
//
//    public void createObstacles(){
//        Position BottomRight , TopLeft;
//        for (int i = 0; i < 34; i++) {
//            Position[] cords = PositionGenerator();
//            BottomRight = cords[0];
//            TopLeft = cords[1];
//            Obstacle obstacle = new Obstacle(TopLeft, BottomRight);
//            obstacleList.add(obstacle);
//        }
//    }
//
//    /** The number of pits is decided here and based on that, the pits' X and Y coordinates are generated. The pits are
//     * then added to the pit list, to be printed out later.
//     */
//    public void createPits() {
//        Random random = new Random();
//        int numberOfPits = 2;
//        Pits pit;
//        int pitY;
//        int pitX;
//
//        for (int j = 0; j <= numberOfPits; j++) {
//            pitY = random.nextInt(width) - width/2;
//            pitX = random.nextInt(height) - height/2;
//            pit = new Pits(pitX, pitY);
//            pitList.add(pit);
//        }
//    }
//}
