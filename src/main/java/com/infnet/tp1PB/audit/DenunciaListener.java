package com.infnet.tp1PB.audit;

import com.infnet.tp1PB.model.Denuncia;
import com.infnet.tp1PB.repository.DenunciaHistoricoRepository;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DenunciaListener {

    @Autowired
    private DenunciaHistoricoService historicoService;

    @PostPersist
    @PostUpdate
    public void onPostPersistOrUpdate(Denuncia denuncia) {
        DenunciaHistorico historico = new DenunciaHistorico();
        historico.setDenunciaId(denuncia.getId());
        historico.setDenuncianteId(denuncia.getDenunciante().getId());
        historico.setDenunciadoId(denuncia.getDenunciado().getId());
        historico.setMotivo(denuncia.getMotivo());  // Certifique-se que getMotivo() está definido e retorna String
        historico.setDescricao(denuncia.getDescricao());
        historico.setTimestamp(LocalDateTime.now());

        historicoService.save(historico);
    }
}