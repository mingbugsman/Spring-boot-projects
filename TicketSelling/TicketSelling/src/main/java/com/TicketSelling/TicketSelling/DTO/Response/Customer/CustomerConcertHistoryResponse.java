package com.TicketSelling.TicketSelling.DTO.Response.Customer;


import com.TicketSelling.TicketSelling.DTO.Response.Concert.ConcertResponse;


import java.util.List;


public record CustomerConcertHistoryResponse (
    String id,
    String customerName,
    List<ConcertResponse> concerts
){}
