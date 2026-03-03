package com.example.event_ticketing_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.event_ticketing_system.entity.Attendee;

@Repository
public interface  AttendeeRepository extends JpaRepository<Attendee, Integer>{
    
}
