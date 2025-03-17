package br.com.wisefinances.smartbrains.service;

import br.com.wisefinances.smartbrains.config.AbstractTest;
import br.com.wisefinances.smartbrains.model.dto.SituacaoCadastroDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@SpringBootTest
class SituacaoCadastroServiceTest extends AbstractTest {

    private final Integer ID = 1;

    private String situacaoCadastro;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    SituacaoCadastroService situacaoCadastroService;

    @BeforeEach
    void setUp() throws IOException {
        if (situacaoCadastro == null) {
            situacaoCadastro = new String(Files.readAllBytes(Paths.get("src/test/resources/situacaoCadastro/situacaoCadastro.json")));
        }
    }

    @Test
    @Order(1)
    void findAll() {
        var situacaoCadastro = situacaoCadastroService.findAll();
        assertThat(situacaoCadastro).isNotEmpty();
    }

    @Test
    @Order(2)
    void findById() {
        var situacaoCadastro = situacaoCadastroService.findById(ID);

        assertThat(situacaoCadastro).isNotNull();
        assertThat(situacaoCadastro.getId()).isEqualTo(ID);
    }

    @Test
    @Order(3)
    void save() {
        Assertions.assertDoesNotThrow(() -> {
            var situacaoCadastroDTO = objectMapper.readValue(situacaoCadastro, SituacaoCadastroDTO.class);
            var situacaoCadastro = situacaoCadastroService.save(situacaoCadastroDTO);

            assertThat(situacaoCadastroDTO).isNotNull();
            assertThat(situacaoCadastro).isNotNull();
            assertThat(situacaoCadastro.getStatus()).isEqualTo(201);
        });
    }

    @Test
    @Order(4)
    void update() {
        Assertions.assertDoesNotThrow(() -> {
            var situacaoCadastroDTO = objectMapper.readValue(situacaoCadastro, SituacaoCadastroDTO.class);

            situacaoCadastroService.update(ID, situacaoCadastroDTO);
            var situacaoCadastro = situacaoCadastroService.findById(ID);

            assertThat(situacaoCadastro.getDescricao()).isEqualToIgnoringCase(situacaoCadastroDTO.getDescricao());
        });
    }

    @Test
    @Order(5)
    void delete() {
        Assertions.assertDoesNotThrow(() -> {
            situacaoCadastroService.delete(ID);
        });
    }
}