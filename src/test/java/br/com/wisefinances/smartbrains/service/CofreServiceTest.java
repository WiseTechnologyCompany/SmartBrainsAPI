package br.com.wisefinances.smartbrains.service;

import br.com.wisefinances.smartbrains.config.AbstractTest;
import br.com.wisefinances.smartbrains.model.create.dto.CreateCofreDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CofreServiceTest extends AbstractTest {

    private String cofre;

    private final Integer ID = 1;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CofreService cofreService;

    @BeforeEach
    void setUp() throws IOException {
        if (cofre == null) {
            cofre = new String(Files.readAllBytes(Paths.get("src/test/resources/cofre/cofre.json")));
        }
    }

    @Test
    @Order(1)
    void findAll() {
        var cofre = cofreService.findAll(Pageable.unpaged());
        assertThat(cofre).isNotEmpty();
    }

    @Test
    @Order(2)
    void findById() {
        var cofre = cofreService.findById(ID);

        assertThat(cofre).isNotNull();
        assertThat(cofre.getId()).isEqualTo(ID);
    }

    @Test
    @Order(3)
    void save() {
        Assertions.assertDoesNotThrow(() -> {
            var createCofreDTO = objectMapper.readValue(cofre, CreateCofreDTO.class);
            var cofre = cofreService.save(createCofreDTO);

            assertThat(cofre).isNotNull();
        });
    }

    @Test
    @Order(4)
    void update() {
        Assertions.assertDoesNotThrow(() -> {
            var updateCofreDTO = objectMapper.readValue(cofre, CreateCofreDTO.class);
            var cofre = cofreService.update(ID, updateCofreDTO);

            assertThat(cofre).isNotNull();
        });
    }

    @Test
    @Order(5)
    void delete() {
        Assertions.assertDoesNotThrow(() -> {
            cofreService.delete(ID);
        });
    }
}