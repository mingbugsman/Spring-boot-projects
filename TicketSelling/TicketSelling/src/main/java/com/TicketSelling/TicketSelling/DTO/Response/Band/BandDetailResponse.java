package com.TicketSelling.TicketSelling.DTO.Response.Band;

import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertResponse;
import com.TicketSelling.TicketSelling.Enum.BandStatus;

import java.time.LocalDateTime;
import java.util.List;

public record BandDetailResponse(
        String id,
        String bandName,
        BandStatus bandStatus,
        String bandInformation,
        List<ConcertResponse> concerList,
        LocalDateTime updatedAt // last updated
) {
}
