package za.co.wethinkcode.model.database;
import za.co.wethinkcode.model.app.CredentialsCheck;
import za.co.wethinkcode.model.app.MissingCheck;
import za.co.wethinkcode.model.app.MissingDetails;
import za.co.wethinkcode.model.app.UserDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JdbcPostgresConnection {

    private static final String MISSING_PERSON = "CREATE TABLE IF NOT EXISTS MISSING_PERSON " +
            "(ID SERIAL PRIMARY KEY,          " +
            "ID_NUMBER       TEXT    NOT NULL," +
            " NAME           TEXT    NOT NULL," +
            " SURNAME        TEXT    NOT NULL," +
            " AGE            INT     NOT NULL," +
            " ADDRESS        CHAR(80),        " +
            " LAST_SEEN      TEXT    NOT NULL," +
            " DETAILS        TEXT    NOT NULL)";

    private static final String USER = "CREATE TABLE IF NOT EXISTS USER_REGISTRATION " +
            "(ID SERIAL PRIMARY KEY,          " +
            " NAME           TEXT    NOT NULL," +
            " LASTNAME       TEXT    NOT NULL," +
            " AGE            INT     NOT NULL," +
            " PASSWORD       TEXT    NOT NULL," +
            " USERNAME       TEXT    NOT NULL," +
            " CONTACT_NUMBER TEXT    NOT NULL," +
            " EMAIL          TEXT    NOT NULL," +
            " ADDRESS        CHAR(80))";

    private static final String USERNAME = "SELECT PASSWORD FROM USER_REGISTRATION WHERE USERNAME = '";
    private static final String IDENTITY = "SELECT ID_NUMBER FROM MISSING_PERSON WHERE ID_NUMBER = '";
    private static final String ALL_MISSING_SELECT = "SELECT * FROM MISSING_PERSON";

    public void runDatabase(){
        Connection conn = null;
        Statement stmt = null;

        try{
            conn = connect();
            stmt = conn.createStatement();
            stmt.executeUpdate(MISSING_PERSON);
            stmt.executeUpdate(USER);
            stmt.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            closeConnection(conn);
        }
        System.out.println("Opened database successfully");
    }

    public void save(UserDetails user){
        Connection conn = null;
        PreparedStatement stmt = null;

        String SQL = "INSERT INTO USER_REGISTRATION (NAME,LASTNAME, AGE, ADDRESS, USERNAME, EMAIL, PASSWORD, CONTACT_NUMBER) VALUES(?,?,?,?,?,?,?,?)";

        try {
            conn = connect();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setInt(3, user.getAge());
            stmt.setString(4, user.getAddress());
            stmt.setString(5, user.getUsername());
            stmt.setString(6, user.getEmail());
            stmt.setString(7, user.getPassword());
            stmt.setString(8, user.getContactNumber());

            stmt.executeUpdate();

        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }

    }

    public CredentialsCheck checkUsername(String username) {
        Connection conn = null;
        Statement stmt = null;
        CredentialsCheck check = new CredentialsCheck();
        try {
            conn = connect();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(USERNAME + username + "'");

            while(rs.next()) {
                check.setPassword(rs.getString("password"));
                check.setUser(true);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return check;
    }

    public void register(MissingDetails missing){
        Connection conn = null;
        PreparedStatement stmt = null;

        String SQL = "INSERT INTO MISSING_PERSON (ID_NUMBER, NAME,SURNAME, AGE, ADDRESS, LAST_SEEN, DETAILS) VALUES(?,?,?,?,?,?,?)";

        try {
            conn = connect();
            stmt = conn.prepareStatement(SQL);
            stmt.setString(1, missing.getId());
            stmt.setString(2, missing.getName());
            stmt.setString(3, missing.getSurname());
            stmt.setInt(4, missing.getAge());
            stmt.setString(5, missing.getAddress());
            stmt.setString(6, missing.getLastSeen());
            stmt.setString(7, missing.getDetails());

            stmt.executeUpdate();

        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            closeConnection(conn);
        }

    }

    public boolean checkMissingPerson(String id){
        Connection conn = null;
        Statement stmt = null;
        boolean verify = false;
        try {
            conn = connect();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(IDENTITY + id + "'");

            while(rs.next()) {
                if (id.equals(rs.getString("id_number")))
                    verify = true;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return verify;
    }

    public List<MissingDetails> getMissing() {
        Connection conn = null;
        Statement stmt = null;
        List<MissingDetails> missingList = new ArrayList<MissingDetails>();
        try {
            conn = connect();
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(ALL_MISSING_SELECT);

            while(rs.next()) {
                MissingDetails missing = new MissingDetails();
                missing.setId(rs.getString("id_number"));
                missing.setName(rs.getString("name"));
                missing.setSurname(rs.getString("surname"));
                missing.setAge(rs.getInt("age"));
                missing.setAddress(rs.getString("address"));
                missing.setLastSeen(rs.getString("last_seen"));
                missing.setDetails(rs.getString("details"));
                missingList.add(missing);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(conn);
        }
        return missingList;
    }

    private Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/missing_db", "tsholofelo", "password@123");
        return conn;
    }

    private void closeConnection(Connection conn) {
        try {
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
