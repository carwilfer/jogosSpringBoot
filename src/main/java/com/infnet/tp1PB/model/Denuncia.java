package com.infnet.tp1PB.model;

import com.infnet.tp1PB.audit.DenunciaListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@EntityListeners(DenunciaListener.class)
public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "denunciante_id")
    private Usuario denunciante;

    @ManyToOne
    @JoinColumn(name = "denunciado_id")
    private Usuario denunciado;

    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;
}
