package br.com.wisefinances.smartbrains.controller;

import br.com.wisefinances.smartbrains.config.AbstractControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
class UsuarioControllerTest extends AbstractControllerTest {

    private String email;

    private String usuario;

    @BeforeEach
    void setUp() throws IOException {
        if (usuario == null) {
            usuario = new String(Files.readAllBytes(Paths.get("src/test/resources/usuario/usuario.json")));
        }
        if (email == null) {
            email = new String(Files.readAllBytes(Paths.get("src/test/resources/usuario/UsuarioEmail.json")));
        }
    }

    @Test
    @Order(1)
    void getAll() throws Exception {
        testGet("/v1/usuarios");
    }

    @Test
    @Order(2)
    void getUsuarioById() throws Exception {
        testGet("/v1/usuarios/1");
    }

    @Test
    @Order(3)
    void getUsuarioInfoByEmail() throws Exception {
        testPostStatusOk("/v1/usuarios/info", email);
    }

    @Test
    @Order(4)
    void saveUsuario() throws Exception {
        testPost("/v1/usuarios", usuario);
    }

    @Test
    @Order(5)
    void updateUsuario() throws Exception {
        testPatch("/v1/usuarios/1", usuario);
    }

    @Test
    @Order(6)
    void deleteUsuario() throws Exception {
        testDelete("/v1/usuarios/1");
    }
}