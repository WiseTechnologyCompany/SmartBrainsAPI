package br.com.wisefinances.smartbrains.controller;

import br.com.wisefinances.smartbrains.config.AbstractControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
class CofreControllerTest extends AbstractControllerTest {

    private String cofre;

    @BeforeEach
    void setUp() throws IOException {
        if (cofre == null) {
            cofre = new String(Files.readAllBytes(Paths.get("src/test/resources/cofre/cofre.json")));
        }
    }

    @Test
    @Order(1)
    void getAll() throws Exception {
        testGet("/v1/cofre");
    }

    @Test
    @Order(2)
    void findCofreById() throws Exception {
        testGet("/v1/cofre/1");
    }

    @Test
    @Order(3)
    void saveCofre() throws Exception {
        testPost("/v1/cofre", cofre);
    }

    @Test
    @Order(4)
    void updateCofre() throws Exception {
        testPatch("/v1/cofre/1", cofre);
    }

    @Test
    @Order(5)
    void deleteCofre() throws Exception {
        testDelete("/v1/cofre/1");
    }
}