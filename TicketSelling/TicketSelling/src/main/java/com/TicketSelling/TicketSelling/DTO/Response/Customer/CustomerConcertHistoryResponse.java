package com.TicketSelling.TicketSelling.DTO.Response.Customer;


import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertResponse;


import java.util.List;


public record CustomerConcertHistoryResponse (
    String id,
    String name,
    List<ConcertResponse> concerts
){}
