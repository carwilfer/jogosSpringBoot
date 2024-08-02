package com.infnet.tp1PB.audit;

import com.infnet.tp1PB.repository.UsuarioHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioHistoricoService {

    @Autowired
    private UsuarioHistoricoRepository historicoRepository;

    public void save(UsuarioHistorico historico) {
        historicoRepository.save(historico);
    }
}
