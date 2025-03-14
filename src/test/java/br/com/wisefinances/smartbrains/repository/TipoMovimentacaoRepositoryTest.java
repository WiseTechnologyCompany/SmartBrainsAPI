package br.com.wisefinances.smartbrains.repository;

import br.com.wisefinances.smartbrains.config.AbstractTest;
import br.com.wisefinances.smartbrains.model.entity.TipoMovimentacao;
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
public class TipoMovimentacaoRepositoryTest extends AbstractTest {

    private String tipoMovimentacao;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TipoMovimentacaoRepository tipoMovimentacaoRepository;

    @BeforeEach
    void setUp() throws IOException {
        if (tipoMovimentacao == null) {
            tipoMovimentacao = new String(Files.readAllBytes(Paths.get("src/test/resources/tipoMovimentacao/tipoMovimentacao.json")));
        }
    }

    @Test
    @Order(1)
    void findAll() {
        var tipoMovimentacao = tipoMovimentacaoRepository.findAll();
        assertThat(tipoMovimentacao).isNotEmpty();
    }

    @Test
    @Order(2)
    void findById() {
        int id = 1;
        var tipoMovimentacao = tipoMovimentacaoRepository.getReferenceById(id);

        assertThat(tipoMovimentacao).isNotNull();
        assertThat(tipoMovimentacao.getId()).isEqualTo(id);
    }

    @Test
    @Order(3)
    void save() {
        Assertions.assertDoesNotThrow(() -> {
            var tipoMovimentacaoEntity = objectMapper.readValue(tipoMovimentacao, TipoMovimentacao.class);
            var savedTipoMovimentacao = tipoMovimentacaoRepository.save(tipoMovimentacaoEntity);

            assertThat(tipoMovimentacaoEntity).isNotNull();
            assertThat(savedTipoMovimentacao).isNotNull();
            assertThat(savedTipoMovimentacao.getDescricao()).isEqualToIgnoringCase(tipoMovimentacaoEntity.getDescricao());
        });
    }

    @Test
    @Order(4)
    void findByIdException() {
        int id = 999;
        var tipoMovimentacao = tipoMovimentacaoRepository.findById(id);

        assertThrows(EntityNotFoundException.class, () -> tipoMovimentacao.orElseThrow(EntityNotFoundException::new));
    }
}
