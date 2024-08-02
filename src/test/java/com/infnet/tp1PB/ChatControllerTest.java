package com.infnet.tp1PB;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.tp1PB.controller.ChatController;
import com.infnet.tp1PB.model.Chat;
import com.infnet.tp1PB.model.Usuario;
import com.infnet.tp1PB.repository.ChatRepository;
import com.infnet.tp1PB.service.ChatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ChatController.class)
@AutoConfigureMockMvc
public class ChatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatService chatService;

    @MockBean
    private ChatRepository chatRepository;

    private Chat mockChat;

    @BeforeEach
    void setUp() {
        // Criando um chat de exemplo
        mockChat = new Chat();
        mockChat.setId(1L);
        mockChat.setRemetente(new Usuario(1L, "Remetente"));
        mockChat.setDestinatario(new Usuario(2L, "Destinatário"));
        mockChat.setMensagem("Olá, tudo bem?");
        mockChat.setTimestamp(LocalDateTime.now());
    }

    @Test
    public void testCriarChat() throws Exception {
        // Simula a criação de um chat
        when(chatService.criarChat(any(Chat.class))).thenReturn(mockChat);

        // Requisição POST para criar um chat
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/chats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mockChat)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$.id").value(1)) // Verifica se o ID retornado é 1
                .andReturn();

        // Verificações adicionais, se necessário
    }

    @Test
    public void testListarChats() throws Exception {
        // Simula a listagem de chats
        when(chatService.listarChats()).thenReturn(Collections.singletonList(mockChat));

        // Requisição GET para listar chats
        mockMvc.perform(MockMvcRequestBuilders.get("/chats"))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$[0].id").value(1)) // Verifica se o primeiro chat tem ID 1
                .andExpect((ResultMatcher) jsonPath("$[0].mensagem").value("Olá, tudo bem?"));
    }

    @Test
    public void testEncontrarPorId() throws Exception {
        // Simula a busca por ID
        when(chatService.encontrarPorId(1L)).thenReturn(Optional.of(mockChat));

        // Requisição GET para buscar por ID
        mockMvc.perform(MockMvcRequestBuilders.get("/chats/{id}", 1))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$.id").value(1)) // Verifica se o ID retornado é 1
                .andExpect((ResultMatcher) jsonPath("$.mensagem").value("Olá, tudo bem?"));
    }

    @Test
    public void testDeletarChat() throws Exception {
        // Requisição DELETE para deletar chat
        mockMvc.perform(MockMvcRequestBuilders.delete("/chats/{id}", 1))
                .andExpect(status().isNoContent()); // Verifica se a resposta é sem conteúdo (204)
    }

    @Test
    public void testAtualizarChat() throws Exception {
        // Simula a atualização de um chat
        when(chatService.atualizarChat(1L, mockChat)).thenReturn(mockChat);

        // Requisição PUT para atualizar um chat
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/chats/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(mockChat)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) content().contentType(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) jsonPath("$.id").value(1)) // Verifica se o ID retornado é 1
                .andExpect((ResultMatcher) jsonPath("$.mensagem").value("Olá, tudo bem?"))
                .andReturn();

    }
}

