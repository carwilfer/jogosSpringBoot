package com.infnet.tp1PB.controller;

import com.infnet.tp1PB.model.Denuncia;
import com.infnet.tp1PB.service.DenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

    @Autowired
    private DenunciaService denunciaService;

    @PostMapping
    public Denuncia criarDenuncia(@RequestBody Denuncia denuncia) {
        return denunciaService.criarDenuncia(denuncia);
    }

    @GetMapping
    public List<Denuncia> listarDenuncias() {
        return denunciaService.listarDenuncias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Denuncia> encontrarPorId(@PathVariable Long id) {
        Optional<Denuncia> denuncia = denunciaService.encontrarPorId(id);
        return denuncia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDenuncia(@PathVariable Long id) {
        denunciaService.deletarDenuncia(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Denuncia> atualizarDenuncia(@PathVariable Long id, @RequestBody Denuncia denunciaAtualizada) {
        Optional<Denuncia> denuncia = denunciaService.encontrarPorId(id);
        if (denuncia.isPresent()) {
            Denuncia denunciaExistente = denuncia.get();
            denunciaExistente.setDescricao(denunciaAtualizada.getDescricao());
            // Atualize outros atributos conforme necessário
            Denuncia denunciaAtualizadaSalva = denunciaService.criarDenuncia(denunciaExistente);
            return ResponseEntity.ok(denunciaAtualizadaSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
