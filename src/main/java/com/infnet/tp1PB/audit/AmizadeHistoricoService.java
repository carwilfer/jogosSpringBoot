package com.infnet.tp1PB.audit;

import com.infnet.tp1PB.repository.AmizadeHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmizadeHistoricoService {

    @Autowired
    private AmizadeHistoricoRepository historicoRepository;

    public void save(AmizadeHistorico historico) {
        historicoRepository.save(historico);
    }
}
