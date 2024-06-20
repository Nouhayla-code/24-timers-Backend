package com.example.timers_24_backend.api;

import com.example.timers_24_backend.dto.DeltagerDto;
import com.example.timers_24_backend.service.DeltagerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/deltager")
public class DeltagerController {

    private final DeltagerService deltagerService;

    @Autowired
    public DeltagerController(DeltagerService deltagerService) {
        this.deltagerService = deltagerService;
    }

    @PostConstruct
    public void init() {
        List<DeltagerDto> deltagerDtoList = new ArrayList<>();

        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Mikkel", "Mand", 25, "Herning"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Sofie", "Kvinde", 30, "Aarhus"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Peter", "Mand", 28, "Odense"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Anna", "Kvinde", 26, "København"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Christian", "Mand", 33, "Aalborg"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Maria", "Kvinde", 29, "Esbjerg"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Andreas", "Mand", 27, "Vejle"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Louise", "Kvinde", 31, "Frederiksberg"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Thomas", "Mand", 32, "Randers"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Julie", "Kvinde", 24, "Kolding"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Nikolaj", "Mand", 26, "Horsens"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Camilla", "Kvinde", 27, "Silkeborg"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Martin", "Mand", 29, "Holstebro"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Sarah", "Kvinde", 28, "Roskilde"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Sebastian", "Mand", 30, "Helsingør"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Ida", "Kvinde", 33, "Sønderborg"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Jonas", "Mand", 31, "Næstved"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Laura", "Kvinde", 25, "Fredericia"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Mathias", "Mand", 28, "Holbæk"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Emma", "Kvinde", 27, "Slagelse"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Jens", "Mand", 29, "Ringsted"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Lou", "Kvinde", 30, "Hillerød"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Simon", "Mand", 32, "Frederikshavn"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Mette", "Kvinde", 31, "Glostrup"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Christianne", "Kvinde", 28, "Hjørring"));
        deltagerDtoList.add(new DeltagerDto(UUID.randomUUID(), "Henrik", "Mand", 27, "Albertslund"));

        deltagerDtoList.forEach(deltagerDto -> deltagerService.createDeltager(deltagerDto));
    }


    @GetMapping
    public ResponseEntity<List<DeltagerDto>> getAllDeltager() {
        List<DeltagerDto> deltagerDtoList = deltagerService.getAllDeltager();
        return ResponseEntity.ok(deltagerDtoList);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DeltagerDto> getDeltagerById(@PathVariable UUID id) {
        DeltagerDto deltagerDto = deltagerService.getDeltagerById(id);
        if (deltagerDto != null) {
            return ResponseEntity.ok(deltagerDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DeltagerDto> createDeltager(@RequestBody DeltagerDto deltagerDto) {
        DeltagerDto createdDeltager = deltagerService.createDeltager(deltagerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDeltager);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeltagerDto> updateDeltager(@PathVariable UUID id, @RequestBody DeltagerDto deltagerDto) {
        DeltagerDto updatedDeltager = deltagerService.updateDeltager(id, deltagerDto);
        if (updatedDeltager != null) {
            return ResponseEntity.ok(updatedDeltager);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{deltagerId}/addDisciplin/{disciplinId}")
    public String addDisciplinToDeltager(@PathVariable UUID deltagerId, @PathVariable UUID disciplinId) {
        DeltagerDto updatedDeltager = deltagerService.addDisciplinToDeltager(deltagerId, disciplinId);
        if (updatedDeltager != null) {
            return "Disciplin added to Deltager";
        }  else {
            return "Disciplin not found";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteDeltager(@PathVariable UUID id) {
        boolean deleted = deltagerService.deleteDeltager(id);
        if (deleted) {
            return "Deltager slettet";
        } else {
            return "Deltager ikke fundet";
        }
    }
}
