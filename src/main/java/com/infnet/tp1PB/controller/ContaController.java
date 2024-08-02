package com.infnet.tp1PB.controller;

import com.infnet.tp1PB.model.Conta;
import com.infnet.tp1PB.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    public Conta criarConta(@RequestBody Conta conta) {
        return contaService.criarConta(conta);
    }

    @GetMapping
    public List<Conta> listarContas() {
        return contaService.listarContas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> encontrarPorId(@PathVariable Long id) {
        Optional<Conta> conta = contaService.encontrarPorId(id);
        return conta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConta(@PathVariable Long id) {
        contaService.deletarConta(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Conta> atualizarConta(@PathVariable Long id, @RequestBody Conta contaAtualizada) {
        Optional<Conta> conta = contaService.encontrarPorId(id);
        if (conta.isPresent()) {
            Conta contaExistente = conta.get();
            contaExistente.setLimiteDisponivel(contaAtualizada.getLimiteDisponivel());
            contaExistente.setAtivo(contaAtualizada.isAtivo());
            // Atualize outros atributos conforme necessário
            Conta contaAtualizadaSalva = contaService.criarConta(contaExistente);
            return ResponseEntity.ok(contaAtualizadaSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
