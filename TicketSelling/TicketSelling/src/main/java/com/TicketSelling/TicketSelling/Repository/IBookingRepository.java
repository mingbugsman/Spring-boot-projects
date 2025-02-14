package com.TicketSelling.TicketSelling.Repository;

import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingCreationRequest;
import com.TicketSelling.TicketSelling.DTO.Request.Booking.BookingUpdateRequest;
import com.TicketSelling.TicketSelling.DTO.Response.Booking.BookingResponse;
import com.TicketSelling.TicketSelling.Entity.Booking;
import com.TicketSelling.TicketSelling.Enum.TypedSort;

import java.util.List;


public interface IBookingRepository {
    Booking getBookingById(String bookingId);
    BookingResponse getBooking(String bookingId);
    List<BookingResponse> getAllBookings(TypedSort typedSort);
    BookingResponse createNewBooking(BookingCreationRequest request);
    BookingResponse updateBooking(String bookingId, BookingUpdateRequest request);
    public void deleteBooking(String bookingId);
}
