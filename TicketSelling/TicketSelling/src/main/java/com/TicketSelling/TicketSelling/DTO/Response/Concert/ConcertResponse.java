package com.TicketSelling.TicketSelling.DTO.Response.Concert;

import com.TicketSelling.TicketSelling.Enum.ConcertStatus;

import java.time.LocalDateTime;

public record ConcertResponse(
        String id,
        String concertName,
        ConcertStatus concertStatus,
        LocalDateTime createdDate,
        LocalDateTime startDate,
        LocalDateTime lastUpdate,
        Integer totalBands
) {
}
