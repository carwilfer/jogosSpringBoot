package com.infnet.tp1PB.repository;

import com.infnet.tp1PB.audit.UsuarioHistorico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioHistoricoRepository extends JpaRepository<UsuarioHistorico, Long> {
}
