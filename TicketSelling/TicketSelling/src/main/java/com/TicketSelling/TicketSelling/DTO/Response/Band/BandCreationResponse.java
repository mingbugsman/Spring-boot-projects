package com.TicketSelling.TicketSelling.DTO.Response.Band;

import com.TicketSelling.TicketSelling.Enum.BandStatus;

import java.time.LocalDateTime;

public record BandCreationResponse (
    String bandName,
    String bandInformation,
    BandStatus bandStatus,
    LocalDateTime createdAt
)
{}