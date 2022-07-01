package za.co.wethinkcode.robotWorld;

/**
 * Used a singleton to create one world
 * Returns the world
 **/

public class CreateWorld {
    private static World world;

    private CreateWorld() {
    }

    public static World getInstance() {
        if (world == null)
            world = new World(200, 100);
        return world;
    }
}
