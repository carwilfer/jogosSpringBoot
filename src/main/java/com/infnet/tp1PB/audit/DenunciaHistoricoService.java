package com.infnet.tp1PB.audit;

import com.infnet.tp1PB.repository.DenunciaHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DenunciaHistoricoService {

    @Autowired
    private DenunciaHistoricoRepository historicoRepository;

    public void save(DenunciaHistorico historico) {
        historicoRepository.save(historico);
    }
}
