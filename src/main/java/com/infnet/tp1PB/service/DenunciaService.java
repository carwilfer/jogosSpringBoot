package com.infnet.tp1PB.service;

import com.infnet.tp1PB.model.Denuncia;
import com.infnet.tp1PB.repository.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    public Denuncia criarDenuncia(Denuncia denuncia) {
        return denunciaRepository.save(denuncia);
    }

    public List<Denuncia> listarDenuncias() {
        return denunciaRepository.findAll();
    }

    public Optional<Denuncia> encontrarPorId(Long id) {
        return denunciaRepository.findById(id);
    }

    public void deletarDenuncia(Long id) {
        denunciaRepository.deleteById(id);
    }

    public Denuncia atualizarDenuncia(Long id, Denuncia denunciaAtualizada) {
        Optional<Denuncia> denunciaOptional = denunciaRepository.findById(id);
        if (denunciaOptional.isPresent()) {
            Denuncia denunciaExistente = denunciaOptional.get();
            denunciaExistente.setDescricao(denunciaAtualizada.getDescricao());
            denunciaExistente.setMotivo(denunciaAtualizada.getMotivo());
            denunciaExistente.setDenunciante(denunciaAtualizada.getDenunciante());
            denunciaExistente.setDenunciado(denunciaAtualizada.getDenunciado());
            denunciaExistente.setJogo(denunciaAtualizada.getJogo());
            // Atualize outros atributos conforme necessário
            return denunciaRepository.save(denunciaExistente);
        } else {
            throw new IllegalArgumentException("Denúncia não encontrada para o ID: " + id);
        }
    }
}
