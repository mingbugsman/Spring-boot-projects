package com.TicketSelling.TicketSelling.DTO.Response.SeatCategory;


import com.TicketSelling.TicketSelling.Enum.SeatType;

import java.math.BigDecimal;

public record SeatCategoryResponse (
        String id,
        BigDecimal price,
        Integer totalSeats,
        SeatType seatType,
        String hallName
) {}
