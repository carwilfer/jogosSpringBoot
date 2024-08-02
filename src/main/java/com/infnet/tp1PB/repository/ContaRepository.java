package com.infnet.tp1PB.repository;

import com.infnet.tp1PB.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}
