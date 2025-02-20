package com.TicketSelling.TicketSelling.DTO.Response.Seat;

import com.TicketSelling.TicketSelling.Enum.SeatStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SeatResponse (
        String id,
        SeatStatus seatStatus,
        String rowLabel,
        Integer seatNumber,
        String hallName,
        BigDecimal price,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
)
{ }
