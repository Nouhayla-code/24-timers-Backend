package com.example.timers_24_backend.api;

import com.example.timers_24_backend.dto.ResultatDto;
import com.example.timers_24_backend.entity.Deltager;
import com.example.timers_24_backend.entity.Disciplin;
import com.example.timers_24_backend.service.DeltagerService;
import com.example.timers_24_backend.service.DisciplinService;
import com.example.timers_24_backend.service.ResultatService;
import jakarta.annotation.PostConstruct;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/resultater")
public class ResultatController {

    private final ResultatService resultatService;
    private final DeltagerService deltagerService;
    private final DisciplinService disciplinService;

    public ResultatController(ResultatService resultatService, DeltagerService deltagerService, DisciplinService disciplinService) {
        this.resultatService = resultatService;
        this.deltagerService = deltagerService;
        this.disciplinService = disciplinService;
    }

// init date til at lave noget mockata når vi starter vores applikation
    @PostConstruct
    public void initData() {
        // Opret Deltager og Disciplin enheder
        Deltager deltager1 = new Deltager(UUID.randomUUID(), "Participant 1", "Male", 25, "Test Klub");
        Deltager deltager2 = new Deltager(UUID.randomUUID(), "Participant 2", "Female", 23, "Another Klub");

        Disciplin disciplin1 = new Disciplin(UUID.randomUUID(), "100-meterløb (atletik)", "Tid");
        Disciplin disciplin2 = new Disciplin(UUID.randomUUID(), "Højdespring (atletik)", "Meter");

        deltagerService.saveDeltager(deltager1);
        deltagerService.saveDeltager(deltager2);

        disciplinService.saveDisciplin(disciplin1);
        disciplinService.saveDisciplin(disciplin2);

        // Tilføj initialiseringsdata til ResultatDto
        ResultatDto resultat1 = new ResultatDto(UUID.randomUUID(), "Tid", LocalDate.now(), "10:00", deltager1.getId(), disciplin1.getId());
        ResultatDto resultat2 = new ResultatDto(UUID.randomUUID(), "Meter", LocalDate.now(), "5.5", deltager2.getId(), disciplin2.getId());

        // Gem initialiseringsdataene ved hjælp af service
        resultatService.registrerResultat(resultat1);
        resultatService.registrerResultat(resultat2);
    }

    // Hent alle resultater
    @GetMapping
    public ResponseEntity<List<ResultatDto>> getAllResultater() {
        List<ResultatDto> resultatDtoList = resultatService.getAllResultater();
        return ResponseEntity.ok(resultatDtoList);
    }


// registrerResultat metode til at registrere et resultat
    @PostMapping
    public ResponseEntity<ResultatDto> registrerResultat(@RequestBody ResultatDto resultatDto) {
        ResultatDto createdResultat = resultatService.registrerResultat(resultatDto);
        return ResponseEntity.created(URI.create("/api/resultater/" + createdResultat.getId())).body(createdResultat);
    }
}
