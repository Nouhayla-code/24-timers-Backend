package com.example.timers_24_backend.dto;

import com.example.timers_24_backend.entity.Disciplin;
import com.example.timers_24_backend.entity.Resultat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeltagerDto {
    private UUID id;
    private String navn;
    private String kon;
    private int alder;
    private String klub;
    private List<ResultatDto> resultater;
    private List<UUID> discipliner = new ArrayList<>();

    public DeltagerDto( UUID id , String navn, String kon, int alder, String klub) {
        this.id = id;
        this.navn = navn;
        this.kon = kon;
        this.alder = alder;
        this.klub = klub;
    }
}
