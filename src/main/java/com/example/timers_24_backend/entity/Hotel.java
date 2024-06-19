package com.example.timers_24_backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
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
    private LocalDateTime created;
    private LocalDateTime updated;


    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Room> rooms;

    public Hotel(String name, String street, String city, int zip, String country) {

        this.name = name;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.country = country;
        this.created= LocalDateTime.now();
        this.updated = LocalDateTime.now();
    }
}
