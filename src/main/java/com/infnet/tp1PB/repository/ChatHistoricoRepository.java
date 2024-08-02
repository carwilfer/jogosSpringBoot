package com.infnet.tp1PB.repository;

import com.infnet.tp1PB.audit.ChatHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatHistoricoRepository extends JpaRepository<ChatHistorico, Long> {
}
