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
class GeneroControllerTest extends AbstractControllerTest {

    private String genero;

    @BeforeEach
    void setUp() throws IOException {
        if (genero == null) {
            genero = new String(Files.readAllBytes(Paths.get("src/test/resources/genero/genero.json")));
        }
    }

    @Test
    @Order(1)
    void getAll() throws Exception {
        testGet("/v1/generos");
    }

    @Test
    @Order(2)
    void getGeneroById() throws Exception {
        testGet("/v1/generos/1");
    }

    @Test
    @Order(3)
    void saveGenero() throws Exception {
        testPost("/v1/generos", genero);
    }

    @Test
    @Order(4)
    void updateGenero() throws Exception {
        testPatch("/v1/generos/1", genero);
    }

    @Test
    @Order(5)
    void deleteGenero() throws Exception {
        testDelete("/v1/generos/1");
    }
}