package br.com.wisefinances.smartbrains.service.auth;

import br.com.wisefinances.smartbrains.config.AbstractTest;
import br.com.wisefinances.smartbrains.infra.security.model.dto.AutenticacaoDTO;
import br.com.wisefinances.smartbrains.infra.security.service.TokenService;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TokenServiceTest extends AbstractTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    TokenService tokenService;

    private String authentication;

    @BeforeEach
    void setUp() throws IOException {
        if (authentication == null) {
            authentication = new String(Files.readAllBytes(Paths.get("src/test/resources/autenticacao/getToken/tokenAutenticacao.json")));
        }
    }

    @Test
    @Order(1)
    void generateToken() {
        Assertions.assertDoesNotThrow(() -> {
            var autenticacaoDTO = objectMapper.readValue(authentication, AutenticacaoDTO.class);
            String token = tokenService.generateToken(autenticacaoDTO);
            Assertions.assertNotNull(token);
        });
    }

    @Test
    @Order(2)
    void getSubject() {
        Assertions.assertDoesNotThrow(() -> {
            String token = sendAuthRequest();
            String subject = tokenService.getSubject(token);
            Assertions.assertNotNull(subject);
        });
    }

    @Test
    @Order(3)
    void getSubjectWithExcetion() {
        Assertions.assertThrows(JWTVerificationException.class, () -> tokenService.getSubject("iOiJ2I43zI.1NiI7sI.Yg9fwqk"));
    }

    String sendAuthRequest() throws Exception {
        return mockMvc.perform(post("/v1/auth")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authentication))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString()
                .substring(17, 189);
    }
}