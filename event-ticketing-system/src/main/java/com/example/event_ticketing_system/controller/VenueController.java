package com.example.event_ticketing_system.controller;

import com.example.event_ticketing_system.entity.Venue;
import com.example.event_ticketing_system.service.VenueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/venues")
@RequiredArgsConstructor
public class VenueController {

    private final VenueService venueService;

    @PostMapping
    public ResponseEntity<Venue> createVenue(@RequestBody Venue venue) {
        System.out.println("capacity = " + venue.getTotal_capacity());
        Venue saved = venueService.createVenue(venue);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
}
