package com.infnet.tp1PB.controller;

import com.infnet.tp1PB.model.Empresa;
import com.infnet.tp1PB.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping
    public Empresa criarEmpresa(@RequestBody Empresa empresa) {
        return empresaService.criarEmpresa(empresa);
    }

    @GetMapping
    public List<Empresa> listarEmpresas() {
        return empresaService.listarEmpresas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> encontrarPorId(@PathVariable Long id) {
        Optional<Empresa> empresa = empresaService.encontrarPorId(id);
        return empresa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable Long id) {
        empresaService.deletarEmpresa(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> atualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresaAtualizada) {
        Optional<Empresa> empresa = empresaService.encontrarPorId(id);
        if (empresa.isPresent()) {
            Empresa empresaExistente = empresa.get();
            empresaExistente.setNome(empresaAtualizada.getNome());
            empresaExistente.setDescricao(empresaAtualizada.getDescricao());
            // Atualize outros atributos conforme necessário
            Empresa empresaAtualizadaSalva = empresaService.criarEmpresa(empresaExistente);
            return ResponseEntity.ok(empresaAtualizadaSalva);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
