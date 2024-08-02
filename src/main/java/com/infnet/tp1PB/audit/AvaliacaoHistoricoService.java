package com.infnet.tp1PB.audit;

import com.infnet.tp1PB.repository.AvaliacaoHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AvaliacaoHistoricoService {

    @Autowired
    private AvaliacaoHistoricoRepository historicoRepository;

    public void save(AvaliacaoHistorico historico) {
        historicoRepository.save(historico);
    }
}
