package br.com.wisefinances.smartbrains.service.auth;

import br.com.wisefinances.smartbrains.config.AbstractTest;
import br.com.wisefinances.smartbrains.infra.security.model.dto.AutenticacaoDTO;
import br.com.wisefinances.smartbrains.infra.security.service.AutenticacaoService;
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

@SpringBootTest
public class AutenticacaoServiceTest extends AbstractTest {

    private String saveAuth;

    private String invalidAuth;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AutenticacaoService autenticacaoService;

    @BeforeEach
    void setUp() throws IOException {
        if (invalidAuth == null) {
            invalidAuth = new String(Files.readAllBytes(Paths.get("src/test/resources/autenticacao/getToken/tokenAutenticacao.json")));
        }

        if (saveAuth == null) {
            saveAuth = new String(Files.readAllBytes(Paths.get("src/test/resources/autenticacao/saveAuth/saveAutenticacao.json")));
        }
    }

    @Test
    @Order(1)
    void saveAuthentication() {
        Assertions.assertDoesNotThrow(() -> {
            var autenticacaoDTO = objectMapper.readValue(saveAuth, AutenticacaoDTO.class);
            autenticacaoService.saveAuthentication(autenticacaoDTO);
        });
    }

    @Test
    @Order(2)
    void saveAuthenticationWithExistingEmail() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            var autenticacaoDTO = objectMapper.readValue(invalidAuth, AutenticacaoDTO.class);
            autenticacaoService.saveAuthentication(autenticacaoDTO);
        });
    }
}