package com.example.event_ticketing_system.dto;

import java.time.LocalDateTime;

import lombok.Data;
import com.example.event_ticketing_system.entity.Event.EventStatus;

@Data
public class EventRequestDTO {

    private String title;
    private String description;
    private LocalDateTime event_date;
    private EventStatus status;
    private Integer organizer_id;
    private Integer venue_id;

}