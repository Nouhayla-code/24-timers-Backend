package com.example.timers_24_backend.api;
import com.example.timers_24_backend.dto.ResultatDto;
import com.example.timers_24_backend.service.ResultatService;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;



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

