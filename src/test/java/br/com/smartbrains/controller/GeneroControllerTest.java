package br.com.smartbrains.controller;

import br.com.smartbrains.config.AbstractControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
public class GeneroControllerTest extends AbstractControllerTest {

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
        testGet("/generos/v1");
    }

    @Test
    @Order(2)
    void getGeneroById() throws Exception {
        testGet("/generos/v1/1");
    }

    @Test
    @Order(3)
    void saveGenero() throws Exception {
        testPost("/generos/v1", genero);
    }

    @Test
    @Order(4)
    void updateGenero() throws Exception {
        testPatch("/generos/v1/1", genero);
    }

    @Test
    @Order(5)
    void deleteGenero() throws Exception {
        testDelete("/generos/v1/1");
    }
}