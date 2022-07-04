package za.co.wethinkcode.robotworlds.webapi.models;

/***
 * Data access object class, matches all the fields in RDB database's World table
 *
 * @author              fkalombo
 * @iteration           4
 * @since               2021-10-21
 */
public class World {
    private String WorldName;
    private Integer WorldSize;

    /***
     * Creates a new world (for World table) and assigns values to the parameters
     * and returns that world object
     *
     * @param _worldName
     * @param _worldSize
     * @return world object
     */
    public static World create(String _worldName, Integer _worldSize) {
        World world = new World();
        world.setWorldName(_worldName);
        world.setWorldSize(_worldSize);
        return world;
    }

    public String getWorldName() {
        return WorldName;
    }

    public void setWorldName(String worldName) {
        WorldName = worldName;
    }

    public Integer getWorldSize() {
        return WorldSize;
    }

    public void setWorldSize(Integer worldSize) {
        WorldSize = worldSize;
    }
}
