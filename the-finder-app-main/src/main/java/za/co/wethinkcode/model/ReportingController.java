package za.co.wethinkcode.model;

import io.javalin.Context;
import io.javalin.core.util.FileUtil;
import za.co.wethinkcode.model.app.MissingDetails;
import za.co.wethinkcode.model.database.JdbcPostgresConnection;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class ReportingController {

    public static void reportMissingPersons(Context context){

        MissingDetails missing = new MissingDetails();
        missing.setId(context.formParam("id_number"));
        missing.setName(context.formParam("name"));
        missing.setSurname(context.formParam("surname"));
        missing.setAge(Integer.parseInt(Objects.requireNonNull(context.formParam("age"))));
        missing.setAddress(context.formParam("address"));
        missing.setLastSeen(context.formParam("last_seen"));
        missing.setDetails(context.formParam("details"));
        context.uploadedFiles("fileUpload").forEach(file -> {
            FileUtil.streamToFile(file.getContent(), "images/" + "test.png");
        });
        JdbcPostgresConnection database = new JdbcPostgresConnection();
        database.register(missing);
        context.redirect("/home.html");
    }


    public static void viewMissingPersons(Context context){
        JdbcPostgresConnection jdbc = new JdbcPostgresConnection();
        List<MissingDetails> missing =  jdbc.getMissing();
        context.json(missing);
    }
}

