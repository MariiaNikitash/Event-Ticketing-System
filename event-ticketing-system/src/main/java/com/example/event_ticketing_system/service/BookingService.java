package com.example.event_ticketing_system.service;

import java.time.LocalDateTime;
import java.time.Year;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.event_ticketing_system.dto.BookingResponseDTO;
import com.example.event_ticketing_system.entity.Attendee;
import com.example.event_ticketing_system.entity.Booking;
import com.example.event_ticketing_system.entity.Booking.PaymentStatus;
import com.example.event_ticketing_system.entity.TicketType;
import com.example.event_ticketing_system.repository.AttendeeRepository;
import com.example.event_ticketing_system.repository.BookingRepository;
import com.example.event_ticketing_system.repository.TicketTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final BookingRepository bookingRepository;
    private final TicketTypeRepository ticketTypeRepository;
    private final AttendeeRepository attendeeRepository;

    // Make sure to properly test bookTicket and cancelBooking, as the prior APIs
    // are missing.
    @Transactional
    public BookingResponseDTO bookTicket(Integer attendeeId, Integer ticketTypeId) {
        TicketType ticketType = ticketTypeRepository.findById(ticketTypeId)
                .orElseThrow(() -> new RuntimeException("Ticket type not found."));

        if (ticketType.getQuantity_available() <= 0) {
            throw new RuntimeException("Sorry, this ticket type is sold out.");
        }

        boolean alreadyBooked = bookingRepository.alreadyBooked(attendeeId, ticketTypeId);

        if (alreadyBooked) {
            throw new RuntimeException("You have already booked this ticket type.");
        }

        Attendee attendee = attendeeRepository.findById(attendeeId)
                .orElseThrow(() -> new RuntimeException("Attendee not found."));

        ticketType.setQuantity_available(ticketType.getQuantity_available() - 1);

        Booking booking = new Booking();
        booking.setAttendee(attendee);
        booking.setTicketType(ticketType);
        booking.setBooking_date(LocalDateTime.now());
        booking.setPayment_status(PaymentStatus.CONFIRMED);

        Booking savedBooking = bookingRepository.save(booking);
        String reference = String.format(
                "TKT-%d-%05d",
                Year.now().getValue(),
                savedBooking.getBooking_id());
        savedBooking.setBooking_reference(reference);

        return new BookingResponseDTO(
                reference,
                savedBooking.getBooking_date(),
                savedBooking.getPayment_status().name(),
                attendee.getName(),
                ticketType.getEvent().getTitle(),
                ticketType.getName(),
                ticketType.getPrice());
    }

    @Transactional
    public BookingResponseDTO cancelBooking(Integer bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found."));

        if (booking.getPayment_status() == PaymentStatus.CANCELLED) {
            throw new RuntimeException("Booking is already cancelled.");
        }

        booking.setPayment_status(PaymentStatus.CANCELLED);

        TicketType ticketType = booking.getTicketType();
        ticketType.setQuantity_available(ticketType.getQuantity_available() + 1);

        return new BookingResponseDTO(
                booking.getBooking_reference(),
                booking.getBooking_date(),
                booking.getPayment_status().name(),
                booking.getAttendee().getName(),
                ticketType.getEvent().getTitle(),
                ticketType.getName(),
                ticketType.getPrice());
    }
}
