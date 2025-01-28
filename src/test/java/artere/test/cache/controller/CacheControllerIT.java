package artere.test.cache.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CacheControllerIT {
    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void testCacheOperations() {
        String baseUrl = "http://localhost:" + port + "/api/cache";

        // Test put
        ResponseEntity<String> putResponse = restTemplate.postForEntity(baseUrl + "?key=testKey&value=testValue", null, String.class);
        assertEquals("Cached successfully", putResponse.getBody());

        // Test get
        String value = restTemplate.getForObject(baseUrl + "?key=testKey", String.class);
        assertEquals("testValue", value);

        // Test remove key
        ResponseEntity<String> deleteResponse = restTemplate.exchange(
            baseUrl + "/key?key=testKey",
            HttpMethod.DELETE,
            null,
            String.class
        );
        assertEquals("Key removed from cache", deleteResponse.getBody());

        // Test clear
        restTemplate.delete(baseUrl);
        String clearedValue = restTemplate.getForObject(baseUrl + "?key=testKey", String.class);
        assertEquals("Key not found in cache", clearedValue);
    }

    @Test
    void should_return_not_found_when_key_does_not_exist() {
        String baseUrl = "http://localhost:" + port + "/api/cache";

        // Try to get not exist key
        String response = restTemplate.getForObject(baseUrl + "?key=notFoundKey", String.class);
        assertEquals("Key not found in cache", response);
    }
}
