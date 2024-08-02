package com.infnet.tp1PB.audit;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class CompraHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long compraId;
    private Long usuarioId;
    private Long jogoId;
    private LocalDateTime data;
    private LocalDateTime timestamp;

    // Getters e Setters
}
