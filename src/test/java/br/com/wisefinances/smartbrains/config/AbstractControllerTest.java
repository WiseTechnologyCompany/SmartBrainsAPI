package br.com.wisefinances.smartbrains.config;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Import(value = {TestDataBaseConfig.class, TestSecurityConfig.class})
public abstract class AbstractControllerTest {

    @Autowired
    MockMvc mockMvc;

    protected void testGet(String url) throws Exception {
        mockMvc.perform(get(url))
                .andExpect(status().isOk());
    }

    protected void testPost(String url, String requestBody) throws Exception {
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());
    }

    protected void testPostStatusOk(String url, String requestBody) throws Exception {
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    protected void testPatch(String url, String requestBody) throws Exception {
        mockMvc.perform(patch(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
    }

    protected void testDelete(String url) throws Exception {
        mockMvc.perform(delete(url))
                .andExpect(status().isOk());
    }
}