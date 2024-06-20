package com.example.timers_24_backend.service;

import com.example.timers_24_backend.dto.DisciplinDto;
import com.example.timers_24_backend.entity.Deltager;
import com.example.timers_24_backend.entity.Disciplin;
import com.example.timers_24_backend.repository.DeltagerRepository;
import com.example.timers_24_backend.repository.DisciplinRepository;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DisciplinService {


    private final DisciplinRepository disciplinRepository;
    private final DeltagerRepository deltagerRepository;


    public DisciplinService(DisciplinRepository disciplinRepository, DeltagerRepository deltagerRepository) {
        this.disciplinRepository = disciplinRepository;
        this.deltagerRepository = deltagerRepository;
    }
    public void createDisciplins(List<Disciplin> disciplins) {
        disciplinRepository.saveAll(disciplins);
    }

    public List<DisciplinDto> getAllDiscipliner() {
        List<Disciplin> disciplins = disciplinRepository.findAll();
        List<DisciplinDto> disciplinDtos = new ArrayList<>();
        for (Disciplin disciplin : disciplins) {
            List<UUID> deltagereId = disciplin.getDeltagere().stream() .map(Deltager::getId).toList() ;
            disciplinDtos.add(new DisciplinDto(disciplin.getId(), disciplin.getNavn(), disciplin.getResultattype(),deltagereId ));
        }
        return disciplinDtos;
    }


    public DisciplinDto getDisciplin(UUID id) {
        Disciplin disciplin = disciplinRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Disciplin not found with id: " + id));

        List<UUID> deltagereId = disciplin.getDeltagere().stream()
                .map(deltager -> deltager.getId())
                .collect(Collectors.toList());

        return new DisciplinDto(disciplin.getId(), disciplin.getNavn(), disciplin.getResultattype(), deltagereId);
    }

    public DisciplinDto addDeltagerToDisciplin(UUID id,  List<UUID> deltagerId) {
        Disciplin disciplin = disciplinRepository.findById(id).orElseThrow();
        for (UUID uuid : deltagerId) {
            Deltager deltager = deltagerRepository.findById(uuid).orElseThrow();
            disciplin.getDeltagere().add(deltager);
            deltager.getDiscipliner().add(disciplin);
        }
        disciplinRepository.save(disciplin);
        return new DisciplinDto(disciplin.getId(), disciplin.getNavn(), disciplin.getResultattype(), disciplin.getDeltagere().stream().map(Deltager::getId).toList());

    }


}
