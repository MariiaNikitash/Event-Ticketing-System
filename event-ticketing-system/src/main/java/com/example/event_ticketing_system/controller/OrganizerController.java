package com.example.event_ticketing_system.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.event_ticketing_system.entity.Organizer;
import com.example.event_ticketing_system.service.OrganizerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/organizers")
@RequiredArgsConstructor
public class OrganizerController {
    private final OrganizerService organizerService;

    @PostMapping
    public ResponseEntity<Organizer> createOrganizer(@RequestBody Organizer organizer) {
        Organizer created = organizerService.createOrganizer(organizer);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

}
