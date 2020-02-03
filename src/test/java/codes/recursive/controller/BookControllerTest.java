package codes.recursive.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.annotation.MicronautTest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.inject.Inject;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookControllerTest {

    @Inject
    @Client("/")
    RxHttpClient client;

    @Test
    public void testIndex() throws Exception {
        assertEquals(HttpStatus.OK, client.toBlocking().exchange("/book").status());
    }

    @Test
    public void testFindTitleLike() throws Exception {
        String body = client.toBlocking().retrieve("/book/title/Semi");
        ObjectMapper objectMapper = new ObjectMapper();
        List result = objectMapper.readValue(body, List.class);
        assertEquals(result.size(), 1);
    }

    @Test
    public void testFindTitleILike() throws Exception {
        String body = client.toBlocking().retrieve("/book/title/ilike/SEMI");
        ObjectMapper objectMapper = new ObjectMapper();
        List result = objectMapper.readValue(body, List.class);
        assertEquals(result.size(), 1);
    }

    @AfterAll
    void cleanup() {
        client.close();
    }
}
