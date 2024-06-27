package com.example.timers_24_backend.service;

import com.example.timers_24_backend.dto.DeltagerDto;
import com.example.timers_24_backend.dto.ResultatDto;
import com.example.timers_24_backend.entity.Deltager;
import com.example.timers_24_backend.entity.Disciplin;
import com.example.timers_24_backend.entity.Resultat;
import com.example.timers_24_backend.exception.NotFoundException;
import com.example.timers_24_backend.repository.DeltagerRepository;
import com.example.timers_24_backend.repository.DisciplinRepository;
import com.example.timers_24_backend.repository.ResultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ResultatService {

    private final ResultatRepository resultatRepository;
    private final DeltagerRepository DeltagerRepository;

    private final DisciplinRepository disciplinRepository;

    @Autowired
    public ResultatService(ResultatRepository resultatRepository, DeltagerRepository DeltagerRepository, DisciplinRepository disciplinRepository) {
        this.resultatRepository = resultatRepository;
        this.DeltagerRepository = DeltagerRepository;
        this.disciplinRepository = disciplinRepository;
    }

    // Hent alle resultater
    public List<ResultatDto> getAllResultater() { // Hent alle resultater
        List<Resultat> resultatList = resultatRepository.findAll(); // Hent alle resultater fra databasen
        return resultatList.stream() // Konverter hver resultat til en DTO og gem i liste
                .map(this::convertToDto) // Konverter resultat til DTO
                .collect(Collectors.toList()); // Gem i liste
    }

    public ResultatDto registrerResultat( ResultatDto resultatDto) { // Opret nyt resultat
        Resultat resultat = convertToEntity(resultatDto); // Konverter DTO til entitet
        Resultat savedResultat = resultatRepository.save(resultat); // Gem resultat i databasen
        return convertToDto(savedResultat); // Konverter resultat til DTO og returner
    }

    private Resultat convertToEntity(ResultatDto resultatDto) { // Konverter DTO til entitet
        Deltager deltager = DeltagerRepository.findById(resultatDto.getDeltagerId()) // Hent deltager fra databasen
                .orElseThrow(() -> new NotFoundException("Deltager not found with id: " + resultatDto.getDeltagerId()));// Hvis deltager ikke findes, kast exception

        Disciplin disciplin = disciplinRepository.findById(resultatDto.getDisciplinId()) // Hent disciplin fra databasen
                .orElseThrow(() -> new NotFoundException("Disciplin not found with id: " + resultatDto.getDisciplinId())); // Hvis disciplin ikke findes, kast exception
         Resultat resultat = new Resultat (); // Opret ny resultat entitet
            resultat.setResultattype(resultatDto.getResultattype()); // Sæt resultattype
            resultat.setDato(resultatDto.getDato()); // Sæt dato
            resultat.setResultatvaerdi(resultatDto.getResultatvaerdi()); // Sæt resultatværdi
            resultat.setDeltager(deltager); // Sæt deltager
            resultat.setDisciplin(disciplin); // Sæt disciplin
            return resultat; // Returner resultat

    }


    // Hjælpefunktion til at konvertere Resultat til ResultatDto
    public ResultatDto convertToDto(Resultat resultat) { // Konverter resultat til DTO
        UUID deltagerId = resultat.getDeltager() != null ? resultat.getDeltager().getId() : null; // Hent deltagerens id
        UUID disciplinId = resultat.getDisciplin() != null ? resultat.getDisciplin().getId() : null; // Hent disciplinens id

        return new ResultatDto( // Opret ny DTO
                resultat.getId(),
                resultat.getResultattype(),
                resultat.getDato(),
                resultat.getResultatvaerdi(),
                deltagerId,
                disciplinId
        );
    }
}
