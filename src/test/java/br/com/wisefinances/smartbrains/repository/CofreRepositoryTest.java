package br.com.wisefinances.smartbrains.repository;

import br.com.wisefinances.smartbrains.config.AbstractTest;
import br.com.wisefinances.smartbrains.model.entity.cofre.CreateCofre;
import br.com.wisefinances.smartbrains.repository.cofre.CofreRepository;
import br.com.wisefinances.smartbrains.repository.cofre.CreateCofreRepository;
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
class CofreRepositoryTest extends AbstractTest {

    private String cofre;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CofreRepository cofreRepository;

    @Autowired
    CreateCofreRepository createCofreRepository;

    @BeforeEach
    void setUp() throws IOException {
        if (cofre == null) {
            cofre = new String(Files.readAllBytes(Paths.get("src/test/resources/cofre/cofre.json")));
        }
    }

    @Test
    @Order(1)
    void findAll() {
        var cofre = cofreRepository.findAll();
        assertThat(cofre).isNotEmpty();
    }

    @Test
    @Order(2)
    void findById() {
        int id = 1;
        var cofre = cofreRepository.getReferenceById(id);

        assertThat(cofre).isNotNull();
        assertThat(cofre.getId()).isEqualTo(id);
    }

    @Test
    @Order(3)
    void save() {
        Assertions.assertDoesNotThrow(() -> {
            var cofreEntity = objectMapper.readValue(cofre, CreateCofre.class);
            var savedCofre = createCofreRepository.save(cofreEntity);

            assertThat(cofreEntity).isNotNull();
            assertThat(cofreEntity).isNotNull();
            assertThat(savedCofre.getObservacao()).isEqualToIgnoringCase(savedCofre.getObservacao());
        });
    }

    @Test
    @Order(4)
    void findByIdException() {
        int id = 999;
        var cofre = cofreRepository.findById(id);

        assertThrows(EntityNotFoundException.class, () -> cofre.orElseThrow(EntityNotFoundException::new));
    }
}