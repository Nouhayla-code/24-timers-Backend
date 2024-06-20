package com.example.timers_24_backend.service;

import com.example.timers_24_backend.entity.Hotel;
import com.example.timers_24_backend.entity.Room;
import com.example.timers_24_backend.repository.HotelRepository;
import com.example.timers_24_backend.repository.RoomRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataInitService {
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    public DataInitService(RoomRepository roomRepository, HotelRepository hotelRepository) {
        this.roomRepository = roomRepository;
        this.hotelRepository = hotelRepository;
    }

@PostConstruct
    public void init() {
        Random random = new Random();
        List <Hotel> hotels= new ArrayList<Hotel>();
        for (int i = 0; i < 250; i++) {
           Hotel hotel = new Hotel("Hotel " + i, "Street " + i, "City " + i, 1000 + i , "Country " + i);
            hotelRepository.save(hotel);

            Set<Room> rooms = new HashSet<Room>();
            for (int j = 0; j < 100; j++) {
                Room room = new Room(1+j, random.nextInt(4) + 1, random.nextInt(1000) + 500 );
                room.setHotel(hotel);
                rooms.add(room);

            }
            hotel.setRooms(rooms);
            hotels.add(hotel);

        }
        hotelRepository.saveAll(hotels);
    }
}
