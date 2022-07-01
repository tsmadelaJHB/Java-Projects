package database;//package database;
//
//public class createDB {
//}

//package za.co.wethinkcode.dbtest;

import java.sql.*;

/**
 * DbTest is a small command-line tool used to check that we can connect to a SQLite database.
 *
 * By default (without any command-line arguments) it attempts to create a SQLite table in an in-memory database.
 * If it succeeds, we assume that all the working parts we need to use SQLite databases are in place and working.
 *
 * The only command-line argument this app understands is
 *  `-f <filename>`
 *  which tells that application to create the test table in a real (disk-resident) database named by the given
 *  filename. Note that the application _does not delete_ the named file, but leaves it in the filesystem
 *  for later examination if desired.
 */
class createDB
{
    public static final String IN_MEMORY_DB_URL = "jdbc:sqlite::memory:";

    public static final String DISK_DB_URL = "jdbc:sqlite:";

    public static void main( String[] args ) {
        final createDB tester = new createDB( args );
    }

    private String dbUrl = "jdbc:sqlite:iteration.db";

    private createDB( String[] args ) {
//        processCmdLineArgs( args );

        try( final Connection connection = DriverManager.getConnection( dbUrl ) ){
            System.out.println( "Connected to database " );
            runTest( connection );
//            createData();
        }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
    }

    private void runTest( Connection connection ) {
        try( final Statement stmt = connection.createStatement() ){
            stmt.executeUpdate( "CREATE TABLE IF NOT EXISTS books( test_id integer PRIMARY KEY," +
                    " Author NOT NULL," +
                    " text NOT NULL)" );
            System.out.println( "Success creating test table!" );
        }catch( SQLException e ){
            System.err.println( e.getMessage() );
        }
    }

    private void processCmdLineArgs( String[] args ){
        if( args.length == 2 && args[ 0 ].equals( "-f" )){
            dbUrl = DISK_DB_URL + args[ 1 ];
        }else if( args.length == 0 ){
            dbUrl = IN_MEMORY_DB_URL;
        }else{
            throw new RuntimeException( "Illegal command-line arguments." );
        }
    }
    public static Boolean createData(String name, String text)
            throws SQLException
    {
        String dbUrl = "jdbc:sqlite:test.db";
//
        Connection connection = DriverManager.getConnection( dbUrl );
        final Statement stmt = connection.createStatement();
        try  {
            String value = "('" + name + "'," + text + ")";
            String inserting = "INSERT INTO books(Author,text) VALUES " + value;
            boolean addAResultSet = stmt.execute(
                    inserting
            );
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
        System.out.println("Text is stored.");
        return true;
    }
}