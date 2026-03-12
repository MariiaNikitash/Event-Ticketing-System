package com.example.event_ticketing_system.service;

import com.example.event_ticketing_system.dto.EventRequestDTO;
import com.example.event_ticketing_system.dto.EventResponseDTO;
import com.example.event_ticketing_system.entity.Event;
import com.example.event_ticketing_system.entity.Organizer;
import com.example.event_ticketing_system.entity.Venue;
import com.example.event_ticketing_system.repository.EventRepository;
import com.example.event_ticketing_system.repository.OrganizerRepository;
import com.example.event_ticketing_system.repository.VenueRepository;

import lombok.RequiredArgsConstructor;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final OrganizerRepository organizerRepository;
    private final VenueRepository venueRepository;

    @Transactional

    public EventResponseDTO createEvent(EventRequestDTO dto) {

        Organizer organizer = organizerRepository.findById(dto.getOrganizer_id())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Organizer not found"));

        Venue venue = venueRepository.findById(dto.getVenue_id())
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Venue not found"));

        Event event = new Event();
        event.setTitle(dto.getTitle());
        event.setDescription(dto.getDescription());
        event.setEvent_date(dto.getEvent_date());
        event.setStatus(dto.getStatus());
        event.setOrganizer(organizer);
        event.setVenue(venue);

        Event saved = eventRepository.save(event);

        EventResponseDTO response = new EventResponseDTO();
        response.setEvent_id(saved.getEvent_id());
        response.setTitle(saved.getTitle());
        response.setDescription(saved.getDescription());
        response.setEvent_date(saved.getEvent_date());
        response.setStatus(saved.getStatus().name());
        response.setOrganizerName(saved.getOrganizer().getName());
        response.setVenueName(saved.getVenue().getName());

        return response;
    }

    // Get all events method
    public List<EventResponseDTO> getAllEvents() {
        List<Event> events = eventRepository.findAll();

        return events.stream().map(event -> {
            EventResponseDTO response = new EventResponseDTO();
            response.setEvent_id(event.getEvent_id());
            response.setTitle(event.getTitle());
            response.setDescription(event.getDescription());
            response.setEvent_date(event.getEvent_date());
            response.setStatus(event.getStatus().name());
            response.setOrganizerName(event.getOrganizer().getName());
            response.setVenueName(event.getVenue().getName());
            return response;
        }).toList();
    }

    // Get event by id
    public EventResponseDTO getEventById(Integer id) {
        Event event = eventRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));

        EventResponseDTO response = new EventResponseDTO();
        response.setEvent_id(event.getEvent_id());
        response.setTitle(event.getTitle());
        response.setDescription(event.getDescription());
        response.setEvent_date(event.getEvent_date());
        response.setStatus(event.getStatus().name());
        response.setOrganizerName(event.getOrganizer().getName());
        response.setVenueName(event.getVenue().getName());

        return response;
    }
}