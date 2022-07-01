package za.co.wethinkcode.server.World1;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.server.RobotWorldClient;
import za.co.wethinkcode.server.RobotWorldJsonClient;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class LaunchAnotherRobotTest {
    private final static int DEFAULT_PORT = 5000;
    private final static String DEFAULT_IP = "localhost";
    private final RobotWorldClient serverClient = new RobotWorldJsonClient();
    private static JsonNode response;

    @BeforeEach
    void connectToServer(){
        serverClient.connect(DEFAULT_IP, DEFAULT_PORT);
    }

    @AfterEach
    void disconnectFromServer(){
        serverClient.disconnect();
    }

    @Test
    void LaunchAnotherRobot() {
        // Given that I am connected to a running Robot Worlds server
        // And the world is of size 2x2 (The world is configured or hardcoded to this size)
        assertTrue(serverClient.isConnected());

        // When I send a valid launch request to the server
        String request = "{" +
                "  \"robot\": \"HAL\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        response = serverClient.sendRequest(request);

        // launch another robot to server
        request = "{" +
                "  \"robot\": \"R2D2\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        response = serverClient.sendRequest(request);


        // Then I should get a valid response from the server
        assertNotNull(response.get("result"));
        assertEquals("ERROR", response.get("result").asText());
    }
}
