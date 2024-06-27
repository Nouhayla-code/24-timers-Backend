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


    // Many to one relation til Deltagerid
    @ManyToOne
    @JoinColumn(name = "deltager_id")
    private Deltager deltager;

    // Many to one relation til Disciplinid
    @ManyToOne
    @JoinColumn(name = "disciplin_id")
    private Disciplin disciplin;



    }



