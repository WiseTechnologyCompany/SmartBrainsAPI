package br.com.wisefinances.smartbrains.service;

import br.com.wisefinances.smartbrains.config.AbstractTest;
import br.com.wisefinances.smartbrains.model.dto.TipoMovimentacaoDTO;
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
class TipoMovimentacaoServiceTest extends AbstractTest {

    private String tipoMovimentacao;

    private final Integer ID = 1;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TipoMovimentacaoService tipoMovimentacaoService;

    @BeforeEach
    void setUp() throws IOException {
        if (tipoMovimentacao == null) {
            tipoMovimentacao = new String(Files.readAllBytes(Paths.get("src/test/resources/tipoMovimentacao/tipoMovimentacao.json")));
        }
    }

    @Test
    @Order(1)
    void findAll() {
        var tipoMovimentacao = tipoMovimentacaoService.findAll();
        assertThat(tipoMovimentacao).isNotEmpty();
    }

    @Test
    @Order(2)
    void findById() {
        int id = 1;
        var tipoMovimentacao = tipoMovimentacaoService.findById(id);

        assertThat(tipoMovimentacao).isNotNull();
        assertThat(tipoMovimentacao.getId()).isEqualTo(id);
    }

    @Test
    @Order(3)
    void save() {
        Assertions.assertDoesNotThrow(() -> {
            var tipoMovimentacaoDTO = objectMapper.readValue(tipoMovimentacao, TipoMovimentacaoDTO.class);
            var tipoMovimentacao = tipoMovimentacaoService.save(tipoMovimentacaoDTO);

            assertThat(tipoMovimentacaoDTO).isNotNull();
            assertThat(tipoMovimentacao).isNotNull();
            assertThat(tipoMovimentacao.getStatus()).isEqualTo(201);
        });
    }

    @Test
    @Order(4)
    void update() {
        Assertions.assertDoesNotThrow(() -> {
            var tipoMovimentacaoDTO = objectMapper.readValue(tipoMovimentacao, TipoMovimentacaoDTO.class);

            tipoMovimentacaoService.update(ID, tipoMovimentacaoDTO);
            var findTipoMovimentacao = tipoMovimentacaoService.findById(ID);

            assertThat(findTipoMovimentacao.getDescricao()).isEqualToIgnoringCase(tipoMovimentacaoDTO.getDescricao());
        });
    }

    @Test
    @Order(5)
    void delete() {
        Assertions.assertDoesNotThrow(() -> {
            tipoMovimentacaoService.delete(ID);
        });
    }
}