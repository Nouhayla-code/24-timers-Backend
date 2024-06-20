package com.example.timers_24_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GuestDto {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private double phoneNumber;

}
