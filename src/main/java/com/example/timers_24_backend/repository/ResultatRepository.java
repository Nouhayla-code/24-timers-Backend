package com.example.timers_24_backend.repository;

import com.example.timers_24_backend.entity.Disciplin;
import com.example.timers_24_backend.entity.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ResultatRepository extends JpaRepository<Resultat, UUID> {
    List<Resultat> findByDisciplin(Disciplin disciplin);
    List<Resultat> findByDisciplinOrderByResultatvaerdi(Disciplin disciplin);
    List<Resultat> findByDisciplinAndDeltagerKonAndDeltagerAlderBetween(Disciplin disciplin, String kon, int minAlder, int maxAlder);
}
