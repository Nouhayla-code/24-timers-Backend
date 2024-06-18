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
public class Guest {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private double phoneNumber;

    public Guest(String username, String firstName, String lastName, String email, double phoneNumber) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}



