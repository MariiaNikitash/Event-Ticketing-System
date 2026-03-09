package com.example.event_ticketing_system.controller;

import com.example.event_ticketing_system.dto.EventRequestDTO;
import com.example.event_ticketing_system.dto.EventResponseDTO;
import com.example.event_ticketing_system.service.EventService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponseDTO> createEvent(@RequestBody EventRequestDTO dto) {

        EventResponseDTO response = eventService.createEvent(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}