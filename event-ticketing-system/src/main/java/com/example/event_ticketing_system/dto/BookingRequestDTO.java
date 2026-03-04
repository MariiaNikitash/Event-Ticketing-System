package com.example.event_ticketing_system.dto;

import lombok.Data;

@Data
public class BookingRequestDTO {
    private Integer attendeeId;
    private Integer ticketTypeId;
}