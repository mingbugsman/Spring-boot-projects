package com.TicketSelling.TicketSelling.DTO.Response.Seat;

import com.TicketSelling.TicketSelling.Enum.SeatStatus;

public record SeatResponse (
        String id,
        SeatStatus seatStatus,
        Integer rowNumber,
        Integer seatNumber,
        String hallName
)
{ }
