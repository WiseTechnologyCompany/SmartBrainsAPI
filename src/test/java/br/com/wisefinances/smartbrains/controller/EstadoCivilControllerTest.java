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
class EstadoCivilControllerTest extends AbstractControllerTest {

    private String estadoCivil;

    @BeforeEach
    void setUp() throws IOException {
        if (estadoCivil == null) {
            estadoCivil = new String(Files.readAllBytes(Paths.get("src/test/resources/estadoCivil/estadoCivil.json")));
        }
    }

    @Test
    @Order(1)
    void getAll() throws Exception {
        testGet("/v1/estadocivil");
    }

    @Test
    @Order(2)
    void findEstadoCivilById() throws Exception {
        testGet("/v1/estadocivil/1");
    }

    @Test
    @Order(3)
    void saveEstadoCivil() throws Exception {
        testPost("/v1/estadocivil", estadoCivil);
    }

    @Test
    @Order(4)
    void updateEstadoCivil() throws Exception {
        testPatch("/v1/estadocivil/1", estadoCivil);
    }

    @Test
    @Order(5)
    void deleteGenero() throws Exception {
        testDelete("/v1/estadocivil/1");
    }
}