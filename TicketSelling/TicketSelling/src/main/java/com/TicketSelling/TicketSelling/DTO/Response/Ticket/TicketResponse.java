package com.TicketSelling.TicketSelling.DTO.Response.Ticket;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TicketResponse(
        String concertId,
        String seatId,
        String bookingId,
        BigDecimal price,
        LocalDateTime createdAt
) {
}
