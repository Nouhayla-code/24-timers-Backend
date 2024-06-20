package com.example.timers_24_backend.repository;

import com.example.timers_24_backend.entity.Disciplin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DisciplinRepository  extends JpaRepository<Disciplin, UUID> {

}
