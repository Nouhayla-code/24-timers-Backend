package com.example.timers_24_backend.service;

import com.example.timers_24_backend.dto.DisciplinDto;

import com.example.timers_24_backend.entity.Deltager;
import com.example.timers_24_backend.entity.Disciplin;

import com.example.timers_24_backend.repository.DeltagerRepository;
import com.example.timers_24_backend.repository.DisciplinRepository;
import com.example.timers_24_backend.repository.ResultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final ResultatRepository resultatRepository;

    @Autowired
    public DisciplinService(DisciplinRepository disciplinRepository, DeltagerRepository deltagerRepository , ResultatRepository resultatRepository) {
        this.disciplinRepository = disciplinRepository;
        this.deltagerRepository = deltagerRepository;
        this.resultatRepository = resultatRepository;
    }

    // Opret ny disciplin
    public void createDisciplins(List<Disciplin> disciplins) {
        disciplinRepository.saveAll(disciplins);
    }


    // Hent alle discipliner
    public List<DisciplinDto> getAllDiscipliner() {
        List<Disciplin> disciplins = disciplinRepository.findAll(); // Hent alle discipliner fra databasen
        List<DisciplinDto> disciplinDtos = new ArrayList<>(); // Opret en liste til at gemme DTO'er
        for (Disciplin disciplin : disciplins) { // Konverter hver disciplin til en DTO og tilføj til listen
            List<UUID> deltagereId = disciplin.getDeltagere().stream() // Hent alle deltageres id'er
                    .map(Deltager::getId) // Konverter deltager til id
                    .collect(Collectors.toList()); // Gem i liste
            disciplinDtos.add(new DisciplinDto(disciplin.getId(), disciplin.getNavn(), disciplin.getResultattype(), deltagereId)); // Tilføj DTO til listen
        }
        return disciplinDtos;
    }


    // Hent disciplin med id
    public DisciplinDto getDisciplin(UUID id) { // Hent disciplin med id
        Disciplin disciplin = disciplinRepository.findById(id) // Hent disciplin fra databasen
                .orElseThrow(() -> new NoSuchElementException("Disciplin not found with id: " + id)); // Hvis disciplin ikke findes, kast exception

        List<UUID> deltagereId = disciplin.getDeltagere().stream() // Hent alle deltageres id'er
                .map(Deltager::getId) // Konverter deltager til id
                .collect(Collectors.toList()); // Gem i liste

        return new DisciplinDto(disciplin.getId(), disciplin.getNavn(), disciplin.getResultattype(), deltagereId); // Returner DTO
    }


    // Tilføj deltager til disciplin
    public DisciplinDto addDeltagerToDisciplin(UUID id, List<UUID> deltagerId) { // Tilføj deltager til disciplin
        Disciplin disciplin = disciplinRepository.findById(id) // Hent disciplin fra databasen
                .orElseThrow(() -> new NoSuchElementException("Disciplin not found with id: " + id)); // Hvis disciplin ikke findes, kast exception
        for (UUID uuid : deltagerId) { // For hver deltager id
            Deltager deltager = deltagerRepository.findById(uuid) // Hent deltager fra databasen
                    .orElseThrow(() -> new NoSuchElementException("Deltager not found with id: " + uuid)); // Hvis deltager ikke findes, kast exception
            disciplin.getDeltagere().add(deltager); // Tilføj deltager til disciplin
            deltager.getDiscipliner().add(disciplin); // Tilføj disciplin til deltager
        }
        disciplinRepository.save(disciplin); // Gem disciplin
        return new DisciplinDto(disciplin.getId(), disciplin.getNavn(), disciplin.getResultattype(), // Returner DTO
                disciplin.getDeltagere().stream().map(Deltager::getId).collect(Collectors.toList())); // Hent alle deltageres id'er og returner dem som en liste
    }



    public void saveDisciplin(Disciplin disciplin2) { // Gem disciplin
        disciplinRepository.save(disciplin2);
    }
}
