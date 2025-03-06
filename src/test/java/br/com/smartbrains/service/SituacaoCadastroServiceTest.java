package br.com.smartbrains.service;

import br.com.smartbrains.config.AbstractTest;
import br.com.smartbrains.model.dto.SituacaoCadastroDTO;
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
public class SituacaoCadastroServiceTest extends AbstractTest {

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
        int id = 1;
        var situacaoCadastro = situacaoCadastroService.findById(id);

        assertThat(situacaoCadastro).isNotNull();
    }

    @Test
    @Order(3)
    void save() {
        Assertions.assertDoesNotThrow(() -> {
            var situacaoCadastroDTO = objectMapper.readValue(situacaoCadastro, SituacaoCadastroDTO.class);

            var situacaoCadastro = situacaoCadastroService.save(situacaoCadastroDTO);
            var findSituacaoCadastro = situacaoCadastroService.findById(situacaoCadastro.getId());

            assertThat(situacaoCadastro).isNotNull();
            assertThat(findSituacaoCadastro).isNotNull();
        });
    }

    @Test
    @Order(4)
    void update() {
        Assertions.assertDoesNotThrow(() -> {
            int id = 1;
            var situacaoCadastroDTO = objectMapper.readValue(situacaoCadastro, SituacaoCadastroDTO.class);

            situacaoCadastroService.update(id, situacaoCadastroDTO);
            var situacaoCadastro = situacaoCadastroService.findById(id);

            assertThat(situacaoCadastro.getDescricao()).isEqualTo(situacaoCadastroDTO.getDescricao());
        });
    }

    @Test
    @Order(5)
    void delete() {
        int id = 1;

        Assertions.assertDoesNotThrow(() -> {
            situacaoCadastroService.delete(id);
        });
    }
}