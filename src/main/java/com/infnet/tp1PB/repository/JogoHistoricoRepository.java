package com.infnet.tp1PB.repository;

import com.infnet.tp1PB.audit.JogoHistorico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JogoHistoricoRepository extends JpaRepository<JogoHistorico, Long> {
    // Métodos específicos de consulta podem ser adicionados aqui, se necessário
}
