package br.com.wisefinances.smartbrains.repository;

import br.com.wisefinances.smartbrains.config.AbstractTest;
import br.com.wisefinances.smartbrains.model.entity.SituacaoCadastro;
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
class SituacaoCadastroRepositoryTest extends AbstractTest {

    private String situacaoCadastro;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SituacaoCadastroRepository situacaoCadastroRepository;

    @BeforeEach
    void setUp() throws IOException {
        if (situacaoCadastro == null) {
            situacaoCadastro = new String(Files.readAllBytes(Paths.get("src/test/resources/situacaoCadastro/situacaoCadastro.json")));
        }
    }

    @Test
    @Order(1)
    void findAll() {
        var situacaoCadastro = situacaoCadastroRepository.findAll();
        assertThat(situacaoCadastro).isNotEmpty();
    }

    @Test
    @Order(2)
    void findById() {
        int id = 1;
        var situacaoCadastro = situacaoCadastroRepository.getReferenceById(id);

        assertThat(situacaoCadastro).isNotNull();
        assertThat(situacaoCadastro.getId()).isEqualTo(id);
    }

    @Test
    @Order(3)
    void save() {
        Assertions.assertDoesNotThrow(() -> {
            var situacaoCadastroEntity = objectMapper.readValue(situacaoCadastro, SituacaoCadastro.class);
            var savedSituacaoCadastro = situacaoCadastroRepository.save(situacaoCadastroEntity);

            assertThat(situacaoCadastroEntity).isNotNull();
            assertThat(savedSituacaoCadastro).isNotNull();
            assertThat(savedSituacaoCadastro.getDescricao()).isEqualToIgnoringCase(situacaoCadastroEntity.getDescricao());
        });
    }

    @Test
    @Order(4)
    void findByIdException() {
        int id = 999;
        var situacaoCadastro = situacaoCadastroRepository.findById(id);

        assertThrows(EntityNotFoundException.class, () -> situacaoCadastro.orElseThrow(EntityNotFoundException::new));
    }
}