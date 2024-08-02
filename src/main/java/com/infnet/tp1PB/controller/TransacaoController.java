package com.infnet.tp1PB.controller;

import com.infnet.tp1PB.model.Transacao;
import com.infnet.tp1PB.service.TransacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

    @Autowired
    private TransacaoService transacaoService;

    @PostMapping
    public Transacao criarTransacao(@RequestBody Transacao transacao) {
        return transacaoService.criarTransacao(transacao);
    }

    @GetMapping
    public List<Transacao> listarTransacoes() {
        return transacaoService.listarTransacoes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transacao> encontrarPorId(@PathVariable Long id) {
        Optional<Transacao> transacao = transacaoService.encontrarPorId(id);
        return transacao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTransacao(@PathVariable Long id) {
        transacaoService.deletarTransacao(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transacao> atualizarTransacao(@PathVariable Long id, @RequestBody Transacao transacaoAtualizada) {
        Optional<Transacao> transacao = transacaoService.encontrarPorId(id);
        if (transacao.isPresent()) {
            Transacao transacaoExistente = transacao.get();
            // Atualize os atributos da transação conforme necessário
            Transacao transacaoAtualizadaSalva = transacaoService.criarTransacao(transacaoExistente);
            return ResponseEntity.ok(transacaoAtualizadaSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
