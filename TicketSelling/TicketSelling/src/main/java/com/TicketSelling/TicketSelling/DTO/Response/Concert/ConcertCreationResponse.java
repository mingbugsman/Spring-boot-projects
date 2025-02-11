package com.TicketSelling.TicketSelling.DTO.Response.Concert;


import com.TicketSelling.TicketSelling.Enum.ConcertStatus;

import java.time.LocalDateTime;




public record ConcertCreationResponse (
    String id,
    String concertName,
    ConcertStatus concertStatus,
    LocalDateTime date, // created date event
    LocalDateTime startDate // event start
){}