package za.co.wethinkcode.server.World4;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.server.RobotWorldClient;
import za.co.wethinkcode.server.RobotWorldJsonClient;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


public class LaunchRobotsWithObstacleTest {
    private final static int DEFAULT_PORT = 5000;
    private final static String DEFAULT_IP = "localhost";
    private final RobotWorldClient serverClient = new RobotWorldJsonClient();

    @BeforeEach
    void connectToServer(){
        serverClient.connect(DEFAULT_IP, DEFAULT_PORT);
    }

    @AfterEach
    void disconnectFromServer(){
        serverClient.disconnect();
    }

    @Test
    void LaunchRobotNoSpace() {
        // Given that I am connected to a running Robot Worlds server
        // And the world is of size 2x2 (The world is configured or hardcoded to this size)
        assertTrue(serverClient.isConnected());

        // When I send a valid launch request to the server
        String robot1 = "{" +
                "  \"robot\": \"HAL\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response1 = serverClient.sendRequest(robot1);

        String robot2 = "{" +
                "  \"robot\": \"ALEX\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response2 = serverClient.sendRequest(robot2);

        String robot3 = "{" +
                "  \"robot\": \"ERIC\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response3 = serverClient.sendRequest(robot3);

        String robot4 = "{" +
                "  \"robot\": \"STAN\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response4 = serverClient.sendRequest(robot4);

        String robot5 = "{" +
                "  \"robot\": \"ROBOT 5\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response5 = serverClient.sendRequest(robot5);

        String robot6 = "{" +
                "  \"robot\": \"ROBOT 6\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response6 = serverClient.sendRequest(robot6);

        String robot7 = "{" +
                "  \"robot\": \"ROBOT 7\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response7 = serverClient.sendRequest(robot7);

        String robot8 = "{" +
                "  \"robot\": \"ROBOT 8\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response8 = serverClient.sendRequest(robot8);

        String robot9 = "{" +
                "  \"robot\": \"ROBOT 9\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response9 = serverClient.sendRequest(robot9);

        // Then I should get a valid response from the server
        assertNotNull(response9.get("result"));
        assertEquals("ERROR", response9.get("result").asText());

        // And the position should be (x:0, y:0)
        assertNotNull(response9.get("data"));
        assertNotNull(response9.get("data").get("message"));
        assertEquals("\"No more space in this world\"", response9.get("data").get("message").toString());
//        assertEquals(0, response3.get("data").get("message").get(1).asInt());

        // And I should also get the state of the robot
        assertNull(response9.get("state"));
    }
}
