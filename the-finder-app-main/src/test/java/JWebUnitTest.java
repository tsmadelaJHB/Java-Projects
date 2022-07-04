//import io.javalin.Javalin;
//import org.junit.Before;
//import org.junit.Test;
//
//import net.sourceforge.jwebunit.util.TestingEngineRegistry;
//import za.co.wethinkcode.Server;
//
//import static net.sourceforge.jwebunit.junit.JWebUnit.*;
//
//public class JWebUnitTest {
//    @Before
//    public void prepare(){
//        Server myclass = new Server();
//        Javalin server = myclass.start(7000);
//        myclass.setupRoutes(server);
//        setTestingEngineKey(TestingEngineRegistry.TESTING_ENGINE_HTMLUNIT);
//        setBaseUrl("http://localhost:7000/");
//    }
//    @Test
//    public void testLoginPage(){
//       beginAt("login-page");
//       assertTitleEquals("Login");
//       assertLinkPresent("home");
//       clickLink("home");
//       assertTitleEquals("Home");
//       setTextField("username", "test"); // do the login
//       setTextField("password", "test123");
//       submit();
//       assertTitleEquals("Home");
//    }
//    @Test
//    public void testHomePage() {
//        //beginAt("login");
//        //assertTitleEquals("Home");
//
//    }
//
//}
