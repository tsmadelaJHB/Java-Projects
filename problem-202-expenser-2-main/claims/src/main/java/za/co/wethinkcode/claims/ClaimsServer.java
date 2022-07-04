package za.co.wethinkcode.claims;

import io.javalin.Javalin;
import org.jetbrains.annotations.NotNull;
import za.co.wethinkcode.claims.app.Sessions;
import static io.javalin.apibuilder.ApiBuilder.*;

public class ClaimsServer {

    private static final int DEFAULT_PORT = 8083;
    private static final String STATIC_DIR = "/html";
    private final Javalin app;

    public ClaimsServer() {
        app = createAndConfigureServer();
        setupRoutes(app);
    }
    /**
     * The main class starts the server on the default port 7070.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        ClaimsServer server = new ClaimsServer();
        server.start(DEFAULT_PORT);
    }


    public void start(int port) {
        app.start(port);
    }

    public int port() {
        return app.port();
    }

    public void close() {
        app.close();
    }

    private void setupRoutes(Javalin server) {
        server.routes(() -> {
            claimRoutes();
            settlementRoutes();
        });
    }

    private static void claimRoutes() {
        get(ClaimsApiController.CLAIM_PATH, ClaimsApiController::getClaim);
        post(ClaimsApiController.CLAIM, ClaimsApiController::create);
        put(ClaimsApiController.CLAIM, ClaimsApiController::update);
        get(ClaimsApiController.CLAIM_BY_PATH, ClaimsApiController::claimsBy);
        get(ClaimsApiController.CLAIM_FROM_PATH, ClaimsApiController::claimsFrom);
        get(ClaimsApiController.TOTAL_CLAIM_BY_PATH, ClaimsApiController::totalClaimsBy);
        get(ClaimsApiController.TOTAL_CLAIM_FROM_PATH, ClaimsApiController::totalClaimsFrom);
    }
    private static void settlementRoutes() {
        post(SettlementApiController.SETTLE_CLAIM_PATH, SettlementApiController::submitSettlement);
    }



    @NotNull
    private Javalin createAndConfigureServer() {
        return Javalin.create(config -> {
            config.sessionHandler(Sessions::nopersistSessionHandler);
        });
    }
}
