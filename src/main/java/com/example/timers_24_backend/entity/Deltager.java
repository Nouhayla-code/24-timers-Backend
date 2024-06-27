package com.example.timers_24_backend.entity;

import com.example.timers_24_backend.dto.ResultatDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Deltager {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String navn;
    private String kon;
    private int alder;
    private String klub;

    // One to many relation til Resultat
    @OneToMany(mappedBy = "deltager", cascade = CascadeType.ALL)
    private List<Resultat> resultater;



    // Many to many relation til Disciplin
    @ManyToMany
    @JoinTable(
            name = "deltager_disciplin",
            joinColumns = @JoinColumn(name = "deltager_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplin_id")
    )

    // List of discipliner
    private List<Disciplin> discipliner;

    public Deltager(String navn, String kon, int alder, String klub) {
        this.navn = navn;
        this.kon = kon;
        this.alder = alder;
        this.klub = klub;
    }

    public Deltager(UUID id, String navn, String kon, int alder, String klub, List<ResultatDto> resultater, List<UUID> discipliner) {this.id = id;
        this.navn = navn;
        this.kon = kon;
        this.alder = alder;
        this.klub = klub;

    }

    // Constructor to convert from entity to DTO
    public Deltager(UUID uuid, String s, String male, int i, String testKlub) {
    }


    public void setResultater(List<Resultat> resultats) {

    }

    public void setDiscipliner(List<Disciplin> discipliner) {

    }
}
