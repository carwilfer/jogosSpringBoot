package com.infnet.tp1PB.audit;

import com.infnet.tp1PB.repository.ChatHistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatHistoricoService {

    @Autowired
    private ChatHistoricoRepository historicoRepository;

    public void save(ChatHistorico historico) {
        historicoRepository.save(historico);
    }
}