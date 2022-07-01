package za.co.wethinkcode.server.database;

public class Object {
    private final int x;
    private final int y;
    private final int size;
    private final String type;

    public Object(int x, int y, int size, String type) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.type = type;
    }

    /**
     * A getter for the x coordinate of the object
     *
     * @return the x coordinate
     */
    public int getX() {
        return this.x;
    }

    /**
     * A getter for the y coordinate of the object
     *
     * @return the object y coordinate
     */
    public int getY() {
        return this.y;
    }

    /**
     * A getter for the size of the object
     *
     * @return the object size
     */
    public int getSize(){return  this.size;}

    /**
     * A getter for the object type
     * @return the object type e.g: Pit, Obstacle, Mine
     */
    public String getType() {return  this.type;}


}


