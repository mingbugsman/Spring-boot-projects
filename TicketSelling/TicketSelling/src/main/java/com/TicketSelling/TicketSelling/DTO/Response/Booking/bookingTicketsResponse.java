package com.TicketSelling.TicketSelling.DTO.Response.Booking;

import com.TicketSelling.TicketSelling.DTO.Response.Ticket.TicketResponse;
import com.TicketSelling.TicketSelling.Enum.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;

public record bookingTicketsResponse(
        String id,
        BookingStatus bookingStatus,
        LocalDateTime createdAt,
        List<TicketResponse> ticketList
) {
}
