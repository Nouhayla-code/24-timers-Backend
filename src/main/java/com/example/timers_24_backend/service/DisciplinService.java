package com.example.timers_24_backend.service;

import com.example.timers_24_backend.entity.Disciplin;
import com.example.timers_24_backend.repository.DisciplinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplinService {

    @Autowired
    private DisciplinRepository disciplinRepository;

    public void createDisciplins(List<Disciplin> disciplins) {
        disciplinRepository.saveAll(disciplins);
    }
}
