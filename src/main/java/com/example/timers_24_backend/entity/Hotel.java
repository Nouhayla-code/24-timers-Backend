package com.example.timers_24_backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Hotel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    private String street;
    private String city;
    private int zip;
    private String country;

    public Hotel(String name, String street, String city, int zip, String country) {

        this.name = name;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.country = country;
    }
}
