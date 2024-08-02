package com.infnet.tp1PB.service;

import com.infnet.tp1PB.model.Comentario;
import com.infnet.tp1PB.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public Comentario criarComentario(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public List<Comentario> listarComentarios() {
        return comentarioRepository.findAll();
    }

    public Optional<Comentario> encontrarPorId(Long id) {
        return comentarioRepository.findById(id);
    }

    public void deletarComentario(Long id) {
        comentarioRepository.deleteById(id);
    }

    public Comentario atualizarComentario(Long id, Comentario comentarioAtualizado) {
        Optional<Comentario> comentarioOptional = comentarioRepository.findById(id);
        if (comentarioOptional.isPresent()) {
            Comentario comentarioExistente = comentarioOptional.get();
            comentarioExistente.setTexto(comentarioAtualizado.getTexto());
            comentarioExistente.setUsuario(comentarioAtualizado.getUsuario());
            comentarioExistente.setJogo(comentarioAtualizado.getJogo());
            // Atualize outros atributos conforme necessário
            return comentarioRepository.save(comentarioExistente);
        } else {
            throw new IllegalArgumentException("Comentário não encontrado para o ID: " + id);
        }
    }
}
