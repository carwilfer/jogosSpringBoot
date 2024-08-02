package com.infnet.tp1PB.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Getter
@Setter
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    private double preco;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    @JsonIgnore // Evita a serialização recursiva
    private Empresa empresa;

    @OneToMany(mappedBy = "jogo")
    private List<Compra> compras;

    @OneToMany(mappedBy = "jogo")
    private List<Avaliacao> avaliacoes;

    @OneToMany(mappedBy = "jogo")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "jogo")
    private List<Denuncia> denuncias;
}
