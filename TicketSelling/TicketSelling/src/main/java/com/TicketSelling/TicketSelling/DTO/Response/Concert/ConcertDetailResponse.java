package com.TicketSelling.TicketSelling.DTO.Response.Concert;

import com.TicketSelling.TicketSelling.DTO.Response.Band.BandDetailResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Band.BandResponse;
import com.TicketSelling.TicketSelling.Enum.ConcertStatus;


import java.time.LocalDateTime;
import java.util.List;


public record ConcertDetailResponse (
    String id,
    LocalDateTime date, // created date event
    LocalDateTime startDate, // event start date;
    ConcertStatus concertStatus,
    String hallName,
    String bandName,
    List<BandResponse> bandDetailList,
    LocalDateTime updatedAt // last updated
) {}
