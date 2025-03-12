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
public class SituacaoCadastroControllerTest extends AbstractControllerTest {

    private String situacaoCadastro;

    @BeforeEach
    void setUp() throws IOException {
        if (situacaoCadastro == null) {
            situacaoCadastro = new String(Files.readAllBytes(Paths.get("src/test/resources/situacaoCadastro/situacaoCadastro.json")));
        }
    }

    @Test
    @Order(1)
    void getAll() throws Exception {
        testGet("/v1/situacaocadastro");
    }

    @Test
    @Order(2)
    void findSituacaoCadastroById() throws Exception {
        testGet("/v1/situacaocadastro/1");
    }

    @Test
    @Order(3)
    void saveSituacaoCadastro() throws Exception {
        testPost("/v1/situacaocadastro", situacaoCadastro);
    }

    @Test
    @Order(4)
    void updateSituacaoCadastro() throws Exception {
        testPatch("/v1/situacaocadastro/1", situacaoCadastro);
    }

    @Test
    @Order(5)
    void deleteSituacaoCadastro() throws Exception {
        testDelete("/v1/situacaocadastro/1");
    }
}