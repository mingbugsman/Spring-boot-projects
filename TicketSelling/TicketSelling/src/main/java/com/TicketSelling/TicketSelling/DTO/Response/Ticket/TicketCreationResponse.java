package com.TicketSelling.TicketSelling.DTO.Response.Ticket;

import com.TicketSelling.TicketSelling.Enum.TicketStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;


public record TicketCreationResponse (
    String seatId,
    String concertId,
    String bookingId,
    TicketStatus ticketStatus,
    BigDecimal price,
    LocalDateTime createdAt
) {}
