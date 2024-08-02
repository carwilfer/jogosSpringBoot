package com.infnet.tp1PB.audit;

import com.infnet.tp1PB.model.Amizade;
import com.infnet.tp1PB.repository.AmizadeHistoricoRepository;
import com.infnet.tp1PB.util.SpringContext;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AmizadeListener {

    @Autowired
    private AmizadeHistoricoService historicoService;

    @PostPersist
    @PostUpdate
    public void onSaveOrUpdate(Amizade amizade) {
        AmizadeHistorico historico = new AmizadeHistorico();
        historico.setAmizadeId(amizade.getId());
        historico.setSolicitanteId(amizade.getSolicitante().getId());
        historico.setSolicitadoId(amizade.getSolicitado().getId());
        historico.setStatus(amizade.getStatus());
        historico.setTimestamp(LocalDateTime.now()); // Corrigido para setar a data/hora atual

        historicoService.save(historico);
    }
}
