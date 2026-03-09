package com.example.event_ticketing_system.service;

import com.example.event_ticketing_system.entity.Venue;
import com.example.event_ticketing_system.repository.VenueRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VenueService {
    private final VenueRepository venueRepository;

    @Transactional
    public Venue createVenue(Venue venue) {
        return venueRepository.save(venue);
    }
}