package com.example.timers_24_backend.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisciplinDto {

    private UUID id;
    private String navn;
    private String resultattype;

    private List<UUID> deltagereId;





}
