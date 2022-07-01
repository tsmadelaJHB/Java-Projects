package test;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import main.GreetingApi;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingApiTest {

    @Test
    @DisplayName("POST /greet")
    public void shouldGreet() {
        GreetingApi api = new GreetingApi();
        api.start();
        HttpResponse<String> response = Unirest.post("http://localhost:7000/greet")
                .body("World")
                .asString();
        assertEquals(201, response.getStatus());
        assertEquals("Hello, World!", response.getBody());
        api.stop();
    }
}
