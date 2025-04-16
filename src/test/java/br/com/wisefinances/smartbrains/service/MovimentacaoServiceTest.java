package br.com.wisefinances.smartbrains.service;

import br.com.wisefinances.smartbrains.config.AbstractTest;
import br.com.wisefinances.smartbrains.model.dto.movimentacao.CreateMovimentacaoDTO;
import br.com.wisefinances.smartbrains.model.dto.movimentacao.UserTransactionsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MovimentacaoServiceTest extends AbstractTest {

    private String movimentacao;

    private final Integer ID = 1;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MovimentacaoService movimentacaoService;

    @BeforeEach
    void setUp() throws IOException {
        if (movimentacao == null) {
            movimentacao = new String(Files.readAllBytes(Paths.get("src/test/resources/movimentacao/movimentacao.json")));
        }
    }

    @Test
    @Order(1)
    void findAll() {
        var movimentacao = movimentacaoService.findAll(Pageable.unpaged());
        assertThat(movimentacao).isNotEmpty();
    }

    @Test
    @Order(2)
    void findById() {
        int id = 1;
        var movimentacao = movimentacaoService.findById(id);

        assertThat(movimentacao).isNotNull();
        assertThat(movimentacao.getId()).isEqualTo(id);
    }

    @Test
    @Order(3)
    void findAllUserTransactions() {
        Assertions.assertDoesNotThrow(() -> {
            String email = "teste@teste.com";
            List<UserTransactionsDTO> userTransactions = movimentacaoService.findAllUserTransactions(email);
            assertThat(userTransactions).isNotEmpty();
        });
    }

    @Test
    @Order(4)
    void save() {
        Assertions.assertDoesNotThrow(() -> {
            var createMovimentacaoDTO = objectMapper.readValue(movimentacao, CreateMovimentacaoDTO.class);
            var movimentacao = movimentacaoService.save(createMovimentacaoDTO);

            assertThat(createMovimentacaoDTO).isNotNull();
            assertThat(movimentacao).isNotNull();
            assertThat(movimentacao.getStatus()).isEqualTo(201);
        });
    }

    @Test
    @Order(5)
    void update() {
        Assertions.assertDoesNotThrow(() -> {
            var updateMovimentacaoDTO = objectMapper.readValue(movimentacao, CreateMovimentacaoDTO.class);
            var movimentacao = movimentacaoService.update(ID, updateMovimentacaoDTO);

            assertThat(movimentacao).isNotNull();
        });
    }

    @Test
    @Order(6)
    void delete() {
        Assertions.assertDoesNotThrow(() -> {
            movimentacaoService.delete(ID);
        });
    }
}