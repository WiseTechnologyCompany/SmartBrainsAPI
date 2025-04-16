package br.com.wisefinances.smartbrains.repository;

import br.com.wisefinances.smartbrains.config.AbstractTest;
import br.com.wisefinances.smartbrains.model.entity.genero.Genero;
import br.com.wisefinances.smartbrains.repository.genero.GeneroRepository;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GeneroRepositoryTest extends AbstractTest {

    private String genero;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    GeneroRepository generoRepository;

    @BeforeEach
    void setUp() throws IOException {
        if (genero == null) {
            genero = new String(Files.readAllBytes(Paths.get("src/test/resources/genero/genero.json")));
        }
    }

    @Test
    @Order(1)
    void findAll() {
        var generos = generoRepository.findAll();
        assertThat(generos).isNotEmpty();
    }

    @Test
    @Order(2)
    void findById() {
        int id = 1;
        var genero = generoRepository.getReferenceById(id);

        assertThat(genero).isNotNull();
        assertThat(genero.getId()).isEqualTo(id);
    }

    @Test
    @Order(3)
    void save() {
        Assertions.assertDoesNotThrow(() -> {
            var generoEntity = objectMapper.readValue(genero, Genero.class);
            var savedGenero = generoRepository.save(generoEntity);

            assertThat(generoEntity).isNotNull();
            assertThat(savedGenero).isNotNull();
            assertThat(savedGenero.getDescricao()).isEqualToIgnoringCase(generoEntity.getDescricao());
        });
    }

    @Test
    @Order(4)
    void findByIdException() {
        int id = 999;
        var genero = generoRepository.findById(id);

        assertThrows(EntityNotFoundException.class, () -> genero.orElseThrow(EntityNotFoundException::new));
    }
}