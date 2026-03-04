package com.example.event_ticketing_system.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingRequestDTO {
    private Integer attendeeId;
    private Integer ticketTypeId;
}