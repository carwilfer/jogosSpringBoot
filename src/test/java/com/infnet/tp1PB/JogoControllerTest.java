package com.infnet.tp1PB;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.tp1PB.model.Jogo;
import com.infnet.tp1PB.service.JogoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class) // Integração com o Spring para JUnit 5
@SpringBootTest
@AutoConfigureMockMvc // Configuração automática do MockMvc
public class JogoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCriarJogo() throws Exception {
        // Dados do jogo para teste
        Jogo jogo = new Jogo();
        jogo.setId(1L);
        jogo.setNome("Teste Jogo");
        jogo.setDescricao("Descrição do jogo de teste");
        jogo.setPreco(29.99);

        // Requisição POST para criar um jogo
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/jogos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(jogo)))
                .andExpect(status().isOk())
                .andReturn();

        // Verificações adicionais, se necessário
    }
}
