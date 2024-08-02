package com.infnet.tp1PB.service;

import com.infnet.tp1PB.model.Amizade;
import com.infnet.tp1PB.repository.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AmizadeService {

    @Autowired
    private AmizadeRepository amizadeRepository;

    public Amizade criarAmizade(Amizade amizade) {
        return amizadeRepository.save(amizade);
    }

    public List<Amizade> listarAmizades() {
        return amizadeRepository.findAll();
    }

    public Optional<Amizade> encontrarPorId(Long id) {
        return amizadeRepository.findById(id);
    }

    public void deletarAmizade(Long id) {
        amizadeRepository.deleteById(id);
    }

    public Amizade atualizarAmizade(Long id, Amizade amizadeAtualizada) {
        Optional<Amizade> optionalAmizade = amizadeRepository.findById(id);
        if (optionalAmizade.isPresent()) {
            Amizade amizade = optionalAmizade.get();
            // Atualiza os campos necessários da amizade com os dados da amizadeAtualizada
            amizade.setSolicitante(amizadeAtualizada.getSolicitante());
            amizade.setSolicitado(amizadeAtualizada.getSolicitado());
            // Pode-se adicionar mais campos conforme necessário
            return amizadeRepository.save(amizade);
        } else {
            throw new IllegalArgumentException("Amizade não encontrada para o ID: " + id);
        }
    }
}
