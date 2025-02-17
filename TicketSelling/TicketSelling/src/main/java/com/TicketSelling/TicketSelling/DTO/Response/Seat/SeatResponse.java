package com.TicketSelling.TicketSelling.DTO.Response.Seat;

import com.TicketSelling.TicketSelling.Enum.SeatStatus;

import java.time.LocalDateTime;

public record SeatResponse (
        String id,
        SeatStatus seatStatus,
        String row,
        Integer seatNumber,
        String hallName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
)
{ }
