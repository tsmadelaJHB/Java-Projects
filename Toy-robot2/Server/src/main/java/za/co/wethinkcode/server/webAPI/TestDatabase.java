package za.co.wethinkcode.server.webAPI;

import com.fasterxml.jackson.databind.JsonNode;
import io.javalin.http.Context;
import org.apache.commons.cli.ParseException;
import za.co.wethinkcode.server.RobotWorldClient;
import za.co.wethinkcode.server.RobotWorldJsonClient;
import za.co.wethinkcode.server.ServerHandler;
import za.co.wethinkcode.server.database.robotWorldDatabaseDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestDatabase extends QuoteDB {
    private Map<Integer, Quote> quotes;
    int count=0;
    int mycount=0;

    public TestDatabase() {
        quotes =  new HashMap<>();
        if(mycount==0) {
            this.addd(Quote.create("LAUNCHED DEFAULT WORLD ", "2"));
            mycount+=1;
        }
    }

    @Override
    public Quote get(Integer id) {
        return quotes.get(id);
    }

    @Override
    public List<Quote> all() {
        return new ArrayList<>(quotes.values());
    }

    @Override
    public Quote add(Quote quote) throws ParseException {


        if(quote.getName().equalsIgnoreCase("admin")){
            System.out.println("Welcome Professor");
            executeAdmin(quote);
        }
        else if (quote.getName().equalsIgnoreCase("obstacles")){
            addingObstacles(quote);
        }
        else{
            if(quote.getText().contains("default")){
               try {
                   ServerHandler.main(new String[]{"-s2","-o1,1"});
               } catch (ParseException e) {
                   e.printStackTrace();
               }
            }
            else{
                executeCommands(quote);
                count+=1;
            }
        }
        if(count<1 || quote.getName().equalsIgnoreCase("admin"))
        {Integer index = quotes.size() + 1;
        quote.setId(index);
        quotes.put(index, quote);
        }


        return quote;
    }

    public void addingObstacles(Quote quote){
        String[] separate = quote.getText().split(" ");
        System.out.println("separation length "+separate.length);
        String WorldName = separate[0];

        for (int i=1;i<separate.length;i++) {
            String obstacle = separate[i];
            String[] coord = obstacle.split(",");
            String x = coord[0];
            String y = coord[1];

            try {
                robotWorldDatabaseDAO.addObject("Obstacle", Integer.parseInt(x), Integer.parseInt(y), WorldName);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Admin play groung
     * @param quote my quotes
     * @throws ParseException exception for serverhandler
     */
    public void executeAdmin(Quote quote) throws ParseException {

        String obstacle="";
        String x ="";
        String y = "";
        
        String[] separate = quote.getText().split(" ");
        String WorldName = separate[0];
        String size = separate[1];
        if(separate.length==3){
            if(!separate[2].equals(""))
            {
                obstacle = separate[2];

                String[] coord = obstacle.split(",");
                x = coord[0];
                y = coord[1];
            }
        }

        try {
            if(!obstacle.equals(""))
                robotWorldDatabaseDAO.addObject("Obstacle",Integer.parseInt(x),Integer.parseInt(y),WorldName);
            robotWorldDatabaseDAO.addDBWorld(WorldName, Integer.parseInt(size));
            ServerHandler.main(new String[]{"-s"+size});

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * executing the commands from the app
     * @param quote all the quotes from the app
     */
    public void executeCommands(Quote quote) {
        Context context = null;
        int DEFAULT_PORT = 5000;
        String DEFAULT_IP = "localhost";
        RobotWorldClient serverClient = new RobotWorldJsonClient();

        serverClient.connect(DEFAULT_IP, DEFAULT_PORT);
        String robot1 = "{" +
                "  \"robot\": \"" + quote.getName() + "\"," +
                "  \"command\": \"laun\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";

        if(quote.getText().equalsIgnoreCase("launch")){
            robot1 = "{" +
                    "  \"robot\": \"" + quote.getName() + "\"," +
                    "  \"command\": \"" + quote.getText() + "\"," +
                    "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                    "}";
            //To be used later
            try  {
                String dbUrl = "jdbc:sqlite:robotWorldDatabase";

                Connection connection = DriverManager.getConnection( dbUrl );
                final Statement stmt = connection.createStatement();

                String value = "('" + quote.getName() + "')";
                String inserting = "INSERT INTO \"robots\"(Rname) VALUES " + value;
                boolean addAResultSet = stmt.execute(
                        inserting
                );
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(quote.getText().equalsIgnoreCase("close")){
            robot1 = "{" +
                    "  \"robot\": \"" + quote.getName() + "\"," +
                    "  \"command\": \"" + quote.getText() + "\"," +
                    "  \"arguments\": [\"\"]" +
                    "}";
        }
        if(quote.getText().equalsIgnoreCase("fire")){
            robot1 = "{" +
                    "  \"robot\": \"" + quote.getName() + "\"," +
                    "  \"command\": \"" + quote.getText() + "\"," +
                    "  \"arguments\": [\"\"]" +
                    "}";
        }
        if(quote.getText().equalsIgnoreCase("mine")){
            robot1 = "{" +
                    "  \"robot\": \"" + quote.getName() + "\"," +
                    "  \"command\": \"" + quote.getText() + "\"," +
                    "  \"arguments\": [\"\"]" +
                    "}";
        }
        if(quote.getText().equalsIgnoreCase("reload")){
            robot1 = "{" +
                    "  \"robot\": \"" + quote.getName() + "\"," +
                    "  \"command\": \"" + quote.getText() + "\"," +
                    "  \"arguments\": [\"\"]" +
                    "}";
        }
        if(quote.getText().equalsIgnoreCase("forward")){
            robot1 = "{" +
                    "  \"robot\": \"" + quote.getName() + "\"," +
                    "  \"command\": \"" + quote.getText() + "\"," +
                    "  \"arguments\": [\"1\"]" +
                    "}";
        }
        if(quote.getText().equalsIgnoreCase("back")){
            robot1 = "{" +
                    "  \"robot\": \"" + quote.getName() + "\"," +
                    "  \"command\": \"" + quote.getText() + "\"," +
                    "  \"arguments\": [\"1\"]" +
                    "}";
        }
        if(quote.getText().equalsIgnoreCase("left")){
            robot1 = "{" +
                    "  \"robot\": \"" + quote.getName() + "\"," +
                    "  \"command\": \"turn\"," +
                    "  \"arguments\": [\""+ quote.getText()+"\"]" +
                    "}";
        }
        if(quote.getText().equalsIgnoreCase("right")){
            robot1 = "{" +
                    "  \"robot\": \"" + quote.getName() + "\"," +
                    "  \"command\": \"turn\"," +
                    "  \"arguments\": [\""+ quote.getText()+"\"]" +
                    "}";
        }
        if(quote.getText().equalsIgnoreCase("Look")){
            robot1 = "{" +
                    "  \"robot\": \"" + quote.getName() + "\"," +
                    "  \"command\": \"" + quote.getText() + "\"," +
                    "  \"arguments\": [\"\"]" +
                    "}";
        }
        if(quote.getText().equalsIgnoreCase("State")){
            robot1 = "{" +
                    "  \"robot\": \"" + quote.getName() + "\"," +
                    "  \"command\": \"" + quote.getText() + "\"," +
                    "  \"arguments\": [\"\"]" +
                    "}";
        }
        if(quote.getText().equalsIgnoreCase("reload")){
            robot1 = "{" +
                    "  \"robot\": \"" + quote.getName() + "\"," +
                    "  \"command\": \"" + quote.getText() + "\"," +
                    "  \"arguments\": [\"\"]" +
                    "}";
        }
        if(quote.getText().equalsIgnoreCase("repair")){
            robot1 = "{" +
                    "  \"robot\": \"" + quote.getName() + "\"," +
                    "  \"command\": \"" + quote.getText() + "\"," +
                    "  \"arguments\": [\"\"]" +
                    "}";
        }

        JsonNode response1 = serverClient.sendRequest(robot1);

        this.addd(Quote.create(quote.getName()+" request "+quote.getText()+" >>> "+response1.toString(), quote.getName()));
    }
    public Quote addd(Quote quote){
        Integer index = quotes.size() + 1;
        quote.setId(index);
        quotes.put(index, quote);
        return quote;
    }
 }
