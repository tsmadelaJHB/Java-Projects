package za.co.wethinkcode.server.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Obstacle {
    private final int x;
    private final int y;
    private final int size;

    /**
     * Constructor for the Obstacle, x and y are the bottom left coordinate of the
     * obstacle. The width and breath are the size of the world.
     * 
     * @param x
     * @param y
     * @param size
     */
    public Obstacle(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        // generateObstacles();
    }

    public static List<Obstacle> obstacles = new ArrayList<>();

    /**
     * Generates random obstacles.
     * 
     * @return
     */
    public static void generateObstacles() {
        Random random = new Random();

        for (int i = 0; i < 500; i++) {
            int x = random.nextInt(900);
            int y = random.nextInt(550);

            Obstacle obstacle = new Obstacle(x, y, 1);
            obstacles.add(obstacle);
        }
    }

    /**
     * A getter for the x coordinate of the obstacle
     * 
     * @return
     */
    public int getX() {
        return this.x;
    }

    /**
     * A getter for the y coordinate of the obstacle
     * 
     * @return
     */
    public int getY() {
        return this.y;
    }

    /**
     * A getter for the size of the coordinate.
     * 
     * @return
     */
    public int getSize() {
        return this.size;
    }

    /**
     * A getter for the obstacles.
     * 
     * @return
     */
    public List<Obstacle> gObstacles() {
        generateObstacles();
        return obstacles;
    }
}
