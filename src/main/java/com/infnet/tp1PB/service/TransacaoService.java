package com.infnet.tp1PB.service;

import com.infnet.tp1PB.model.Transacao;
import com.infnet.tp1PB.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    public Transacao criarTransacao(Transacao transacao) {
        return transacaoRepository.save(transacao);
    }

    public List<Transacao> listarTransacoes() {
        return transacaoRepository.findAll();
    }

    public Optional<Transacao> encontrarPorId(Long id) {
        return transacaoRepository.findById(id);
    }

    public void deletarTransacao(Long id) {
        transacaoRepository.deleteById(id);
    }

    public Transacao atualizarTransacao(Long id, Transacao transacaoAtualizada) {
        Optional<Transacao> transacaoOptional = transacaoRepository.findById(id);
        if (transacaoOptional.isPresent()) {
            Transacao transacaoExistente = transacaoOptional.get();
            transacaoExistente.setTipo(transacaoAtualizada.getTipo());
            transacaoExistente.setValor(transacaoAtualizada.getValor());
            transacaoExistente.setData(transacaoAtualizada.getData());
            transacaoExistente.setConta(transacaoAtualizada.getConta());
            // Atualize outros atributos conforme necessário
            return transacaoRepository.save(transacaoExistente);
        } else {
            throw new IllegalArgumentException("Transação não encontrada para o ID: " + id);
        }
    }
}
