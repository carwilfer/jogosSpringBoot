package com.infnet.tp1PB.service;

import com.infnet.tp1PB.model.Conta;
import com.infnet.tp1PB.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public Conta criarConta(Conta conta) {
        return contaRepository.save(conta);
    }

    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    public Optional<Conta> encontrarPorId(Long id) {
        return contaRepository.findById(id);
    }

    public void deletarConta(Long id) {
        contaRepository.deleteById(id);
    }

    public Conta atualizarConta(Long id, Conta contaAtualizada) {
        Optional<Conta> contaOptional = contaRepository.findById(id);
        if (contaOptional.isPresent()) {
            Conta contaExistente = contaOptional.get();
            contaExistente.setLimiteDisponivel(contaAtualizada.getLimiteDisponivel());
            contaExistente.setAtivo(contaAtualizada.isAtivo());
            contaExistente.setUsuario(contaAtualizada.getUsuario());
            // Atualize outros atributos conforme necessário
            return contaRepository.save(contaExistente);
        } else {
            throw new IllegalArgumentException("Conta não encontrada para o ID: " + id);
        }
    }
}
