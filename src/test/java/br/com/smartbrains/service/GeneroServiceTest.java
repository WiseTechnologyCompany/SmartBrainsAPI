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

    private final Integer ID = 1;

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
            var generoDTO = objectMapper.readValue(genero, GeneroDTO.class);
            var genero = generoService.save(generoDTO);

            assertThat(generoDTO).isNotNull();
            assertThat(genero).isNotNull();
            assertThat(genero.getDescricao()).isEqualToIgnoringCase(generoDTO.getDescricao());
        });
    }

    @Test
    @Order(4)
    void update() {
        Assertions.assertDoesNotThrow(() -> {
            var generoDTO = objectMapper.readValue(genero, GeneroDTO.class);

            generoService.update(ID, generoDTO);
            var findGenero = generoService.findById(ID);

            assertThat(findGenero.getDescricao()).isEqualToIgnoringCase(generoDTO.getDescricao());
        });
    }

    @Test
    @Order(5)
    void delete() {
        Assertions.assertDoesNotThrow(() -> {
            generoService.delete(ID);
        });
    }
}