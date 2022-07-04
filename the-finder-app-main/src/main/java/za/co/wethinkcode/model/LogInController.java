package za.co.wethinkcode.model;

import io.javalin.Context;
import za.co.wethinkcode.model.app.CredentialsCheck;
import za.co.wethinkcode.model.database.JdbcPostgresConnection;


public class LogInController {

    public static void index(Context context) {
        context.redirect("/index.html");
    }

    public static void handleLogin(Context context){
        String username = context.formParam("username");

        if (username == null) {
            context.redirect("/index.html");
        } else{
        }
        context.redirect("index.html");

    }

    public static void handleLogout(Context context){
        context.redirect("index.html");

    }

    public static void checkLogin(Context context){
        String username = context.formParam("username");
        String password = context.formParam("password");
        if (username != null && password != null) {
            JdbcPostgresConnection database = new JdbcPostgresConnection();
            CredentialsCheck check = database.checkUsername(username);
            if(password.equals(check.getPassword())) {
                context.redirect("/home.html");
            } else {
                context.redirect("/index.html?error=true");
            }
        } else{
            context.redirect("index.html?error=true");
        }
    }
}
