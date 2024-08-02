package com.infnet.tp1PB.audit;

import com.infnet.tp1PB.model.Jogo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class JogoListener {
    @Autowired
    private JogoHistoricoService jogoHistoricoService;

    @EventListener
    public void jogoCriado(JogoCriadoEvent event) {
        Jogo jogo = event.getJogo();
        jogoHistoricoService.registrarHistorico(jogo, "Jogo criado");
    }
}
