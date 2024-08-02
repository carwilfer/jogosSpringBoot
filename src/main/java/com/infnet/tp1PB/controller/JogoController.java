package com.infnet.tp1PB.controller;

import com.infnet.tp1PB.model.Jogo;
import com.infnet.tp1PB.service.JogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jogos")
public class JogoController {

    @Autowired
    private JogoService jogoService;

    @PostMapping
    public Jogo criarJogo(@RequestBody Jogo jogo) {
        return jogoService.criarJogo(jogo);
    }

    @GetMapping
    public List<Jogo> listarJogos() {
        return jogoService.listarJogos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogo> encontrarPorId(@PathVariable Long id) {
        Optional<Jogo> jogo = jogoService.encontrarPorId(id);
        return jogo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarJogo(@PathVariable Long id) {
        jogoService.deletarJogo(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogo> atualizarJogo(@PathVariable Long id, @RequestBody Jogo jogo) {
        Optional<Jogo> jogoAtualizado = jogoService.atualizarJogo(id, jogo);
        return jogoAtualizado.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
