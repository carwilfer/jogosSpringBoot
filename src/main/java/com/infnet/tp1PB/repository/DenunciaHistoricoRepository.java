package com.infnet.tp1PB.repository;

import com.infnet.tp1PB.audit.DenunciaHistorico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DenunciaHistoricoRepository extends JpaRepository<DenunciaHistorico, Long> {
}