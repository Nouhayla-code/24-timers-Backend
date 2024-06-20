package com.example.timers_24_backend.dto;

import com.example.timers_24_backend.entity.Deltager;
import com.example.timers_24_backend.entity.Disciplin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ResultatDto {

    private UUID id;
    private String resultattype;
    private LocalDate dato;
    private String resultatvaerdi;
    private UUID deltagerId;
    private UUID disciplinId;

    // Constructor to convert from entity to DTO
    public ResultatDto(UUID id, String resultattype, LocalDate dato, String resultatvaerdi, UUID deltagerId, UUID disciplinId) {
        this.id = id;
        this.resultattype = resultattype;
        this.dato = dato;
        this.resultatvaerdi = resultatvaerdi;
        this.deltagerId = deltagerId;
        this.disciplinId = disciplinId;
    }
}
