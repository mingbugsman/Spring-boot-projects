package com.TicketSelling.TicketSelling.DTO.Response.Hall;

import com.TicketSelling.TicketSelling.Enum.HallStatus;

import java.time.LocalDateTime;

public record HallResponse(
        String id,
        String hallName,
        HallStatus hallStatus,
        String hallInformation,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
