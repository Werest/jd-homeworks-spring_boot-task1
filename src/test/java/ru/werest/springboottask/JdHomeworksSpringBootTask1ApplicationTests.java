package ru.werest.springboottask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class JdHomeworksSpringBootTask1ApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Container
    private static final GenericContainer<?> devapp = new GenericContainer<>("devapp")
            .withExposedPorts(8080);

    @Container
    private static final GenericContainer<?> prodapp = new GenericContainer<>("prodapp")
            .withExposedPorts(8081);

    @Test
    void testDevApp() {
        Integer devappMappedPort = devapp.getMappedPort(8080);
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + devappMappedPort + "/profile", String.class);
        assertEquals(forEntity.getBody(), "Current profile is dev");
    }

    @Test
    void testProdApp() {
        Integer prodappMappedPort = prodapp.getMappedPort(8081);
        ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:" + prodappMappedPort + "/profile", String.class);
        assertEquals(forEntity.getBody(), "Current profile is production");
    }
}
