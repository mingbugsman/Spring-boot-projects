package com.TicketSelling.TicketSelling.DTO.Response.Band;

import com.TicketSelling.TicketSelling.Enum.BandStatus;

import java.time.LocalDateTime;

public record BandResponse(
        String id,
        BandStatus bandStatus,
        String bandName,
        String bandInformation,
        LocalDateTime updateAt
) {
}
