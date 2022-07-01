package za.co.wethinkcode.server;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import za.co.wethinkcode.server.RobotWorldClient;
import za.co.wethinkcode.server.RobotWorldJsonClient;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

public class moveTest {
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
    void move(){
        String request = "{" +
                "  \"robot\": \"HAL\"," +
                "  \"command\": \"launch\"," +
                "  \"arguments\": [\"shooter\",\"5\",\"5\"]" +
                "}";
        response = serverClient.sendRequest(request);
//        System.out.println(response.get("state").get("direction"));
//        System.out.println(response.get("data").get("position"));
//
        request = "{" +
                "  \"robot\": \"HAL\"," +
                "  \"command\": \"forward\"," +
                "  \"arguments\": [\"1\"]" +
                "}";
        response = serverClient.sendRequest(request);
        System.out.println(response.get("data"));

        request = "{" +
                "  \"robot\": \"HAL\"," +
                "  \"command\": \"turn\"," +
                "  \"arguments\": [\"right\"]" +
                "}";
        response = serverClient.sendRequest(request);

        request = "{" +
                "  \"robot\": \"HAL\"," +
                "  \"command\": \"forward\"," +
                "  \"arguments\": [\"1\"]" +
                "}";
        response = serverClient.sendRequest(request);
        System.out.println(response.get("data"));

        request = "{" +
                "  \"robot\": \"HAL\"," +
                "  \"command\": \"turn\"," +
                "  \"arguments\": [\"right\"]" +
                "}";
        response = serverClient.sendRequest(request);

        request = "{" +
                "  \"robot\": \"HAL\"," +
                "  \"command\": \"forward\"," +
                "  \"arguments\": [\"1\"]" +
                "}";
        response = serverClient.sendRequest(request);
        System.out.println(response.get("data"));

        request = "{" +
                "  \"robot\": \"HAL\"," +
                "  \"command\": \"turn\"," +
                "  \"arguments\": [\"right\"]" +
                "}";
        response = serverClient.sendRequest(request);

        request = "{" +
                "  \"robot\": \"HAL\"," +
                "  \"command\": \"forward\"," +
                "  \"arguments\": [\"1\"]" +
                "}";
        response = serverClient.sendRequest(request);
        System.out.println(response.get("data"));


//        System.out.println(response.get("state").get("direction"));
//        System.out.println(response.get("data").get("position"));
//        request = "{" +
//                "  \"robot\": \"HAL\"," +
//                "  \"command\": \"back\"," +
//                "  \"arguments\": [\"1\"]" +
//                "}";
//        response = serverClient.sendRequest(request);
//        System.out.println(response.get("state").get("direction"));
//        System.out.println(response.get("data").get("position"));
//        request = "{" +
//                "  \"robot\": \"HAL\"," +
//                "  \"command\": \"turn\"," +
//                "  \"arguments\": [\"right\"]" +
//                "}";
//        response = serverClient.sendRequest(request);
//        System.out.println(response.get("state").get("direction"));
//        request = "{" +
//                "  \"robot\": \"HAL\"," +
//                "  \"command\": \"forward\"," +
//                "  \"arguments\": [\"1\"]" +
//                "}";
//        response = serverClient.sendRequest(request);
//        System.out.println(response.get("data").get("position"));
//        request = "{" +
//                "  \"robot\": \"HAL\"," +
//                "  \"command\": \"back\"," +
//                "  \"arguments\": [\"1\"]" +
//                "}";
//        response = serverClient.sendRequest(request);
//        System.out.println(response.get("state").get("direction"));
//        System.out.println(response.get("data").get("position"));
//        request = "{" +
//                "  \"robot\": \"HAL\"," +
//                "  \"command\": \"turn\"," +
//                "  \"arguments\": [\"left\"]" +
//                "}";
//        response = serverClient.sendRequest(request);
//        System.out.println(response.get("state").get("direction"));
//        String request = "{" +
//                "  \"robot\": \"HAL\"," +
//                "  \"command\": \"forward\"," +
//                "  \"arguments\": [\"1\"]" +
//                "}";
//        response = serverClient.sendRequest(request);
//        System.out.println(response);

    }
}
