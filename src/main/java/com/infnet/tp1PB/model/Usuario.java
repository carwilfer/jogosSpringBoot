package com.infnet.tp1PB.model;

import com.infnet.tp1PB.audit.UsuarioListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@EntityListeners(UsuarioListener.class)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private double saldo;

    @OneToMany(mappedBy = "usuario")
    private List<Conta> contas;

    @OneToMany(mappedBy = "usuario")
    private List<Compra> compras;

    @OneToMany(mappedBy = "usuario")
    private List<Trofeu> trofeus;

    @OneToMany(mappedBy = "usuario")
    private List<Avaliacao> avaliacoes;

    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

    @OneToMany(mappedBy = "solicitante")
    private List<Amizade> amizadesSolicitadas;

    @OneToMany(mappedBy = "solicitado")
    private List<Amizade> amizadesRecebidas;

    @OneToMany(mappedBy = "remetente")
    private List<Chat> chatsEnviados;

    @OneToMany(mappedBy = "destinatario")
    private List<Chat> chatsRecebidos;

    @OneToMany(mappedBy = "denunciante")
    private List<Denuncia> denunciasFeitas;

    @OneToMany(mappedBy = "denunciado")
    private List<Denuncia> denunciasRecebidas;

    public Usuario(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Usuario() {

    }
}
