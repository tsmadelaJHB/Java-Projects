package za.co.wethinkcode.server.World1;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.server.RobotWorldClient;
import za.co.wethinkcode.server.RobotWorldJsonClient;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


public class LaunchRobotTestCapacityTest {
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
        // And the world is of size 1x1 (The world is configured or hardcoded to this size)
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

        // Then I should get a valid response from the server
        assertNotNull(response3.get("result"));
        assertEquals("ERROR", response3.get("result").asText());

        // And the position should be (x:0, y:0)
        assertNotNull(response3.get("data"));
        assertNotNull(response3.get("data").get("message"));
        assertEquals("\"No more space in this world\"", response3.get("data").get("message").toString());
//        assertEquals(0, response3.get("data").get("message").get(1).asInt());

        // And I should also get the state of the robot
        assertNull(response3.get("state"));
    }
}
