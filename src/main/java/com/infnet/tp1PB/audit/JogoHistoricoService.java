package com.infnet.tp1PB.audit;

import com.infnet.tp1PB.model.Jogo;
import com.infnet.tp1PB.repository.JogoHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JogoHistoricoService {

    @Autowired
    private JogoHistoricoRepository jogoHistoricoRepository;

    public JogoHistorico registrarHistorico(Jogo jogo, String acao) {
        JogoHistorico historico = new JogoHistorico();
        historico.setJogo(jogo);
        historico.setAcao(acao);
        return jogoHistoricoRepository.save(historico);
    }
}
