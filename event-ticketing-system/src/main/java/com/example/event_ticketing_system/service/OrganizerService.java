package com.example.event_ticketing_system.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.event_ticketing_system.entity.Organizer;
import com.example.event_ticketing_system.repository.OrganizerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrganizerService {
    private final OrganizerRepository organizerRepository;

    @Transactional
    public Organizer createOrganizer(Organizer organizer) {
        return organizerRepository.save(organizer);
    }
}
