package za.co.wethinkcode.server.World2;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.server.RobotWorldClient;
import za.co.wethinkcode.server.RobotWorldJsonClient;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;



public class LookRobotInWorldTest {
    private final static int DEFAULT_PORT = 5000;
    private final static String DEFAULT_IP = "localhost";
    private final RobotWorldClient serverClient = new RobotWorldJsonClient();
    private final RobotWorldClient serverClient2 = new RobotWorldJsonClient();

    @BeforeEach
    void connectToServer(){
        serverClient.connect(DEFAULT_IP, DEFAULT_PORT);
        serverClient2.connect(DEFAULT_IP, DEFAULT_PORT);
    }

    @AfterEach
    void disconnectFromServer() { serverClient.disconnect();
        serverClient2.disconnect();}

    @Test
    void lookShouldShowAnotherRobot(){

        assertTrue(serverClient.isConnected());
        assertTrue(serverClient2.isConnected());
        String  robot1= "{" +
                "  \"robot\": \"HAL\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response1 = serverClient.sendRequest(robot1);
        String  robot2= "{" +
                "  \"robot\": \"Jordan\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        JsonNode response2 = serverClient2.sendRequest(robot2);

        String requestRobot1 = "{" +
                "  \"robot\": \"HAL\"," +
                "  \"command\": \"look\"," +
                "  \"arguments\": []" +
                "}";
        JsonNode response = serverClient.sendRequest(requestRobot1);

        assertNotNull(response.get("result"));
        assertEquals("OK", response.get("result").asText());

        assertNotNull(response.get("data"));
        assertNotNull(response.get("data").get("objects"));
        assertTrue(response.get("data").get("objects").findValuesAsText("type").contains("ROBOT"));


        assertNotNull(response.get("state"));
    }
}

