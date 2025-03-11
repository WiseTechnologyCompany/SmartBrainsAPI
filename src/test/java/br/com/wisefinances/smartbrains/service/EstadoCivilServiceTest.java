package br.com.wisefinances.smartbrains.service;

import br.com.wisefinances.smartbrains.config.AbstractTest;
import br.com.wisefinances.smartbrains.model.dto.EstadoCivilDTO;
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
public class EstadoCivilServiceTest extends AbstractTest {

    private String estadoCivil;

    private final Integer ID = 1;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EstadoCivilService estadoCivilService;

    @BeforeEach
    void setUp() throws IOException {
        if (estadoCivil == null) {
            estadoCivil = new String(Files.readAllBytes(Paths.get("src/test/resources/estadoCivil/estadoCivil.json")));
        }
    }

    @Test
    @Order(1)
    void findAll() {
        var estadoCivil = estadoCivilService.findAll();
        assertThat(estadoCivil).isNotEmpty();
    }

    @Test
    @Order(2)
    void findById() {
        var estadoCivil = estadoCivilService.findById(ID);

        assertThat(estadoCivil).isNotNull();
        assertThat(estadoCivil.getId()).isEqualTo(ID);
    }

    @Test
    @Order(3)
    void save() {
        Assertions.assertDoesNotThrow(() -> {
            var estadoCivilDTO = objectMapper.readValue(estadoCivil, EstadoCivilDTO.class);
            var estadoCivil = estadoCivilService.save(estadoCivilDTO);

            assertThat(estadoCivilDTO).isNotNull();
            assertThat(estadoCivil).isNotNull();
            assertThat(estadoCivil.getDescricao()).isEqualToIgnoringCase(estadoCivilDTO.getDescricao());
        });
    }

    @Test
    @Order(4)
    void update() {
        Assertions.assertDoesNotThrow(() -> {
            var estadoCivilDTO = objectMapper.readValue(estadoCivil, EstadoCivilDTO.class);

            estadoCivilService.update(ID, estadoCivilDTO);
            var findEstadoCivil = estadoCivilService.findById(ID);

            assertThat(findEstadoCivil.getDescricao()).isEqualToIgnoringCase(estadoCivilDTO.getDescricao());
        });
    }

    @Test
    @Order(5)
    void delete() {
        Assertions.assertDoesNotThrow(() -> {
            estadoCivilService.delete(ID);
        });
    }
}