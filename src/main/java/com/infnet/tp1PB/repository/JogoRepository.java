package com.infnet.tp1PB.repository;

import com.infnet.tp1PB.model.Jogo;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {
    @EntityGraph(attributePaths = {"empresa"})
    Optional<Jogo> findById(Long id);
}
