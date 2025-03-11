package br.com.wisefinances.smartbrains.repository;

import br.com.wisefinances.smartbrains.config.AbstractTest;
import br.com.wisefinances.smartbrains.model.modify.entity.ModifyUsuario;
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
public class UsuarioRepositoryTest extends AbstractTest {

    private String usuario;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ModifyUsuarioRepository modifyUsuarioRepository;

    @BeforeEach
    void setUp() throws IOException {
        if (usuario == null) {
            usuario = new String(Files.readAllBytes(Paths.get("src/test/resources/usuario/usuario.json")));
        }
    }

    @Test
    @Order(1)
    void findAll() {
        var usuario = usuarioRepository.findAll();
        assertThat(usuario).isNotEmpty();
    }

    @Test
    @Order(2)
    void findById() {
        int id = 1;
        var usuario = usuarioRepository.getReferenceById(id);

        assertThat(usuario).isNotNull();
        assertThat(usuario.getId()).isEqualTo(id);
    }

    @Test
    @Order(3)
    void save() {
        Assertions.assertDoesNotThrow(() -> {
            var usuarioEntity = objectMapper.readValue(usuario, ModifyUsuario.class);
            var savedUsuario = modifyUsuarioRepository.save(usuarioEntity);

            assertThat(usuarioEntity).isNotNull();
            assertThat(savedUsuario).isNotNull();
            assertThat(savedUsuario.getEmail()).isEqualToIgnoringCase(usuarioEntity.getEmail());
        });
    }

    @Test
    @Order(4)
    void findByIdException() {
        int id = 999;
        var usuario = usuarioRepository.findById(id);

        assertThrows(EntityNotFoundException.class, () -> usuario.orElseThrow(EntityNotFoundException::new));
    }
}