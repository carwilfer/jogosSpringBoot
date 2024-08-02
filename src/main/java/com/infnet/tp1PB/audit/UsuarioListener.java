package com.infnet.tp1PB.audit;

import com.infnet.tp1PB.model.Usuario;
import com.infnet.tp1PB.repository.UsuarioHistoricoRepository;
import com.infnet.tp1PB.util.SpringContext;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UsuarioListener {

    @PostPersist
    @PostUpdate
    public void onPostPersistOrUpdate(Usuario usuario) {
        UsuarioHistorico historico = new UsuarioHistorico();
        historico.setUsuarioId(usuario.getId());
        historico.setNome(usuario.getNome());
        historico.setCpf(usuario.getCpf());
        historico.setEmail(usuario.getEmail());
        historico.setSenha(usuario.getSenha());
        historico.setTimestamp(LocalDateTime.now());

        SpringContext.getBean(UsuarioHistoricoService.class).save(historico);
    }
}
