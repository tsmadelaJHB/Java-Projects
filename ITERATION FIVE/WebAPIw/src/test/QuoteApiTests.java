package test;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.UnirestException;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import main.Quote;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuoteApiTests {
    private static main.QuteQuoteServer server;

    @BeforeAll
    public static void startServer() throws SQLException {
        server = new main.QuteQuoteServer();
        server.start(5000);
    }

    @AfterAll
    public static void stopServer() {
        server.stop();
    }

    @Test
    @DisplayName("GET /quote/{id}")
    public void getOneQuote() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:5000/quote/1").asJson();
        assertEquals(200, response.getStatus());
        assertEquals("application/json", response.getHeaders().getFirst("Content-Type"));

        JSONObject jsonObject = response.getBody().getObject();
        assertEquals("There is no snooze button on a cat who wants breakfast.", jsonObject.get("text"));
        assertEquals("Unknown", jsonObject.get("name"));
    }

    @Test
    @DisplayName("GET /quotes")
    void getAllQuotes() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get("http://localhost:5000/quotes").asJson();
        assertEquals(200, response.getStatus());
        assertEquals("application/json", response.getHeaders().getFirst("Content-Type"));

        JSONArray jsonArray = response.getBody().getArray();
        assertTrue(jsonArray.length() > 1);
    }

    @Test
    @DisplayName("POST /quotes")
    void create() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post("http://localhost:5000/quotes")
                .header("Content-Type", "application/json")
                .body(Quote.create("Meow", "The Cat"))
                .asJson();
        assertEquals(201, response.getStatus());
        assertEquals("/quote/4", response.getHeaders().getFirst("Location"));

        response = Unirest.get("http://localhost:5000/quote/4").asJson();
        assertEquals(200, response.getStatus());
    }
}
