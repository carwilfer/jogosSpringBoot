package com.infnet.tp1PB;

import com.infnet.tp1PB.model.Amizade;
import com.infnet.tp1PB.model.Usuario;
import com.infnet.tp1PB.repository.AmizadeRepository;
import com.infnet.tp1PB.service.AmizadeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AmizadeServiceTest {

    @MockBean
    private AmizadeRepository amizadeRepository;

    @Autowired
    private AmizadeService amizadeService;

    @Test
    public void testCriarAmizade() {
        Usuario solicitante = new Usuario(); // configure o objeto conforme necessário
        Usuario solicitado = new Usuario(); // configure o objeto conforme necessário
        Amizade amizade = new Amizade(solicitante, solicitado, "PENDENTE");

        Mockito.when(amizadeRepository.save(amizade)).thenReturn(amizade);

        Amizade novaAmizade = amizadeService.criarAmizade(amizade);

        assertNotNull(novaAmizade);
        assertEquals("PENDENTE", novaAmizade.getStatus());
    }

    @Test
    public void testListarAmizades() {
        List<Amizade> amizades = new ArrayList<>();
        amizades.add(new Amizade(new Usuario(), new Usuario(), "PENDENTE"));

        Mockito.when(amizadeRepository.findAll()).thenReturn(amizades);

        List<Amizade> result = amizadeService.listarAmizades();

        assertEquals(1, result.size());
    }

    @Test
    public void testEncontrarPorId() {
        Long id = 1L;
        Amizade amizade = new Amizade(new Usuario(), new Usuario(), "PENDENTE");

        Mockito.when(amizadeRepository.findById(id)).thenReturn(Optional.of(amizade));

        Optional<Amizade> result = amizadeService.encontrarPorId(id);

        assertTrue(result.isPresent());
        assertEquals("PENDENTE", result.get().getStatus());
    }

    @Test
    public void testDeletarAmizade() {
        Long id = 1L;
        amizadeService.deletarAmizade(id);
        Mockito.verify(amizadeRepository, Mockito.times(1)).deleteById(id);
    }

    @Test
    public void testAtualizarAmizade() {
        Long id = 1L;
        Usuario solicitante = new Usuario(); // configure o objeto conforme necessário
        Usuario solicitado = new Usuario(); // configure o objeto conforme necessário
        Amizade amizade = new Amizade(solicitante, solicitado, "PENDENTE");
        Amizade amizadeAtualizada = new Amizade(solicitante, solicitado, "ACEITO");

        Mockito.when(amizadeRepository.findById(id)).thenReturn(Optional.of(amizade));
        Mockito.when(amizadeRepository.save(amizade)).thenReturn(amizadeAtualizada);

        Amizade result = amizadeService.atualizarAmizade(id, amizadeAtualizada);

        assertEquals("ACEITO", result.getStatus());
    }
}
