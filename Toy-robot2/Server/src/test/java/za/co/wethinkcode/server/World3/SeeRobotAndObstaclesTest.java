package za.co.wethinkcode.server.World3;


import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.server.RobotWorldClient;
import za.co.wethinkcode.server.RobotWorldJsonClient;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SeeRobotAndObstaclesTest {


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
    void seerobotandobstacles() throws InterruptedException {
        // Given that I am connected to a running Robot Worlds server
        // And the world is of size 2x2 (The world is configured or hardcoded to this size)
        // And the world has obstacle at 1,1
        assertTrue(serverClient.isConnected());

        // When I send a valid launch request to the server for three robots
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
//        TimeUnit.SECONDS.sleep(10);

        // When I send a valid look request to the server
        String request = "{" +
                "  \"robot\": \"HAL\"," +
                "  \"command\": \"look\"," +
                "  \"arguments\": []" +
                "}";
        JsonNode response3 = serverClient.sendRequest(request);

        // Then I should get a valid response from the server
        assertNotNull(response3.get("result"));
        assertEquals("OK", response3.get("result").asText());

        assertNotNull(response3.get("data"));
        assertNotNull(response3.get("data").get("objects"));
        assertTrue(response3.get("data").get("objects").findValuesAsText("type").contains("OBSTACLE"));
        assertTrue(response3.get("data").get("objects").findValuesAsText("type").contains("ROBOT"));

        // And I should also get the state of the robot
        assertNotNull(response3.get("state"));
    }
}
