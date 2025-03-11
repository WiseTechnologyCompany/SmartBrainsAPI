package br.com.wisefinances.smartbrains.service;

import br.com.wisefinances.smartbrains.config.AbstractTest;
import br.com.wisefinances.smartbrains.model.modify.dto.ModifyUsuarioDTO;
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
public class UsuarioServiceTest extends AbstractTest {

    private String usuario;

    private final Integer ID = 1;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UsuarioService usuarioService;

    @BeforeEach
    void setUp() throws IOException {
        if (usuario == null) {
            usuario = new String(Files.readAllBytes(Paths.get("src/test/resources/usuario/usuario.json")));
        }
    }

    @Test
    @Order(1)
    void findAll() {
        var usuario = usuarioService.findAll(Pageable.unpaged());
        assertThat(usuario).isNotEmpty();
    }

    @Test
    @Order(2)
    void findById() {
        var usuario = usuarioService.findById(ID);

        assertThat(usuario).isNotNull();
        assertThat(usuario.getId()).isEqualTo(ID);
    }

    @Test
    @Order(3)
    void save() {
        Assertions.assertDoesNotThrow(() -> {
            var modifyUsuarioDTO = objectMapper.readValue(usuario, ModifyUsuarioDTO.class);
            var usuario = usuarioService.save(modifyUsuarioDTO);

            assertThat(usuario).isNotNull();
        });
    }

    @Test
    @Order(4)
    void update() {
        Assertions.assertDoesNotThrow(() -> {
            var modifyUsuarioDTO = objectMapper.readValue(usuario, ModifyUsuarioDTO.class);
            var usuario = usuarioService.update(ID, modifyUsuarioDTO);

            assertThat(usuario).isNotNull();
        });
    }

    @Test
    @Order(5)
    void delete() {
        Assertions.assertDoesNotThrow(() -> {
            usuarioService.delete(ID);
            var usuario = usuarioService.findById(ID);

            assertThat(usuario.getSituacaoCadastro()).isEqualToIgnoringCase("EXCLUIDO");
        });
    }
}