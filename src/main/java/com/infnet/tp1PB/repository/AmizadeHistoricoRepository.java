package com.infnet.tp1PB.repository;

import com.infnet.tp1PB.audit.AmizadeHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmizadeHistoricoRepository extends JpaRepository<AmizadeHistorico, Long> {
}
