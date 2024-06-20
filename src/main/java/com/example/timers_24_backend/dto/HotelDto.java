package com.example.timers_24_backend.dto;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@AllArgsConstructor
@Data

public class HotelDto {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    private String street;
    private int room;

}
