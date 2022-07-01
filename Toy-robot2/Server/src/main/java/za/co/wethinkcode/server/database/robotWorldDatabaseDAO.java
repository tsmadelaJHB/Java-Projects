package za.co.wethinkcode.server.database;

import za.co.wethinkcode.server.world.World;

import java.sql.SQLException;
import java.util.List;

public interface robotWorldDatabaseDAO {
    //Jonas

    /**
     * Returns a world stored in the database as the new world
     *
     * @param name the name of the world to retrieve from the database
     * @return a new world object to be used with data from
     */
//    World getDBWorld(String name);
    static void addObject(String object, int x, int y, String name) throws SQLException {
        ObjectTable.addObject(object,x,y,name);
    }

    /**
     * Adds the current world to database
     *
     * @param name the current world
     * @return True if the world was added or false if it was not
     */

//    Boolean addDBWorld(World world);
    static Boolean addDBWorld(String name, int size) throws SQLException {
        WorldTable.addDBWorld(name,size);
        return null;
    }
    /**
     * Adds the current world to database
     * @param world the current world
     * @return True if the world was added or false if it was not
     */

    /**
     * Removes a world from the database
     * @param name name of the world to be removed
     * @return True if the world was removed
     */
    Boolean removeDBWorld(String name);

    //Paulo
    /**
     * Returns a list of all the current object_types stored in the database
     * @return a list of all the object types as string names
     */
    List<String> getObjectType();

    /**
     * Adds a new object type to the database
     * @param object the name of the new object type
     * @return True if the object type was add false if not
     */
    Boolean addObjectType(String object);

    /**
     * Removes an object type from the database
     * @param object the name of the new object type
     * @return True if the object type was add false if not
     */
    Boolean removeObjectType(String object);

    //Bongiwe
    /**
     * Add a new object to the database
     * @param name the object to be added
     * @return  True if the object was added false if not
     */
//    Boolean addDBObject(Object object);
    static void getDBWorld(String name) throws SQLException {
        WorldTable.getDBWorld(name);
    }

    /**
     * Returns a list of objects for the current world
     * @param name name of a valid world
     * @return a lits of objects for a named world
     */
    List<Object> getDBObject(String name);

    /**
     * Remove an object from the database
     * @param object the object to be removed
     * @return  True if the object was removed false if not
     */
    Boolean removeDBObject(Object object);

}
