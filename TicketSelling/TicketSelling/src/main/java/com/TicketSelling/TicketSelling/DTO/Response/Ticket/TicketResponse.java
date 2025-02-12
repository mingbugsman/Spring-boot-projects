package com.TicketSelling.TicketSelling.DTO.Response.Ticket;

import com.TicketSelling.TicketSelling.Enum.TicketStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketResponse(

        String concertId,
        TicketStatus ticketStatus,
        String seatId,
        String bookingId,
        BigDecimal price,
        LocalDateTime createdAt,
        LocalDateTime lastUpdated
) {
}
