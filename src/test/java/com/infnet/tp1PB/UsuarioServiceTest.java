package com.infnet.tp1PB;

import com.infnet.tp1PB.model.Usuario;
import com.infnet.tp1PB.repository.UsuarioRepository;
import com.infnet.tp1PB.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UsuarioServiceTest {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setup() {
        usuarioRepository.deleteAll();
    }

    @Test
    public void testCriarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Nome Teste");
        usuario.setCpf("12345678900");
        usuario.setEmail("teste@teste.com");
        usuario.setSenha("senha123");

        Usuario criado = usuarioService.criarUsuario(usuario);
        assertNotNull(criado.getId());
    }

    @Test
    public void testListarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        assertTrue(usuarios.isEmpty());

        Usuario usuario = new Usuario();
        usuario.setNome("Nome Teste");
        usuario.setCpf("12345678900");
        usuario.setEmail("teste@teste.com");
        usuario.setSenha("senha123");
        usuarioService.criarUsuario(usuario);

        usuarios = usuarioService.listarUsuarios();
        assertFalse(usuarios.isEmpty());
    }

    @Test
    public void testEncontrarPorId() {
        Usuario usuario = new Usuario();
        usuario.setNome("Nome Teste");
        usuario.setCpf("12345678900");
        usuario.setEmail("teste@teste.com");
        usuario.setSenha("senha123");
        Usuario criado = usuarioService.criarUsuario(usuario);

        Optional<Usuario> encontrado = usuarioService.encontrarPorId(criado.getId());
        assertTrue(encontrado.isPresent());
    }

    @Test
    public void testDeletarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Nome Teste");
        usuario.setCpf("12345678900");
        usuario.setEmail("teste@teste.com");
        usuario.setSenha("senha123");
        Usuario criado = usuarioService.criarUsuario(usuario);

        usuarioService.deletarUsuario(criado.getId());

        Optional<Usuario> encontrado = usuarioService.encontrarPorId(criado.getId());
        assertFalse(encontrado.isPresent());
    }

    @Test
    public void testAtualizarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Nome Teste");
        usuario.setCpf("12345678900");
        usuario.setEmail("teste@teste.com");
        usuario.setSenha("senha123");
        Usuario criado = usuarioService.criarUsuario(usuario);

        Usuario atualizado = new Usuario();
        atualizado.setNome("Nome Atualizado");
        atualizado.setCpf("12345678900");
        atualizado.setEmail("atualizado@teste.com");
        atualizado.setSenha("novaSenha123");

        Usuario resultado = usuarioService.atualizarUsuario(criado.getId(), atualizado);
        assertEquals("Nome Atualizado", resultado.getNome());
    }
}
