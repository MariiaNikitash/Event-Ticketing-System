package com.example.event_ticketing_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.event_ticketing_system.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

  @Query("""
          SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END
          FROM Booking b
          WHERE b.attendee.attendeeId = :attendeeId
            AND b.ticketType.ticketTypeId = :ticketTypeId
      """)
  boolean alreadyBooked(@Param("attendeeId") Integer attendeeId,
      @Param("ticketTypeId") Integer ticketTypeId);
}
