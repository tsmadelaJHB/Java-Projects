package za.co.wethinkcode.model;


import io.javalin.Context;
import org.eclipse.jetty.server.Authentication;
import org.jetbrains.annotations.NotNull;
import za.co.wethinkcode.model.app.UserDetails;
import za.co.wethinkcode.model.database.JdbcPostgresConnection;

import java.util.Objects;

public class UserRegistrationDataController {

    public static void signUpHandler(@NotNull Context context){
        UserDetails user = new UserDetails();
        user.setUsername(context.formParam("username"));
        user.setFirstName(context.formParam("name"));
        user.setLastName(context.formParam("lastname"));
        user.setEmail(context.formParam("email"));
        user.setPassword(context.formParam("password"));
        user.setAge(Integer.parseInt(Objects.requireNonNull(context.formParam("age"))));
        user.setAddress(context.formParam("address"));
        user.setContactNumber(context.formParam("contacts"));


        JdbcPostgresConnection database = new JdbcPostgresConnection();
        database.save(user);
        context.redirect("/login-page");
    }
}
