package com.example.timers_24_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Resultat {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String resultattype;
    private LocalDate dato;
    private String resultatvaerdi;



    @ManyToOne
    @JoinColumn(name = "deltager_id")
    private Deltager deltager;

    @ManyToOne
    @JoinColumn(name = "disciplin_id")
    private Disciplin disciplin;

    public Resultat(String resultattype, LocalDate dato, String resultatvaerdi,
                    Deltager deltager, Disciplin disciplin) {
        this.resultattype = resultattype;
        this.dato = dato;
        this.resultatvaerdi = resultatvaerdi;
        this.deltager = deltager;
        this.disciplin = disciplin;




    }

    public Resultat(Object placering, Object resultat) {
        // Constructor with placering and resultat, which seems unused based on your provided code

    }
}
