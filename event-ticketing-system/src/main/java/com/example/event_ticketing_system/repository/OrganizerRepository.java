package com.example.event_ticketing_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.event_ticketing_system.entity.Organizer;

public interface OrganizerRepository extends JpaRepository<Organizer, Integer> {
    
}
