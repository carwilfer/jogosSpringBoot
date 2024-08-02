package com.infnet.tp1PB.audit;

import com.infnet.tp1PB.model.Chat;
import com.infnet.tp1PB.repository.ChatHistoricoRepository;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatListener {

    @Autowired
    private ChatHistoricoService historicoService;

    @PostPersist
    @PostUpdate
    public void onSaveOrUpdate(Chat chat) {
        ChatHistorico historico = new ChatHistorico();
        historico.setChatId(chat.getId());
        historico.setRemetenteId(chat.getRemetente().getId());
        historico.setDestinatarioId(chat.getDestinatario().getId());
        historico.setMensagem(chat.getMensagem());
        historico.setTimestamp(chat.getTimestamp());

        historicoService.save(historico);
    }
}
