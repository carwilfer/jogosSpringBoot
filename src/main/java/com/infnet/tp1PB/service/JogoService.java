package com.infnet.tp1PB.service;

import com.infnet.tp1PB.model.Jogo;
import com.infnet.tp1PB.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;

    public Jogo criarJogo(Jogo jogo) {
        return jogoRepository.save(jogo);
    }

    public List<Jogo> listarJogos() {
        return jogoRepository.findAll();
    }

    public Optional<Jogo> encontrarPorId(Long id) {
        return jogoRepository.findById(id);
    }

    public void deletarJogo(Long id) {
        jogoRepository.deleteById(id);
    }

    public Optional<Jogo> atualizarJogo(Long id, Jogo jogoAtualizado) {
        Optional<Jogo> jogoOptional = jogoRepository.findById(id);
        if (jogoOptional.isPresent()) {
            Jogo jogoExistente = jogoOptional.get();
            jogoExistente.setNome(jogoAtualizado.getNome());
            jogoExistente.setDescricao(jogoAtualizado.getDescricao());
            jogoExistente.setPreco(jogoAtualizado.getPreco());
            jogoExistente.setEmpresa(jogoAtualizado.getEmpresa());
            // Atualize outros atributos conforme necessário
            Optional<Jogo> jogoOptional2=(Optional.of(jogoRepository.save(jogoExistente)));
            return jogoOptional2;
        } else {
            throw new IllegalArgumentException("Jogo não encontrado para o ID: " + id);
        }
    }
}
