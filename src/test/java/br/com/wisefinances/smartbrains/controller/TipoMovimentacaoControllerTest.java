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
class TipoMovimentacaoControllerTest extends AbstractControllerTest {

    private String tipoMovimentacao;

    @BeforeEach
    void setUp() throws IOException {
        if (tipoMovimentacao == null) {
            tipoMovimentacao = new String(Files.readAllBytes(Paths.get("src/test/resources/tipoMovimentacao/tipoMovimentacao.json")));
        }
    }

    @Test
    @Order(1)
    void getAll() throws Exception {
        testGet("/v1/tipomovimentacao");
    }

    @Test
    @Order(2)
    void getTipoMovimentacaoById() throws Exception {
        testGet("/v1/tipomovimentacao/1");
    }

    @Test
    @Order(3)
    void saveTipoMovimentacao() throws Exception {
        testPost("/v1/tipomovimentacao", tipoMovimentacao);
    }

    @Test
    @Order(4)
    void updateTipoMovimentacao() throws Exception {
        testPatch("/v1/tipomovimentacao/1", tipoMovimentacao);
    }

    @Test
    @Order(5)
    void deleteTipoMovimentacao() throws Exception {
        testDelete("/v1/tipomovimentacao/1");
    }
}