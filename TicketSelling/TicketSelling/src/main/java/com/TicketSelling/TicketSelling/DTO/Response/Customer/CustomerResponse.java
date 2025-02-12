package com.TicketSelling.TicketSelling.DTO.Response.Customer;


import java.time.LocalDate;

public record CustomerResponse (
        String id,
        String customerName,
        String email,
        String address,
        LocalDate birthDate
) {}