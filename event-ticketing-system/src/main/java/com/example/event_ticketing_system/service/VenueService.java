package com.example.event_ticketing_system.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.event_ticketing_system.entity.Venue;
import com.example.event_ticketing_system.repository.VenueRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VenueService {
    private final VenueRepository venueRepository;

    @Transactional
    public Venue createVenue(Venue venue) {
        return venueRepository.save(venue);
    } 
}
