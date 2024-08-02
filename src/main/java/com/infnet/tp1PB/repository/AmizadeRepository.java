package com.infnet.tp1PB.repository;

import com.infnet.tp1PB.model.Amizade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmizadeRepository extends JpaRepository<Amizade, Long> {
}
