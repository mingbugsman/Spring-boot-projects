package com.TicketSelling.TicketSelling.DTO.Response.Ticket;

import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Seat.SeatResponse;
import com.TicketSelling.TicketSelling.Enum.TicketStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketDetailResponse (
        TicketStatus ticketStatus,
        SeatResponse seat,
        ConcertDetailResponse concert,
        BookingResponse booking,
        BigDecimal price,
        LocalDateTime createdAt,
        LocalDateTime lastUpdate
)
{ }
