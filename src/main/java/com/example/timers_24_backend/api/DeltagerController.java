package com.example.timers_24_backend.api;

import com.example.timers_24_backend.dto.DeltagerDto;
import com.example.timers_24_backend.service.DeltagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeltager(@PathVariable UUID id) {
        boolean deleted = deltagerService.deleteDeltager(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
