package com.infnet.tp1PB.model;

import com.infnet.tp1PB.audit.AvaliacaoListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EntityListeners(AvaliacaoListener.class)
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private int nota;
    private String comentario;
}
