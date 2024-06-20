package com.example.timers_24_backend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ReservationDto {

    private LocalDate reservationDate;
    private int price;
    private String room;
    private String guest;

}
