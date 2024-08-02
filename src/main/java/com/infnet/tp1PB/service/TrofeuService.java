package com.infnet.tp1PB.service;

import com.infnet.tp1PB.model.Trofeu;
import com.infnet.tp1PB.repository.TrofeuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrofeuService {

    @Autowired
    private TrofeuRepository trofeuRepository;

    public Trofeu criarTrofeu(Trofeu trofeu) {
        return trofeuRepository.save(trofeu);
    }

    public List<Trofeu> listarTrofeus() {
        return trofeuRepository.findAll();
    }

    public Optional<Trofeu> encontrarPorId(Long id) {
        return trofeuRepository.findById(id);
    }

    public void deletarTrofeu(Long id) {
        trofeuRepository.deleteById(id);
    }

    public Trofeu atualizarTrofeu(Long id, Trofeu trofeuAtualizado) {
        Optional<Trofeu> trofeuOptional = trofeuRepository.findById(id);
        if (trofeuOptional.isPresent()) {
            Trofeu trofeuExistente = trofeuOptional.get();
            trofeuExistente.setDescricao(trofeuAtualizado.getDescricao());
            trofeuExistente.setData(trofeuAtualizado.getData());
            trofeuExistente.setUsuario(trofeuAtualizado.getUsuario());
            // Atualize outros atributos conforme necessário
            return trofeuRepository.save(trofeuExistente);
        } else {
            throw new IllegalArgumentException("Trofeu não encontrado para o ID: " + id);
        }
    }
}
