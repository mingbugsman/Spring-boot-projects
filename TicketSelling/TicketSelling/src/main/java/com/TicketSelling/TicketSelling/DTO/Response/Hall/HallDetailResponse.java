package com.TicketSelling.TicketSelling.DTO.Response.Hall;

import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertResponse;
import com.TicketSelling.TicketSelling.Enum.HallStatus;

import java.util.List;



// paging algorithm should be used to optimize data retrieval
public record HallDetailResponse(
        String id,
        String hallName,
        HallStatus hallStatus,
        String hallInformationa,
        Integer seatCount,
        Integer concertCount,
        List<ConcertResponse> concerts
) {
}
