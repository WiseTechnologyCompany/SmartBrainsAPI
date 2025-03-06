package br.com.smartbrains.service;

import br.com.smartbrains.config.AbstractTest;
import br.com.smartbrains.model.dto.EstadoCivilDTO;
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
        int id = 1;
        var estadoCivil = estadoCivilService.findById(id);

        assertThat(estadoCivil).isNotNull();
    }

    @Test
    @Order(3)
    void save() {
        Assertions.assertDoesNotThrow(() -> {
            var estadoCivilDTO = objectMapper.readValue(estadoCivil, EstadoCivilDTO.class);

            var estadoCivil = estadoCivilService.save(estadoCivilDTO);
            var findEstadoCivil = estadoCivilService.findById(estadoCivil.getId());

            assertThat(estadoCivil).isNotNull();
            assertThat(findEstadoCivil).isNotNull();
        });
    }

    @Test
    @Order(4)
    void update() {
        Assertions.assertDoesNotThrow(() -> {
            int id = 1;
            var estadoCivilDTO = objectMapper.readValue(estadoCivil, EstadoCivilDTO.class);

            estadoCivilService.update(id, estadoCivilDTO);
            var findEstadoCivil = estadoCivilService.findById(id);

            assertThat(findEstadoCivil.getDescricao()).isEqualTo(estadoCivilDTO.getDescricao());
        });
    }

    @Test
    @Order(5)
    void delete() {
        int id = 1;

        Assertions.assertDoesNotThrow(() -> {
            estadoCivilService.delete(id);
        });
    }
}