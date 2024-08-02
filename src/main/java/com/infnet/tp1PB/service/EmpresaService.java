package com.infnet.tp1PB.service;

import com.infnet.tp1PB.model.Empresa;
import com.infnet.tp1PB.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public Empresa criarEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> encontrarPorId(Long id) {
        return empresaRepository.findById(id);
    }

    public void deletarEmpresa(Long id) {
        empresaRepository.deleteById(id);
    }

    public Empresa atualizarEmpresa(Long id, Empresa empresaAtualizada) {
        Optional<Empresa> empresaOptional = empresaRepository.findById(id);
        if (empresaOptional.isPresent()) {
            Empresa empresaExistente = empresaOptional.get();
            empresaExistente.setNome(empresaAtualizada.getNome());
            empresaExistente.setDescricao(empresaAtualizada.getDescricao());
            // Atualize outros atributos conforme necessário
            return empresaRepository.save(empresaExistente);
        } else {
            throw new IllegalArgumentException("Empresa não encontrada para o ID: " + id);
        }
    }
}
