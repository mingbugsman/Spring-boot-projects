package com.TicketSelling.TicketSelling.DTO.Response.Payment;
import com.TicketSelling.TicketSelling.DTO.Response.Seat.SeatResponse;
import com.TicketSelling.TicketSelling.DTO.Response.Ticket.TicketDetailResponse;

import java.util.List;

public record PaymentInformation (
        int totalTickets,
        double totalAmount,
        List<SeatResponse> ticketDetailResponses
) { }