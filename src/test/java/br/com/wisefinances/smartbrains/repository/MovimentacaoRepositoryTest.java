package br.com.wisefinances.smartbrains.repository;

import br.com.wisefinances.smartbrains.config.AbstractTest;
import br.com.wisefinances.smartbrains.model.entity.movimentacao.CreateMovimentacao;
import br.com.wisefinances.smartbrains.repository.movimentacao.CreateMovimentacaoRepository;
import br.com.wisefinances.smartbrains.repository.movimentacao.MovimentacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MovimentacaoRepositoryTest extends AbstractTest {

    private String movimentacao;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MovimentacaoRepository movimentacaoRepository;

    @Autowired
    CreateMovimentacaoRepository createMovimentacaoRepository;

    @BeforeEach
    void setUp() throws IOException {
        if (movimentacao == null) {
            movimentacao = new String(Files.readAllBytes(Paths.get("src/test/resources/movimentacao/movimentacao.json")));
        }
    }

    @Test
    @Order(1)
    void findAll() {
        var movimentacao = movimentacaoRepository.findAll();
        assertThat(movimentacao).isNotEmpty();
    }

    @Test
    @Order(2)
    void findById() {
        int id = 1;
        var movimentacao = movimentacaoRepository.getReferenceById(id);

        assertThat(movimentacao).isNotNull();
        assertThat(movimentacao.getId()).isEqualTo(id);
    }

    @Test
    @Order(3)
    void findAllUserTransactionsByUserId() {
        Assertions.assertDoesNotThrow(() -> {
            var movimentacao = movimentacaoRepository.findAllUserTransactionsByUserId(1);
            assertThat(movimentacao).isNotEmpty();
        });
    }

    @Test
    @Order(4)
    void save() {
        Assertions.assertDoesNotThrow(() -> {
            var movimentacaoEntity = objectMapper.readValue(movimentacao, CreateMovimentacao.class);
            var savedMovimentacao = createMovimentacaoRepository.save(movimentacaoEntity);

            assertThat(movimentacaoEntity).isNotNull();
            assertThat(savedMovimentacao).isNotNull();
            assertThat(savedMovimentacao.getDescricao()).isEqualToIgnoringCase(movimentacaoEntity.getDescricao());
        });
    }

    @Test
    @Order(5)
    void findByIdException() {
        int id = 999;
        var movimentacao = movimentacaoRepository.findById(id);

        assertThrows(EntityNotFoundException.class, () -> movimentacao.orElseThrow(EntityNotFoundException::new));
    }
}