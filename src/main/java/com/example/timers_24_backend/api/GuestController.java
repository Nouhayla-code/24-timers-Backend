package com.example.timers_24_backend.api;

import com.example.timers_24_backend.service.GuestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping("/guests")
    public String getGuests() {
        return "Guests";
    }

    @GetMapping("/guests/{id}")
    public String getGuest() {
        return "Guest";
    }



}


