package br.com.wisefinances.smartbrains.controller.auth;

import br.com.wisefinances.smartbrains.config.AbstractControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest
public class AutenticacaoControllerTest extends AbstractControllerTest {

    private String saveAuth;

    private String getToken;

    @BeforeEach
    void setUp() throws IOException {
        if (getToken == null) {
            getToken = new String(Files.readAllBytes(Paths.get("src/test/resources/autenticacao/getToken/tokenAutenticacao.json")));
        }

        if (saveAuth == null) {
            saveAuth = new String(Files.readAllBytes(Paths.get("src/test/resources/autenticacao/saveAuth/saveAutenticacao.json")));
        }
    }

    @Test
    @Order(1)
    void saveAuth() throws Exception {
        testPost("/v1/auth/save", saveAuth);
    }

    @Test
    @Order(2)
    void getToken() throws Exception {
        testPost("/v1/auth", getToken);
    }
}