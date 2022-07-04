package Handlers;

import io.javalin.http.Context;
import dataStore.db.DataRepository;
import dataStore.model.Person;
import Handlers.NettExpenseHandler;

public class LoginHandler {
    public static final String LOGIN_PATH = "/login";
    public static final String LOGOUT_PATH = "/logout";
    public static final String ROOT_PATH = "/index.html";

    public static void handleLogin(Context context) {
        String username = context.formParam("email");
        if (username == null) {
            context.redirect(ROOT_PATH);
            return;
        }

        final Person person = DataRepository.getInstance().addPerson(new Person(username));
        context.sessionAttribute("user", person);

        context.redirect(NettExpenseHandler.PATH);
    }

    public static void handleLogout(Context context) {
        context.sessionAttribute("user", null);
        context.redirect(ROOT_PATH);
    }
}