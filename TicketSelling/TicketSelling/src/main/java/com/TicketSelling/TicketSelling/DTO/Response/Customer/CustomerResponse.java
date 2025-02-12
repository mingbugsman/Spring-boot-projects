package com.TicketSelling.TicketSelling.DTO.Response.Customer;


public record CustomerResponse (
        String id,
        String customerName,
        String address,
        String birthDate
) {}