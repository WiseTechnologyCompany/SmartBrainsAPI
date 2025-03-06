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
        testGet("/situacaocadastro/v1");
    }

    @Test
    @Order(2)
    void findSituacaoCadastroById() throws Exception {
        testGet("/situacaocadastro/v1/1");
    }

    @Test
    @Order(3)
    void saveSituacaoCadastro() throws Exception {
        testPost("/situacaocadastro/v1", situacaoCadastro);
    }

    @Test
    @Order(4)
    void updateSituacaoCadastro() throws Exception {
        testPatch("/situacaocadastro/v1/1", situacaoCadastro);
    }

    @Test
    @Order(5)
    void deleteSituacaoCadastro() throws Exception {
        testDelete("/situacaocadastro/v1/1");
    }
}