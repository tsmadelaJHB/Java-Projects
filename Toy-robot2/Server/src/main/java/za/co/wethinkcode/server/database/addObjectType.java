package za.co.wethinkcode.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class addObjectType {

    /**
     * Connects to the database
     * Once connected it will call readData
     */

    private addObjectType(String object) {
        String dbUrl = "jdbc:sqlite:robotWorldDatabase";
        try(final Connection connection = DriverManager.getConnection(dbUrl) ){
            System.out.println( "Connected to database " );
            createData(connection, object);
        }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
    }

    /**
     * createData will pass the name of the object it will add into objects_type
     * @param object String name of object
     * @return True if the object type was added false if not
     */

    private Boolean createData( final Connection connection, String object )
            throws SQLException
    {
        try( final Statement stmt = connection.createStatement() ){
            boolean gotAResultSet = stmt.execute(
                    "INSERT INTO objects_type VALUES (\"" + object + "\")" );
            if( gotAResultSet ){
                throw new RuntimeException( "Unexpectedly got a SQL resultset." );
            }else{
                final int updateCount = stmt.getUpdateCount();
                if( updateCount == 1 ){
                    System.out.println( "1 row INSERTED into Object type." );
                }else{
                    throw new RuntimeException( "Expected 1 row to be inserted, but got " + updateCount );
                }
            }
        }catch (RuntimeException e){
            return false;
        }
        return true;
    }
}