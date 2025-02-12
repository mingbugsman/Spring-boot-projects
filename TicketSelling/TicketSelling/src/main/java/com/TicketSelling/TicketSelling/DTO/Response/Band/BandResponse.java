package com.TicketSelling.TicketSelling.DTO.Response.Band;

import com.TicketSelling.TicketSelling.Enum.BandStatus;

import java.time.LocalDateTime;

public record BandResponse(
        String id,
        String bandName,
        BandStatus bandStatus,
        String bandInformation,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
