package com.example.timers_24_backend.api;
import com.example.timers_24_backend.dto.DeltagerDto;
import com.example.timers_24_backend.dto.ResultatDto;
import com.example.timers_24_backend.service.ResultatService;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/resultater")
public class ResultatController {

    private final ResultatService resultatService;


    public ResultatController(ResultatService resultatService) {
        this.resultatService = resultatService;
    }

    @GetMapping
    public ResponseEntity<List<ResultatDto>> getAllResultater() {
        List<ResultatDto> resultatDtoList = resultatService.getAllResultater();
        return ResponseEntity.ok(resultatService.getAllResultater());
    }


    @PostMapping
    public ResponseEntity<ResultatDto> registrerResultat(@RequestBody ResultatDto resultatDto) {
        ResultatDto createdResultat = resultatService.registrerResultat( resultatDto);
        return ResponseEntity.created(URI.create("/api/resultater/" + createdResultat.getId())).body(createdResultat);
    }
}

