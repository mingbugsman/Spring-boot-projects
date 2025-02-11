package com.TicketSelling.TicketSelling.DTO.Response.Hall;


import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertDetailResponse;

import java.util.List;



// paging algorithm should be used to optimize data retrieval
public record HallDetailResponse(
        String id,
        String hallName,
        String informationHall,
        Integer seatCount,
        Integer concertCount,
        List<ConcertDetailResponse> concertName
) {
}
