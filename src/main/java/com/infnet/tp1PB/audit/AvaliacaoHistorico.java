package com.infnet.tp1PB.audit;

import com.infnet.tp1PB.model.Jogo;
import com.infnet.tp1PB.model.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class AvaliacaoHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long avaliacaoId;
    private int nota;
    private String comentario;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;
}
