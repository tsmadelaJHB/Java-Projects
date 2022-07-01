package za.co.wethinkcode.server.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pit {
    private final int x;
    private final int y;
    private final int size;

    /**
     * A constructor for a pit.
     * 
     * @param x
     * @param y
     * @param size
     */
    public Pit(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public static List<Pit> pits = new ArrayList<>();

    public static Pit generatePits() {
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(900);
            int y = random.nextInt(550);

            Pit pit = new Pit(x, y, 1);
            pits.add(pit);
        }
        return null;
    }

    /**
     * A getter for the x coordinate of the pit
     * 
     * @return
     */
    public int getX() {
        return this.x;
    }

    /**
     * A getter for the y coordinate of the pit
     * 
     * @return
     */
    public int getY() {
        return this.y;
    }

    /**
     * A getter for the size of the pit.
     * 
     * @return
     */
    public int getSize() {
        return this.size;
    }

    /**
     * A getter for the list of pits
     * 
     * @return pits
     */
    public List<Pit> gPits() {
        generatePits();
        return pits;
    }
}
