package za.co.wethinkcode.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class removeObjectType {

    /**
     * Connects to the database
     * Once connected it will call readData
     */

    private removeObjectType(String object) {
        String dbUrl = "jdbc:sqlite:robotWorldDatabase";
        try(final Connection connection = DriverManager.getConnection(dbUrl) ){
            System.out.println( "Connected to database " );
            deleteData(connection, object);
        }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
    }

    /**
     * deleteData will pass the name of the object it will remove from objects_type
     * @param object String name of object
     * @return True if the object type was removed false if not
     */

    private Boolean deleteData( final Connection connection, String object )
            throws SQLException
    {
        try( final Statement stmt = connection.createStatement() ){
            boolean gotAResultSet = stmt.execute(
                    "DELETE FROM objects_type WHERE " + object );
            if( gotAResultSet ){
                throw new RuntimeException( "Unexpectedly got a SQL resultset." );
            }else{
                final int updateCount = stmt.getUpdateCount();
                if( updateCount == 1 ){
                    System.out.println( "1 row REMOVED into Object type." );
                }else{
                    throw new RuntimeException( "Expected 1 row to be removed, but got " + updateCount );
                }
            }
        }catch (RuntimeException e){
            return false;
        }
        return true;
    }
}
