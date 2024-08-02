package com.infnet.tp1PB.audit;


import com.infnet.tp1PB.model.Avaliacao;
import com.infnet.tp1PB.repository.AvaliacaoHistoricoRepository;
import com.infnet.tp1PB.util.SpringContext;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AvaliacaoListener {

    @PostPersist
    @PostUpdate
    public void onPostPersistOrUpdate(Avaliacao avaliacao) {
        AvaliacaoHistorico historico = new AvaliacaoHistorico();
        historico.setAvaliacaoId(avaliacao.getId());
        historico.setNota(avaliacao.getNota());
        historico.setComentario(avaliacao.getComentario());
        historico.setTimestamp(LocalDateTime.now());
        historico.setUsuario(avaliacao.getUsuario());
        historico.setJogo(avaliacao.getJogo());

        SpringContext.getBean(AvaliacaoHistoricoService.class).save(historico);
    }
}
