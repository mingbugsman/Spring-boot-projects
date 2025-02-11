package com.TicketSelling.TicketSelling.DTO.Response.Customer;


import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingResponse;


import java.util.List;


public record CustomerBookingsResponse (
    String id,
    String name,
    List<BookingResponse> bookings
) {}
