package com.example.timers_24_backend.service;

import com.example.timers_24_backend.dto.DeltagerDto;
import com.example.timers_24_backend.dto.DisciplinDto;
import com.example.timers_24_backend.dto.ResultatDto;
import com.example.timers_24_backend.entity.Deltager;
import com.example.timers_24_backend.entity.Disciplin;
import com.example.timers_24_backend.entity.Resultat;
import com.example.timers_24_backend.exception.NotFoundException;
import com.example.timers_24_backend.repository.DeltagerRepository;
import com.example.timers_24_backend.repository.DisciplinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DeltagerService {

    private final DeltagerRepository deltagerRepository;
    private final DisciplinRepository disciplinRepository;

    @Autowired
    public DeltagerService(DeltagerRepository deltagerRepository, DisciplinRepository disciplinRepository) {
        this.deltagerRepository = deltagerRepository;
        this.disciplinRepository = disciplinRepository;
    }

    // Opret ny deltager med eller uden disciplin
    public DeltagerDto createDeltager(DeltagerDto deltagerDto) {
        Deltager deltager = convertToEntity(deltagerDto);

        // Konverter og sæt Resultat entiteter
        deltager.setResultater(convertToResultatEntities(deltagerDto.getResultater()));

        // Hvis discipliner er angivet, tilføj dem til deltageren
        if (deltagerDto.getDiscipliner() != null && !deltagerDto.getDiscipliner().isEmpty()) {
            List<Disciplin> discipliner = fetchDiscipliner(deltagerDto.getDiscipliner());
            deltager.setDiscipliner(discipliner);
        }

        // Gem deltager entitet
        Deltager savedDeltager = deltagerRepository.save(deltager);
        return convertToDto(savedDeltager);
    }

    // Opdater eksisterende deltager med nye oplysninger og/eller discipliner
    public DeltagerDto updateDeltager(UUID deltagerId, DeltagerDto deltagerDto) {
        Deltager deltager = deltagerRepository.findById(deltagerId)
                .orElseThrow(() -> new NotFoundException("Deltager not found with id: " + deltagerId));

        // Opdater deltager detaljer
        deltager.setNavn(deltagerDto.getNavn());
        deltager.setAlder(deltagerDto.getAlder());
        deltager.setKon(deltagerDto.getKon());
        deltager.setKlub(deltagerDto.getKlub());

        // Konverter og sæt Resultat entiteter
        deltager.setResultater(convertToResultatEntities(deltagerDto.getResultater()));

        // Hvis discipliner er angivet, tilføj dem til deltageren
        if (deltagerDto.getDiscipliner() != null && !deltagerDto.getDiscipliner().isEmpty()) {
            List<Disciplin> discipliner = fetchDiscipliner(deltagerDto.getDiscipliner());
            deltager.setDiscipliner(discipliner);
        }

        // Gem deltager entitet
        Deltager savedDeltager = deltagerRepository.save(deltager);
        return convertToDto(savedDeltager);
    }

    // Tilføj disciplin til en eksisterende deltager
    public DeltagerDto addDisciplinToDeltager(UUID deltagerId, UUID disciplinId) {
        Deltager deltager = deltagerRepository.findById(deltagerId)
                .orElseThrow(() -> new NotFoundException("Deltager not found with id: " + deltagerId));

        Disciplin disciplin = disciplinRepository.findById(disciplinId)
                .orElseThrow(() -> new NotFoundException("Disciplin not found with id: " + disciplinId));

        List<Disciplin> discipliner = deltager.getDiscipliner(); // Get the existing list of discipliner
        discipliner.add(disciplin); // Add the new disciplin to the list

        deltager.setDiscipliner(discipliner); // Set the updated list back to the deltager

        Deltager savedDeltager = deltagerRepository.save(deltager);
        return convertToDto(savedDeltager);
    }

    // Hjælpefunktion til at konvertere ResultatDto til Resultat entiteter
    private List<Resultat> convertToResultatEntities(List<ResultatDto> resultatDtos) {
        if (resultatDtos == null) {
            return Collections.emptyList(); // Return an empty list if resultatDtos is null
        }

        return resultatDtos.stream()
                .map(this::convertToEntity) // Using method reference for clarity
                .collect(Collectors.toList());
    }

    // Hjælpefunktion til at konvertere Deltager til DeltagerDto
    private DeltagerDto convertToDto(Deltager deltager) {
        return new DeltagerDto(
                deltager.getId(),
                deltager.getNavn(),
                deltager.getKon(),
                deltager.getAlder(),
                deltager.getKlub()
        );
    }

    // Hjælpefunktion til at hente discipliner baseret på deres IDs
    private List<Disciplin> fetchDiscipliner(List<UUID> disciplinIds) {
        return disciplinIds.stream()
                .map(disciplinId -> disciplinRepository.findById(disciplinId)
                        .orElseThrow(() -> new NotFoundException("Disciplin not found with id: " + disciplinId)))
                .collect(Collectors.toList());
    }

    // Hjælpefunktion til at konvertere DeltagerDto til Deltager entitet
    private Deltager convertToEntity(DeltagerDto deltagerDto) {
        Deltager deltager = new Deltager();
        deltager.setNavn(deltagerDto.getNavn());
        deltager.setAlder(deltagerDto.getAlder());
        deltager.setKon(deltagerDto.getKon());
        deltager.setKlub(deltagerDto.getKlub());
        return deltager;
    }

    private Resultat convertToEntity(ResultatDto resultatDto) {
        // You need to define a constructor in your Resultat entity to match the fields you're passing
        // Assuming a constructor with necessary fields exists in Resultat entity
        Resultat resultat = new Resultat();
        resultat.setResultattype(resultatDto.getResultattype());
        resultat.setDato(resultatDto.getDato());
        resultat.setResultatvaerdi(resultatDto.getResultatvaerdi());
        return resultat;
    }
    public boolean deleteDeltager(UUID id) {
        Optional<Deltager> deltagerOptional = deltagerRepository.findById(id);
        if (deltagerOptional.isPresent()) {
            deltagerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public DeltagerDto getDeltagerById(UUID id) {
        Deltager deltager = deltagerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Deltager not found with id: " + id));
        return convertToDto(deltager);
    }

    public List<DeltagerDto> getAllDeltager() {
        List<Deltager> deltagerList = deltagerRepository.findAll();
        return deltagerList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<DisciplinDto> getAllDiscipliner() {
        List<Disciplin> disciplins = disciplinRepository.findAll();
        return disciplins.stream()
                .map(disciplin -> new DisciplinDto(
                        disciplin.getId(),
                        disciplin.getNavn(),
                        disciplin.getResultattype(),
                        disciplin.getDeltagere().stream().map(Deltager::getId).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
