package com.infnet.tp1PB.service;

import com.infnet.tp1PB.model.Compra;
import com.infnet.tp1PB.model.Jogo;
import com.infnet.tp1PB.model.Usuario;
import com.infnet.tp1PB.repository.CompraRepository;
import com.infnet.tp1PB.repository.JogoRepository;
import com.infnet.tp1PB.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JogoRepository jogoRepository;

    public Compra criarCompra(Compra compra) {
        Usuario usuario = compra.getUsuario();
        Jogo jogo = compra.getJogo();

        if (usuario.getSaldo() >= jogo.getPreco()) {
            usuario.setSaldo(usuario.getSaldo() - jogo.getPreco());
            compra.setData(LocalDateTime.now());
            return compraRepository.save(compra);
        } else {
            throw new IllegalArgumentException("Saldo insuficiente para realizar a compra.");
        }
    }

    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }

    public Optional<Compra> encontrarPorId(Long id) {
        return compraRepository.findById(id);
    }

    public void deletarCompra(Long id) {
        compraRepository.deleteById(id);
    }

    public Compra atualizarCompra(Long id, Compra compraAtualizada) {
        Optional<Compra> compraOptional = compraRepository.findById(id);
        if (compraOptional.isPresent()) {
            Compra compraExistente = compraOptional.get();
            compraExistente.setData(compraAtualizada.getData());
            compraExistente.setUsuario(compraAtualizada.getUsuario());
            compraExistente.setJogo(compraAtualizada.getJogo());
            // Atualize outros atributos conforme necessário
            return compraRepository.save(compraExistente);
        } else {
            throw new IllegalArgumentException("Compra não encontrada para o ID: " + id);
        }
    }
}
