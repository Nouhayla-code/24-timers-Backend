package com.example.timers_24_backend.exception;


public class NotFoundException extends RuntimeException {


    // funktion til at give en fejlmeddelser, hvis der ikke findes en ressource
    public NotFoundException(String message) {
        super(message);
    }

}

