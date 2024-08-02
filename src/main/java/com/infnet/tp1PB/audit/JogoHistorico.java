package com.infnet.tp1PB.audit;

import com.infnet.tp1PB.model.Jogo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class JogoHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "jogo_id", nullable = false)
    private Jogo jogo;

    @Column(nullable = false)
    private String acao;

    @Column(nullable = false)
    private LocalDateTime dataHora = LocalDateTime.now();
}
