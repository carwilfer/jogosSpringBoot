package com.infnet.tp1PB.audit;

import com.infnet.tp1PB.repository.CompraHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraHistoricoService {

    @Autowired
    private CompraHistoricoRepository historicoRepository;

    public void save(CompraHistorico historico) {
        historicoRepository.save(historico);
    }
}
