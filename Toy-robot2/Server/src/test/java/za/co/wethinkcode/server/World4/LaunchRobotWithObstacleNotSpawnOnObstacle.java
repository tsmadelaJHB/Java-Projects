package za.co.wethinkcode.server.World4;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.server.RobotWorldClient;
import za.co.wethinkcode.server.RobotWorldJsonClient;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;


public class LaunchRobotWithObstacleNotSpawnOnObstacle {
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
    void LaunchRobotsTest() {
        // Given that I am connected to a running Robot Worlds server
        // And the world is of size 1x1 (The world is configured or hardcoded to this size)
        Assertions.assertTrue(serverClient.isConnected());

        // When I send a valid launch request to the server
        String robot1 = "{" +
                "  \"robot\": \"HAL\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response1 = serverClient.sendRequest(robot1);

        // And the position should not be at (x:1, y:1)
        assertNotNull(response1.get("data"));
        assertNotNull(response1.get("data").get("position"));
        if(response1.get("data").get("position").get(0).asInt() == 1 && response1.get("data").get("position").get(1).asInt() == 1)
        {
            assertNotEquals(1, response1.get("data").get("position").get(0).asInt());
            assertNotEquals(1, response1.get("data").get("position").get(1).asInt());
        }

        String robot2 = "{" +
                "  \"robot\": \"ALEX\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response2 = serverClient.sendRequest(robot2);

        assertNotNull(response2.get("data"));
        assertNotNull(response2.get("data").get("position"));
        if(response2.get("data").get("position").get(0).asInt() == 1 && response2.get("data").get("position").get(1).asInt() == 1)
        {
            assertNotEquals(1, response2.get("data").get("position").get(0).asInt());
            assertNotEquals(1, response2.get("data").get("position").get(1).asInt());
        }

        String robot3 = "{" +
                "  \"robot\": \"ERIC\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response3 = serverClient.sendRequest(robot3);

        assertNotNull(response3.get("data"));
        assertNotNull(response3.get("data").get("position"));
        if(response3.get("data").get("position").get(0).asInt() == 1 && response3.get("data").get("position").get(1).asInt() == 1)
        {
            assertNotEquals(1, response3.get("data").get("position").get(0).asInt());
            assertNotEquals(1, response3.get("data").get("position").get(1).asInt());
        }

        String robot4 = "{" +
                "  \"robot\": \"ROBOT 4\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response4 = serverClient.sendRequest(robot4);

        assertNotNull(response4.get("data"));
        assertNotNull(response4.get("data").get("position"));
        if(response4.get("data").get("position").get(0).asInt() == 1 && response4.get("data").get("position").get(1).asInt() == 1)
        {
            assertNotEquals(1, response4.get("data").get("position").get(0).asInt());
            assertNotEquals(1, response4.get("data").get("position").get(1).asInt());
        }

        String robot5 = "{" +
                "  \"robot\": \"ROBOT 5\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response5 = serverClient.sendRequest(robot5);

        assertNotNull(response5.get("data"));
        assertNotNull(response5.get("data").get("position"));
        if(response5.get("data").get("position").get(0).asInt() == 1 && response5.get("data").get("position").get(1).asInt() == 1)
        {
            assertNotEquals(1, response5.get("data").get("position").get(0).asInt());
            assertNotEquals(1, response5.get("data").get("position").get(1).asInt());
        }
        String robot6 = "{" +
                "  \"robot\": \"ROBOT 6\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response6 = serverClient.sendRequest(robot6);

        // And the position should not be at (x:1, y:1)
        assertNotNull(response6.get("data"));
        assertNotNull(response6.get("data").get("position"));
        if(response6.get("data").get("position").get(0).asInt() == 1 && response6.get("data").get("position").get(1).asInt() == 1)
        {
            assertNotEquals(1, response6.get("data").get("position").get(0).asInt());
            assertNotEquals(1, response6.get("data").get("position").get(1).asInt());
        }

        String robot7 = "{" +
                "  \"robot\": \"ROBOT 7\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response7 = serverClient.sendRequest(robot7);

        assertNotNull(response7.get("data"));
        assertNotNull(response7.get("data").get("position"));
        if(response7.get("data").get("position").get(0).asInt() == 1 && response7.get("data").get("position").get(1).asInt() == 1)
        {
            assertNotEquals(1, response7.get("data").get("position").get(0).asInt());
            assertNotEquals(1, response7.get("data").get("position").get(1).asInt());
        }

        String robot8 = "{" +
                "  \"robot\": \"ROBOT 8\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response8 = serverClient.sendRequest(robot8);

        assertNotNull(response8.get("data"));
        assertNotNull(response8.get("data").get("position"));
        if(response8.get("data").get("position").get(0).asInt() == 1 && response8.get("data").get("position").get(1).asInt() == 1)
        {
            assertNotEquals(1, response8.get("data").get("position").get(0).asInt());
            assertNotEquals(1, response8.get("data").get("position").get(1).asInt());
        }


    }
}
