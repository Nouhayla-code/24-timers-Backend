package com.example.timers_24_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private int roomNumber;
    private int numberOfBeds;
    private int price;
    private LocalDateTime created;
    private LocalDateTime updated;


    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @OneToMany(mappedBy = "room")
    private List<Reservation> reservations;



    public Room(int roomNumber, int numberOfBeds, int price) {
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
        this.price = price;
        this.created = LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }
}
