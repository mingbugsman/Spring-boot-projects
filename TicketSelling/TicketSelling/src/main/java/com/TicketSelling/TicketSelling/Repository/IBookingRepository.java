package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingResponse;
import com.TicketSelling.TicketSelling.Entity.Booking;
import com.TicketSelling.TicketSelling.Enum.TypedSort;

import java.util.List;


public interface IBookingRepository {
    Booking save(Booking booking);
    Booking getBookingById(String bookingId);
    List<Booking> getAllBookings(TypedSort typedSort);
    void deleteBookingById(String bookingId);
    boolean existsById(String bookingId);
}
