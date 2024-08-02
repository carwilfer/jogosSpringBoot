package com.infnet.tp1PB;

import com.infnet.tp1PB.model.Avaliacao;
import com.infnet.tp1PB.model.Jogo;
import com.infnet.tp1PB.model.Usuario;
import com.infnet.tp1PB.repository.AvaliacaoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AvaliacaoRepositoryTest {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testSalvarAvaliacao() {
        Jogo jogo = criarJogo("God of War", "Jogo de ação e aventura", 199.99);
        Usuario usuario = criarUsuario("Maria", "222.222.222-22", "maria@teste.com", "senha456");

        entityManager.persist(jogo);
        entityManager.persist(usuario);
        entityManager.flush();

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setJogo(jogo);
        avaliacao.setUsuario(usuario);
        avaliacao.setNota(5);
        avaliacao.setComentario("Ótimo jogo!");

        Avaliacao avaliacaoSalva = avaliacaoRepository.save(avaliacao);
        assertThat(avaliacaoSalva.getId()).isNotNull();
        assertThat(avaliacaoSalva.getJogo()).isEqualTo(jogo);
        assertThat(avaliacaoSalva.getUsuario()).isEqualTo(usuario);
        assertThat(avaliacaoSalva.getNota()).isEqualTo(5);
        assertThat(avaliacaoSalva.getComentario()).isEqualTo("Ótimo jogo!");
    }

    private Jogo criarJogo(String nome, String descricao, double preco) {
        Jogo jogo = new Jogo();
        jogo.setNome(nome);
        jogo.setDescricao(descricao);
        jogo.setPreco(preco);
        return jogo;
    }

    private Usuario criarUsuario(String nome, String cpf, String email, String senha) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setEmail(email);
        usuario.setSenha(senha);
        return usuario;
    }
}
