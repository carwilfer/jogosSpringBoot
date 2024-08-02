package com.infnet.tp1PB.controller;

import com.infnet.tp1PB.model.Compra;
import com.infnet.tp1PB.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping
    public ResponseEntity<?> criarCompra(@RequestBody Compra compra) {
        try {
            Compra novaCompra = compraService.criarCompra(compra);
            return ResponseEntity.ok(novaCompra);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Compra> listarCompras() {
        return compraService.listarCompras();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> encontrarPorId(@PathVariable Long id) {
        Optional<Compra> compra = compraService.encontrarPorId(id);
        return compra.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCompra(@PathVariable Long id) {
        compraService.deletarCompra(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compra> atualizarCompra(@PathVariable Long id, @RequestBody Compra compraAtualizada) {
        Optional<Compra> compra = compraService.encontrarPorId(id);
        if (compra.isPresent()) {
            Compra compraExistente = compra.get();
            compraExistente.setData(compraAtualizada.getData());
            // Atualize outros atributos conforme necessário
            Compra compraAtualizadaSalva = compraService.criarCompra(compraExistente);
            return ResponseEntity.ok(compraAtualizadaSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
