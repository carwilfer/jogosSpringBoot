package com.infnet.tp1PB.model;

import com.infnet.tp1PB.audit.AmizadeListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EntityListeners(AmizadeListener.class)
public class Amizade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "solicitante_id")
    private Usuario solicitante;

    @ManyToOne
    @JoinColumn(name = "solicitado_id")
    private Usuario solicitado;

    private String status;
    public Amizade() {
    }

    public Amizade(Usuario solicitante, Usuario solicitado, String status) {
        this.solicitante = solicitante;
        this.solicitado = solicitado;
        this.status = status;
    }
}
