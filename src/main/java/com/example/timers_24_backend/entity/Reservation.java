package com.example.timers_24_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter

public class Reservation  {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private LocalDateTime reservationDate;
    private int price;

    public Reservation(LocalDateTime reservationDate, int price) {
        this.reservationDate = reservationDate;
        this.price = price;
    }
}
