package com.example.timers_24_backend.repository;

import com.example.timers_24_backend.entity.Deltager;
import com.example.timers_24_backend.entity.Disciplin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface DeltagerRepository  extends JpaRepository<Deltager, UUID> {
    List<Deltager> findByNavnContaining(String navn);

}
