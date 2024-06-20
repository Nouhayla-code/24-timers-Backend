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
public class Disciplin {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String navn;
    private String resultattype;

    @OneToMany(mappedBy = "disciplin", cascade = CascadeType.ALL)
    private List<Resultat> resultater;

    @ManyToMany(mappedBy = "discipliner")
    private List<Deltager> deltagere;

    public Disciplin(String navn, String resultattype) {
        this.navn = navn;
        this.resultattype = resultattype;
    }
}
