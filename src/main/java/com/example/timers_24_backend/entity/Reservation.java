package com.example.timers_24_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter

public class Reservation{
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private LocalDate reservationDate;
    private int price;

    @ManyToOne
    private Room room;

    @ManyToOne
    private Guest guest;

    public Reservation(LocalDate reservationDate, int price) {
        this.reservationDate = reservationDate;
        this.price = price;
    }
}
