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

        List<Disciplin> discipliner = deltager.getDiscipliner(); // Indhenter listen af discipliner fra deltageren
        discipliner.add(disciplin); // Tilføj den nye disciplin til listen

        deltager.setDiscipliner(discipliner); // Opdater listen af discipliner på deltageren


        // Gem deltager entitet
        Deltager savedDeltager = deltagerRepository.save(deltager);
        return convertToDto(savedDeltager);
    }

    // Hjælpefunktion til at konvertere ResultatDto til Resultat entiteter
    private List<Resultat> convertToResultatEntities(List<ResultatDto> resultatDtos) {
        if (resultatDtos == null) {
            return Collections.emptyList(); // Returner en tom liste, hvis resultatDtos er null
        }

        return resultatDtos.stream()
                .map(this::convertToEntity) // Konverter hvert ResultatDto til Resultat entitet
                .collect(Collectors.toList()); // Returner en liste af Resultat entiteter
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

    // Hjælpefunktion til at konvertere Resultat til ResultatDto
    private Resultat convertToEntity(ResultatDto resultatDto) {
        Resultat resultat = new Resultat(); // Opret ny Resultat entitet
        resultat.setResultattype(resultatDto.getResultattype());// Sæt resultattype
        resultat.setDato(resultatDto.getDato()); // Sæt dato
        resultat.setResultatvaerdi(resultatDto.getResultatvaerdi()); // Sæt resultatværdi
        return resultat;
    }

    // Slet deltager
    public boolean deleteDeltager(UUID id) {
        Optional<Deltager> deltagerOptional = deltagerRepository.findById(id); // Find deltageren med det givne id
        if (deltagerOptional.isPresent()) { // Hvis deltageren findes
            deltagerRepository.deleteById(id); // Slet deltageren
            return true; // Returner true
        } else {
            return false; // Returner false
        }
    }

    // Hent deltager med det givne id
    public DeltagerDto getDeltagerById(UUID id) { // Hent deltageren med det givne id
        Deltager deltager = deltagerRepository.findById(id) // Find deltageren med det givne id
                .orElseThrow(() -> new NotFoundException("Deltager not found with id: " + id)); // Hvis deltageren ikke findes, kast en NotFoundException
        return convertToDto(deltager); // Konverter deltageren til en DeltagerDto og returner den
    }

    // Hent alle deltager
    public List<DeltagerDto> getAllDeltager() { // Hent alle deltager
        List<Deltager> deltagerList = deltagerRepository.findAll(); // Hent alle deltager fra databasen
        return deltagerList.stream() // Konverter alle deltager til DeltagerDto og returner dem som en liste
                .map(this::convertToDto)// Konverter hver deltager til en DeltagerDto
                .collect(Collectors.toList()); // Returner en liste af DeltagerDto
    }

    // Hent alle discipliner
    public List<DisciplinDto> getAllDiscipliner() { // Hent alle discipliner
        List<Disciplin> disciplins = disciplinRepository.findAll(); // Hent alle discipliner fra databasen
        return disciplins.stream() // Konverter alle discipliner til DisciplinDto og returner dem som en liste
                .map(disciplin -> new DisciplinDto( // Konverter hver disciplin til en DisciplinDto
                        disciplin.getId(),
                        disciplin.getNavn(),
                        disciplin.getResultattype(),
                        disciplin.getDeltagere().stream().map(Deltager::getId).collect(Collectors.toList()))) // Hent alle deltageres id'er og returner dem som en liste
                .collect(Collectors.toList()); // Returner en liste af DisciplinDto
    }


    // Hjælpefunktion til at konvertere Resultat til ResultatDto
    public void saveDeltager(Deltager deltager1) {
        deltagerRepository.save(deltager1);
    }
}
