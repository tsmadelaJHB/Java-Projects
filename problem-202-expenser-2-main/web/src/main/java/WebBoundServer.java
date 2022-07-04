import Handlers.*;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.jetbrains.annotations.NotNull;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import dataStore.DefaultAccessManager;
import dataStore.Sessions;
import Handlers.claim.ClaimExpenseHandler;
import Handlers.RatingsHandler;

import static io.javalin.apibuilder.ApiBuilder.*;

public class WebBoundServer {
    private static final int DEFAULT_PORT = 8070;
    private static final String STATIC_DIR = "/html";
    private final Javalin app;


    /**
     * The main class starts the server on the default port 7070.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        WebBoundServer server = new WebBoundServer();
        server.start(DEFAULT_PORT);
    }

    public WebBoundServer() {
        configureThymeleafTemplateEngine();
        app = createAndConfigureServer();
        setupRoutes(app);
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

    /**
     * Setup the Thymeleaf template engine to load templates from 'resources/templates'
     */
    private void configureThymeleafTemplateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateEngine.setTemplateResolver(templateResolver);

        templateEngine.addDialect(new LayoutDialect());
        JavalinThymeleaf.configure(templateEngine);
    }

    private void setupRoutes(Javalin server) {
        server.routes(() -> {
            loginAndLogoutRoutes();
            homePageRoute();
            expenseRoutes();
            claimRoutes();
            settlementRoutes();
            ratingsHandler();
        });
    }

    private static void settlementRoutes() {
        path(SettlementViewHandler.PATH, () -> {
            get(SettlementViewHandler::renderSettleClaimForm);
            post(SettlementViewHandler::submitSettlement);
        });
        post("/settelment",SettlementViewHandler::submitSettlement);
    }

    private static void claimRoutes() {
        get(ClaimExpenseHandler.PATH, ClaimExpenseHandler::renderClaimExpensePage);
        post(ClaimExpenseHandler.PATH, ClaimExpenseHandler::create);
        put("/claim", ClaimExpenseHandler::updateClaim);
        get("/claims/by/{email}?{settled=value}", ClaimExpenseHandler::getClaimsBy);
        get("/settlement", ClaimExpenseHandler::createSettlementClaim);
        get("/totalclaimvalue/from/{email}", ClaimExpenseHandler::getTotalClaimValueFrom);
        get("totalclaimvalue/by/{email}", ClaimExpenseHandler::getTotalClaimValueBy);
    }
    private static void ratingsHandler() {
        get("/ratings?{by=value}", RatingsHandler::getRatings);
    }

    private static void expenseRoutes() {
        path("/newexpense", () -> get(context -> {
            context.render("expenseform.html");
        }));
        post(CaptureExpenseHandler.PATH, CaptureExpenseHandler::createExpense);
        get("/expense/{email=value}",CaptureExpenseHandler::getAllExpense);
        get("/person/{email}",CaptureExpenseHandler::getAllTotals);
        get("/expense/{id}",CaptureExpenseHandler::getExpenseById);

    }

    private void homePageRoute() {
        path(NettExpenseHandler.PATH, () -> get(NettExpenseHandler::renderHomePage));
    }

    private void loginAndLogoutRoutes() {

        post(LoginHandler.LOGIN_PATH, LoginHandler::handleLogin);
        get(LoginHandler.LOGOUT_PATH, LoginHandler::handleLogout);
    }

    @NotNull
    private Javalin createAndConfigureServer() {
        return Javalin.create(config -> {
            config.addStaticFiles(STATIC_DIR, Location.CLASSPATH);
            config.sessionHandler(Sessions::nopersistSessionHandler);
            config.accessManager(new DefaultAccessManager());
        });
    }
}