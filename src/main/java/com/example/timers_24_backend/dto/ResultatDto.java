package com.example.timers_24_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultatDto {

    private int placering;
    private String resultat;


    // Getter for placering
    public int getPlacering() {
        return placering;
    }

    // Setter for placering
    public void setPlacering(int placering) {
        this.placering = placering;
    }

    // Getter for resultat
    public String getResultat() {
        return resultat;
    }

    // Setter for resultat
    public void setResultat(String resultat) {
        this.resultat = resultat;
    }
}
