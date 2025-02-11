package com.TicketSelling.TicketSelling.DTO.Response.Booking;

import com.TicketSelling.TicketSelling.Enum.BookingStatus;
import java.time.LocalDateTime;


public record BookingResponse (
    String id,
    BookingStatus bookingStatus,
    LocalDateTime createdAt
){}
