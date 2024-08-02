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
public class AmizadeHistorico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long amizadeId;
    private Long solicitanteId;
    private Long solicitadoId;
    private String status;
    private LocalDateTime timestamp;

    // Getters e Setters
}
