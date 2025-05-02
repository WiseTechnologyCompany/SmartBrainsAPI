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
class MovimentacaoControllerTest extends AbstractControllerTest {

    private String email;

    private String movimentacao;

    @BeforeEach
    void setUp() throws IOException {
        if (movimentacao == null) {
            movimentacao = new String(Files.readAllBytes(Paths.get("src/test/resources/movimentacao/movimentacao.json")));
        }
        if (email == null) {
            email = new String(Files.readAllBytes(Paths.get("src/test/resources/usuario/usuarioEmail.json")));
        }
    }

    @Test
    @Order(1)
    void getAll() throws Exception {
        testGet("/v1/movimentacao");
    }

    @Test
    @Order(2)
    void getTipoMovimentacaoById() throws Exception {
        testGet("/v1/movimentacao/1");
    }

    @Test
    @Order(3)
    void saveTipoMovimentacao() throws Exception {
        testPost("/v1/movimentacao", movimentacao);
    }

    @Test
    @Order(4)
    void updateTipoMovimentacao() throws Exception {
        testPatch("/v1/movimentacao/1", movimentacao);
    }

    @Test
    @Order(5)
    void deleteTipoMovimentacao() throws Exception {
        testDelete("/v1/movimentacao/1");
    }

    @Test
    @Order(6)
    void getAllUserTransactionsTest() throws Exception {
        testPostStatusOk("/v1/movimentacao/user/table", email);
    }

    @Test
    @Order(6)
    void getUserTotalTransactionsTest() throws Exception {
        testPostStatusOk("/v1/movimentacao/user/card", email);
    }

    @Test
    @Order(6)
    void getUserTotalTransactionsByDateTest() throws Exception {
        testPostStatusOk("/v1/movimentacao/user/card/date", email);
    }
}