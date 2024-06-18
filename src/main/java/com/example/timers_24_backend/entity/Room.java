package com.example.timers_24_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public Room(int roomNumber, int numberOfBeds, int price) {
        this.roomNumber = roomNumber;
        this.numberOfBeds = numberOfBeds;
        this.price = price;
    }
}
