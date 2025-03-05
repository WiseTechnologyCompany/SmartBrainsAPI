package br.com.smartbrains.service;

import br.com.smartbrains.config.AbstractTest;
import br.com.smartbrains.model.dto.GeneroDTO;
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
public class GeneroServiceTest extends AbstractTest {

    private String genero;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    GeneroService generoService;

    @BeforeEach
    void setUp() throws IOException {
        if (genero == null) {
            genero = new String(Files.readAllBytes(Paths.get("src/test/resources/genero/genero.json")));
        }
    }

    @Test
    @Order(1)
    void findAll() {
        var generos = generoService.findAll();
        assertThat(generos).isNotEmpty();
    }

    @Test
    @Order(2)
    void findById() {
        int id = 1;
        var generos = generoService.findById(id);

        assertThat(generos).isNotNull();
        assertThat(generos.getId()).isEqualTo(id);
    }

    @Test
    @Order(3)
    void save() {
        Assertions.assertDoesNotThrow(() -> {
            var generoEntity = objectMapper.readValue(genero, GeneroDTO.class);

            var saveGenero = generoService.save(generoEntity);
            var findGenero = generoService.findById(saveGenero.getId());

            assertThat(saveGenero).isNotNull();
            assertThat(findGenero).isNotNull();
            assertThat(findGenero.getId()).isEqualTo(saveGenero.getId());
        });
    }

    @Test
    @Order(3)
    void update() {
        Assertions.assertDoesNotThrow(() -> {
            int id = 1;

            var generoEntity = objectMapper.readValue(genero, GeneroDTO.class);
            var updateGenero = generoService.update(id, generoEntity);
            var findGenero = generoService.findById(id);

            assertThat(updateGenero).isNotNull();
            assertThat(findGenero).isNotNull();
            assertThat(findGenero.getDescricao()).isEqualTo(generoEntity.getDescricao());
        });
    }

    @Test
    @Order(4)
    void delete() {
        int id = 1;

        Assertions.assertDoesNotThrow(() -> {
            generoService.delete(id);
        });
    }
}