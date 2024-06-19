package com.example.timers_24_backend.service;


import com.example.timers_24_backend.repository.GuestRepository;
import org.springframework.stereotype.Service;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }


}
