package com.infnet.tp1PB.audit;

import com.infnet.tp1PB.model.Compra;
import com.infnet.tp1PB.repository.CompraHistoricoRepository;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CompraListener {

    @Autowired
    private CompraHistoricoRepository historicoRepository;

    @PostPersist
    @PostUpdate
    public void onPostPersistOrUpdate(Compra compra) {
        CompraHistorico historico = new CompraHistorico();
        historico.setCompraId(compra.getId());
        historico.setUsuarioId(compra.getUsuario().getId());
        historico.setJogoId(compra.getJogo().getId());
        historico.setData(compra.getData());
        historico.setTimestamp(LocalDateTime.now());
        historicoRepository.save(historico);
    }
}
