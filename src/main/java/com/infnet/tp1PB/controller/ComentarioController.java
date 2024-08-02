package com.infnet.tp1PB.controller;

import com.infnet.tp1PB.model.Comentario;
import com.infnet.tp1PB.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @PostMapping
    public Comentario criarComentario(@RequestBody Comentario comentario) {
        return comentarioService.criarComentario(comentario);
    }

    @GetMapping
    public List<Comentario> listarComentario() {
        return comentarioService.listarComentarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> encontrarPorId(@PathVariable Long id) {
        Optional<Comentario> comentario = comentarioService.encontrarPorId(id);
        return comentario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarComentario(@PathVariable Long id) {
        comentarioService.deletarComentario(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> atualizarComentario(@PathVariable Long id, @RequestBody Comentario comentarioAtualizado) {
        Optional<Comentario> comentario = comentarioService.encontrarPorId(id);
        if (comentario.isPresent()) {
            Comentario comentarioExistente = comentario.get();
            comentarioExistente.setTexto(comentarioAtualizado.getTexto());
            // Atualize outros atributos conforme necessário
            Comentario comentarioAtualizadoSalvo = comentarioService.criarComentario(comentarioExistente);
            return ResponseEntity.ok(comentarioAtualizadoSalvo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
