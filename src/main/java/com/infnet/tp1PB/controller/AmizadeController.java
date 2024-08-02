package com.infnet.tp1PB.controller;

import com.infnet.tp1PB.model.Amizade;
import com.infnet.tp1PB.service.AmizadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/amizades")
public class AmizadeController {

    @Autowired
    private AmizadeService amizadeService;

    @PostMapping
    public ResponseEntity<Amizade> criarAmizade(@RequestBody Amizade amizade) {
        Amizade novaAmizade = amizadeService.criarAmizade(amizade);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaAmizade);
    }

    @GetMapping
    public List<Amizade> listarAmizades() {
        return amizadeService.listarAmizades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Amizade> encontrarPorId(@PathVariable Long id) {
        Optional<Amizade> amizade = amizadeService.encontrarPorId(id);
        return amizade.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAmizade(@PathVariable Long id) {
        amizadeService.deletarAmizade(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Amizade> atualizarAmizade(@PathVariable Long id, @RequestBody Amizade amizadeAtualizada) {
        try {
            Amizade amizade = amizadeService.atualizarAmizade(id, amizadeAtualizada);
            return ResponseEntity.ok(amizade);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
