package com.infnet.tp1PB.service;

import com.infnet.tp1PB.model.Avaliacao;
import com.infnet.tp1PB.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Avaliacao criarAvaliacao(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    public List<Avaliacao> listarAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    public Optional<Avaliacao> encontrarPorId(Long id) {
        return avaliacaoRepository.findById(id);
    }

    public void deletarAvaliacao(Long id) {
        avaliacaoRepository.deleteById(id);
    }

    public Avaliacao atualizarAvaliacao(Long id, Avaliacao avaliacaoAtualizada) {
        Optional<Avaliacao> avaliacaoOptional = avaliacaoRepository.findById(id);
        if (avaliacaoOptional.isPresent()) {
            Avaliacao avaliacaoExistente = avaliacaoOptional.get();
            avaliacaoExistente.setJogo(avaliacaoAtualizada.getJogo());
            avaliacaoExistente.setUsuario(avaliacaoAtualizada.getUsuario());
            avaliacaoExistente.setNota(avaliacaoAtualizada.getNota());
            avaliacaoExistente.setComentario(avaliacaoAtualizada.getComentario());
            // Atualize outros atributos conforme necessário
            return avaliacaoRepository.save(avaliacaoExistente);
        } else {
            throw new IllegalArgumentException("Avaliação não encontrada para o ID: " + id);
        }
    }
}
