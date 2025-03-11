package br.com.wisefinances.smartbrains.repository;

import br.com.wisefinances.smartbrains.config.AbstractTest;
import br.com.wisefinances.smartbrains.model.entity.EstadoCivil;
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
class EstadoCivilRepositoryTest extends AbstractTest {

    private String estadoCivil;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    EstadoCivilRepository estadoCivilRepository;

    @BeforeEach
    void setUp() throws IOException {
        if (estadoCivil == null) {
            estadoCivil = new String(Files.readAllBytes(Paths.get("src/test/resources/estadoCivil/estadoCivil.json")));
        }
    }

    @Test
    @Order(1)
    void findAll() {
        var estadoCivil = estadoCivilRepository.findAll();
        assertThat(estadoCivil).isNotEmpty();
    }

    @Test
    @Order(2)
    void findById() {
        int id = 1;
        var estadoCivil = estadoCivilRepository.getReferenceById(id);

        assertThat(estadoCivil).isNotNull();
        assertThat(estadoCivil.getId()).isEqualTo(id);
    }

    @Test
    @Order(3)
    void save() {
        Assertions.assertDoesNotThrow(() -> {
            var estadoCivilEntity = objectMapper.readValue(estadoCivil, EstadoCivil.class);
            var savedEstadoCivil = estadoCivilRepository.save(estadoCivilEntity);

            assertThat(estadoCivilEntity).isNotNull();
            assertThat(savedEstadoCivil).isNotNull();
            assertThat(savedEstadoCivil.getDescricao()).isEqualToIgnoringCase(savedEstadoCivil.getDescricao());
        });
    }

    @Test
    @Order(4)
    void findByIdException() {
        int id = 999;
        var estadoCivil = estadoCivilRepository.findById(id);

        assertThrows(EntityNotFoundException.class, () -> estadoCivil.orElseThrow(EntityNotFoundException::new));
    }
}