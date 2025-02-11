package com.TicketSelling.TicketSelling.DTO.Response.Hall;

import com.TicketSelling.TicketSelling.Enum.HallStatus;

public record HallCreationResponse(
        String id,
        String hallName,
        HallStatus hallStatus,
        String informationHall
) {
}
