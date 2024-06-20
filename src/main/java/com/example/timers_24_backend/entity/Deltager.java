package com.example.timers_24_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @OneToMany(mappedBy = "deltager", cascade = CascadeType.ALL)
    private List<Resultat> resultater;

    @ManyToMany
    @JoinTable(
            name = "deltager_disciplin",
            joinColumns = @JoinColumn(name = "deltager_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplin_id")
    )
    private List<Disciplin> discipliner;

    public Deltager(String navn, String kon, int alder, String klub) {
        this.navn = navn;
        this.kon = kon;
        this.alder = alder;
        this.klub = klub;
    }

    public Deltager(UUID id, String navn, String kon, int alder, String klub, List<String> resultater, List<String> discipliner) {this.id = id;
        this.navn = navn;
        this.kon = kon;
        this.alder = alder;
        this.klub = klub;

    }

    public void setDiscipliner(List<String> discipliner) {

    }

    public void setResultater(List<String> resultater) {

    }
}
