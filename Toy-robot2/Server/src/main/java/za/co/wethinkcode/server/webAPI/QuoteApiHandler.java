package za.co.wethinkcode.server.webAPI;

import io.javalin.http.Context;
import io.javalin.http.HttpCode;
import io.javalin.http.NotFoundResponse;
import org.apache.commons.cli.ParseException;
import za.co.wethinkcode.server.database.WorldTable;
import za.co.wethinkcode.server.database.robotWorldDatabaseDAO;

import java.sql.*;
import java.util.Scanner;

public class QuoteApiHandler {
    private static QuoteDB database = new TestDatabase();

    public static void getAll(Context context) {
        context.json(database.all());
    }


//    public static void getAll(Context context) throws SQLException {
//        String dbUrl = "jdbc:sqlite:robotWorldDatabase";
//        String id = null;
//        String WorldName = null;
//        String WorldSize = null;
//        String Obj_id = null;
//        String x_posi = null;
//        String y_posi = null ;
//        String ObjectName=null;
//
//        final Connection connection = DriverManager.getConnection( dbUrl );
//        //get the world in world and in object
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("input name of the world");
//        String name = scanner.nextLine().trim();
//        try (final Statement stmt = connection.createStatement()) {
//             if (name.equals("")) {
//                 boolean gotAResultSet = stmt.execute("SELECT * FROM world,object ORDER BY id DESC LIMIT 1");
//
//            }
//             else {
//            String where = "'"+name+"'";
//            boolean gotAResultSet = stmt.execute(
//                    "SELECT *,object_id,x_position,y_position FROM world,object WHERE name="+where+" AND" +
//                            " world_name="+where
//            );}
//
//            //display the world nicely
//            try (ResultSet results = stmt.getResultSet()) {
//                String b = "";
//                System.out.println("id\tname\tsize\tobj_ID\tx\ty\tObjectName");
//                while (results.next()) {
//
//                    id = results.getString("id");
//                    WorldName = results.getString("name");
//                    WorldSize = results.getString("size");
//                    Obj_id = results.getString("object_id");
//                    x_posi = results.getString("x_position");
//                    y_posi = results.getString("y_position");
//
//                    ObjectName=getObjectName(Integer.parseInt(Obj_id));
//
//                    b= id + "\t" +
//                            WorldName + "\t\t" +
//                            WorldSize + "\t\t" +
//                            Obj_id +"\t"+
//                            x_posi+"\t"+
//                            y_posi+"\t"+
//                            ObjectName
//                    ;
//                    System.out.println(b);
//
//                }
//                System.out.println("Successfully retrieving the world test3 from World table!");
//                if(b.equals("")){
//                    System.out.println("World test3 does not exists in the world");
//
//                }
//            } catch (SQLException e) {
//
//                System.err.println(e.getMessage());
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        context.json("{" +
//                "  \"id\": \"" + id + "\"," +
//                "  \"robot Name\": \"" + WorldName + "\"," +
//                "  \"World size\": \"" + WorldSize  + "\"," +
//                "  \"Object Id\": \"" + Obj_id  + "\"," +
//                "  \"Object Name\": \"" + ObjectName  + "\"," +
//                "  \"Position x\": \"" + x_posi  + "\"," +
//                "  \"Position y\": \"" + y_posi  + "\"," +
//                "}");
//    }
//    /**
//     * get the name of the object
//     * @param id for object type id
//     * @return the name of the object
//     * @throws SQLException sql exception
//     */
//    public static String getObjectName(int id)  throws SQLException {
//        String dbUrl = "jdbc:sqlite:robotWorldDatabase";
//        String ObjectName = "";
//
//        final Connection connection = DriverManager.getConnection(dbUrl);
//        //get the world in world and in object
//        try (final Statement stmt = connection.createStatement()) {
//            boolean gotAResultSet = stmt.execute(
//                    "SELECT type FROM object_type WHERE object_id=" +id
//            );
//            try (ResultSet results = stmt.getResultSet()) {
//                while (results.next()) {
//                    ObjectName = results.getString("type");
//                }
//
//            } catch (SQLException e) {
//                System.err.println(e.getMessage());
//            }
//        }
//        return ObjectName;
//    }

    public static void getOne(Context context) {
//        Integer id = context.pathParamAsClass("id", Integer.class).get();
        Integer id = 1;
        Quote quote = database.get(id);
        if (quote == null) {
            throw new NotFoundResponse("Quote not found: " + id);
        }
        context.json(quote);

    }

    public static void create(Context context) throws ParseException {
        Quote quote = context.bodyAsClass(Quote.class);
        Quote newQuote = database.add(quote);
        context.header("Location", "/quote/" + newQuote.getId());
        context.status(HttpCode.CREATED);
        context.json(newQuote);

    }
    public static void createAdmin(Context context) throws ParseException {
        Quote quote = context.bodyAsClass(Quote.class);
        Quote newQuote = database.add(quote);
        context.header("Location", "/world/" + newQuote.getId());
        context.status(HttpCode.CREATED);
        context.json(newQuote);
    }
    public static void getWorld(Context context) throws ParseException {
        Quote quote = context.bodyAsClass(Quote.class);
        Quote newQuote = database.add(quote);
        context.header("Location", "/myworlds/" + newQuote.getId());
        context.status(HttpCode.CREATED);
        context.json(newQuote);
    }
}
