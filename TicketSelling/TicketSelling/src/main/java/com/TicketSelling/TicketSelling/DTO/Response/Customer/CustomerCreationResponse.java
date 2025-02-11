package com.TicketSelling.TicketSelling.DTO.Response.Customer;




public record CustomerCreationResponse (
        String id,
        String name,
        String address,
        String birthDate
) {}