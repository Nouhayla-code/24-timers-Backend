package com.example.timers_24_backend.api;

import com.example.timers_24_backend.dto.DisciplinDto;
import com.example.timers_24_backend.entity.Disciplin;
import com.example.timers_24_backend.service.DisciplinService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/disciplin")
public class DisciplinController {

    private final DisciplinService disciplinService;

    @Autowired
    public DisciplinController(DisciplinService disciplinService) {
        this.disciplinService = disciplinService;
    }

    @PostConstruct
    public void init() {
        List<Disciplin> disciplins = new ArrayList<>();
        disciplins.add(new Disciplin("1-milløb (atletik)", "time"));
        disciplins.add(new Disciplin("10.000-meterløb (atletik)", "time"));
        disciplins.add(new Disciplin("100-meterløb (atletik)", "time"));
        disciplins.add(new Disciplin("110 meter hækkeløb", "time"));
        disciplins.add(new Disciplin("1500-meterløb (atletik)", "time"));
        disciplins.add(new Disciplin("200 meter hækkeløb", "time"));
        disciplins.add(new Disciplin("200-meterløb (atletik)", "time"));
        disciplins.add(new Disciplin("3000-meterløb (atletik)", "time"));
        disciplins.add(new Disciplin("4 × 100-meterløb (atletik)", "time"));
        disciplins.add(new Disciplin("4 × 400-meterløb (atletik)", "time"));
        disciplins.add(new Disciplin("400 meter hækkeløb", "time"));
        disciplins.add(new Disciplin("400-meterløb (atletik)", "time"));
        disciplins.add(new Disciplin("4x400-meterløb blandet hold (atletik)", "time"));
        disciplins.add(new Disciplin("5000-meter-løb (atletik)", "time"));
        disciplins.add(new Disciplin("60 meter hækkeløb", "time"));
        disciplins.add(new Disciplin("60-meterløb (atletik)", "time"));
        disciplins.add(new Disciplin("800-meterløb (atletik)", "time"));
        disciplins.add(new Disciplin("Cross (løbesport)", "time"));
        disciplins.add(new Disciplin("Diskoskast (atletik)", "distance"));
        disciplins.add(new Disciplin("Femkamp (atletik)", "points"));
        disciplins.add(new Disciplin("Forhindringsløb (atletik)", "time"));
        disciplins.add(new Disciplin("Halvmaratonløb (løbesport)", "time"));
        disciplins.add(new Disciplin("Hammerkast (atletik)", "distance"));
        disciplins.add(new Disciplin("Højdespring (atletik)", "height"));
        disciplins.add(new Disciplin("Højdespring uden tilløb (atletik)", "height"));

        disciplinService.createDisciplins(disciplins);
    }

    @GetMapping
    public ResponseEntity<List<DisciplinDto>> getAllDiscipliner() {
        List<DisciplinDto> disciplinDtoList = disciplinService.getAllDiscipliner();
        return ResponseEntity.ok().body(disciplinDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinDto> getDisciplin(@PathVariable UUID id) {
        DisciplinDto disciplinDto = disciplinService.getDisciplin(id);
        if (disciplinDto != null) {
            return ResponseEntity.ok(disciplinDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinDto> addDeltagerToDisciplin(@PathVariable UUID id, @RequestBody List<UUID> deltagerId) {
        DisciplinDto disciplinDto = disciplinService.addDeltagerToDisciplin(id, deltagerId);
        if (disciplinDto != null) {
            return ResponseEntity.ok(disciplinDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
