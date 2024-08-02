package com.infnet.tp1PB.repository;

import com.infnet.tp1PB.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
