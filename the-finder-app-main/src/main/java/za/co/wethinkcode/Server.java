package za.co.wethinkcode;

import io.javalin.Javalin;
import org.jetbrains.annotations.NotNull;
import za.co.wethinkcode.model.*;
import za.co.wethinkcode.model.database.JdbcPostgresConnection;

public class Server {
    private static final int DEFAULT_PORT = 9000;
    private static Javalin server;
    public static Javalin start(int port) {

        if (port == 0) {
            server = Javalin.create().enableStaticFiles("templates")
                    .start(DEFAULT_PORT);
        } else {
            server = Javalin.create().enableStaticFiles("templates")
                    .start(port);
        }
        return server;
    }

    public static void setupRoutes(@NotNull Javalin server) {
        server.routes(() -> {
            server.get("/login-page", LogInController::handleLogin);
            server.post("/login", LogInController::checkLogin);
            server.get("/logout-page", LogInController::handleLogout);
            server.post("/register", UserRegistrationDataController::signUpHandler);
            server.post("/report-page", ReportingController::reportMissingPersons);
            server.get("/view_data", ReportingController::viewMissingPersons);
            server.post("/update-missing", UpdateMissingPersons::foundPersons);
            server.post("/", LogInController::index);
                });
    }

    public static void main(String... opts){
        Javalin server = start(0);
        setupRoutes(server);
        new JdbcPostgresConnection().runDatabase();
    }
}
