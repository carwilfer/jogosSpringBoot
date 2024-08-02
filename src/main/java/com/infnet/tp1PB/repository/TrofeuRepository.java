package com.infnet.tp1PB.repository;

import com.infnet.tp1PB.model.Transacao;
import com.infnet.tp1PB.model.Trofeu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrofeuRepository extends JpaRepository<Trofeu, Long> {
}
