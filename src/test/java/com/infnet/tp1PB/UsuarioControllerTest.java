package com.infnet.tp1PB;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infnet.tp1PB.controller.UsuarioController;
import com.infnet.tp1PB.model.Usuario;
import com.infnet.tp1PB.repository.UsuarioRepository;
import com.infnet.tp1PB.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Locale;

import static javax.swing.UIManager.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
@AutoConfigureMockMvc
public class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @MockBean
    private UsuarioService usuarioService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testCriarUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setCpf("12345678901");
        usuario.setEmail("email@exemplo.com");
        usuario.setNome("Nome");
        usuario.setSaldo(100.0);
        usuario.setSenha("senha");

        mockMvc.perform(post("/usuarios/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk());
    }

    @Test
    public void testListarUsuarios() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios"))
                .andExpect(status().isOk());
    }

    @Test
    public void testEncontrarPorId() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Nome Teste");
        usuario.setCpf("12345678900");
        usuario.setEmail("teste@teste.com");
        usuario.setSenha("senha123");
        Usuario criado = usuarioRepository.save(usuario);

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/{id}", criado.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeletarUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Nome Teste");
        usuario.setCpf("12345678900");
        usuario.setEmail("teste@teste.com");
        usuario.setSenha("senha123");
        Usuario criado = usuarioRepository.save(usuario);

        mockMvc.perform(MockMvcRequestBuilders.delete("/usuarios/{id}", criado.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testAtualizarUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Nome Teste");
        usuario.setCpf("12345678900");
        usuario.setEmail("teste@teste.com");
        usuario.setSenha("senha123");
        Usuario criado = usuarioRepository.save(usuario);

        Usuario atualizado = new Usuario();
        atualizado.setNome("Nome Atualizado");
        atualizado.setCpf("12345678900");
        atualizado.setEmail("atualizado@teste.com");
        atualizado.setSenha("novaSenha123");

        mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/{id}", criado.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(atualizado)))
                .andExpect(status().isOk());
    }
}