package com.TicketSelling.TicketSelling.DTO.Response.Booking;

import com.TicketSelling.TicketSelling.DTO.Response.Ticket.TicketResponse;
import com.TicketSelling.TicketSelling.Enum.BookingStatus;

import java.time.LocalDateTime;
import java.util.List;

public record BookingTicketsResponse(
        String id,
        String customerId,
        String customerName,
        BookingStatus bookingStatus,
        LocalDateTime createdAt,
        LocalDateTime lastUpdate,
        List<TicketResponse> tickets
) {
}
