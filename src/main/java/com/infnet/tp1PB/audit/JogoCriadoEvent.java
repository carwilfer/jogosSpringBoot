package com.infnet.tp1PB.audit;

import com.infnet.tp1PB.model.Jogo;
import org.springframework.context.ApplicationEvent;

public class JogoCriadoEvent extends ApplicationEvent {

    private Jogo jogo;

    public JogoCriadoEvent(Object source, Jogo jogo) {
        super(source);
        this.jogo = jogo;
    }

    public Jogo getJogo() {
        return jogo;
    }
}
